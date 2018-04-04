package sang.com.weathermode.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

import sang.com.weathermode.drawable.WeatherConver;
import sang.com.weathermode.drawable.WeatherDrawable;
import sang.com.weathermode.drawable.holder.BasicWeatherHolder;
import sang.com.weathermode.factory.WeatherBgFactory;

/**
 * 作者： ${PING} on 2018/3/29.
 */

public class WeatherView extends SurfaceView implements SurfaceHolder.Callback, Runnable, WeatherDrawable.onValueChangesListener {

    private String tag = "DemoSurfaceView";


    private int width;//控件宽高
    private int height;//控件宽高
    private WeatherDrawable weatherDrawable;//背景
    private List<BasicWeatherHolder> currentHolders;//当前显示的天气状态装饰
    private List<BasicWeatherHolder> preHolders;//前一个天气状况显示
    private Paint mPaint;

    private Thread thread;
    private boolean isDrawing;//是否正在绘制，false停止绘制，结束线程


    public WeatherView(Context context) {
        this(context, null);
    }

    public WeatherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        SurfaceHolder mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        mSurfaceHolder.setFormat(PixelFormat.RGBA_8888);
        weatherDrawable = new WeatherDrawable(false, getContext());
        weatherDrawable.setListener(this);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        currentHolders=new ArrayList<>();
        preHolders=new ArrayList<>();

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isDrawing=true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        drawSomething();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDrawing=false;
    }

    public void changeColorsWithAni(Integer[] colors) {
        weatherDrawable.changToColorsWithAni(colors);
    }

    /**
     * 切换天气
     * @param type
     */
    public void changeWeather(WeatherConver.WeatherType type) {
        preHolders.clear();
        preHolders.addAll(currentHolders);
        currentHolders.clear();
        List<BasicWeatherHolder> holders = WeatherBgFactory.creatWeatherHolder(type,getContext(), width, height);
        if (holders!=null) {
            currentHolders.addAll(holders);
        }

        changeColorsWithAni(WeatherConver.creatSkyBackground(type));


    }
    @Override
    public void onValueChanges(float animatedFraction) {
        if (animatedFraction>0.9){
            preHolders.clear();
        }
        for (BasicWeatherHolder preHolder : preHolders) {
            preHolder.setAnimatedFraction(1-animatedFraction);
        }

        for (BasicWeatherHolder currentHolder : currentHolders) {
            currentHolder.setAnimatedFraction( animatedFraction);
        }
    }


    List<BasicWeatherHolder> holders = new ArrayList<>();
    private void drawSomething() {
        //获得canvas对象
        //绘制背景
        Canvas canvas = getHolder().lockCanvas();
        final int w = width;
        final int h = height;
        if (w == 0 || h == 0||canvas == null) {
            return;
        }
        weatherDrawable.setSize(width, height).draw(canvas);
        synchronized (WeatherView.class) {
            if (preHolders != null&&!preHolders.isEmpty()) {
                holders.addAll(preHolders);
                for (BasicWeatherHolder holder : holders) {
                    holder.excuse(canvas, mPaint);
                }
                holders.clear();
            }
            if (currentHolders != null&&!currentHolders.isEmpty()) {
                holders.addAll(currentHolders);
                for (BasicWeatherHolder holder : holders) {
                    holder.excuse(canvas, mPaint);
                }
                holders.clear();

            }
        }
        getHolder().unlockCanvasAndPost(canvas);
    }


    @Override
    public void run() {
        while (isDrawing) {
            drawSomething();
        }
    }
}
