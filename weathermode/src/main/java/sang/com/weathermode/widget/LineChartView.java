package sang.com.weathermode.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import sang.com.baselibrary.utils.JLog;
import sang.com.baselibrary.utils.WUtils;

/**
 * 作者： ${PING} on 2018/4/4.
 * <p>
 * 折线图
 */

public class LineChartView extends View {


    private Paint mPaint;
    private Path mPath;

    //    List<List<LineChatBean>> lineInfors;
    List<LineChatBean> lineInfors;

    private int maxY = 50;
    private int minY = -50;
    private int mHeight;
    private int mWidth;
    private int cellWidth;

    private Point pointDown, pointStart, pointEnd;


    public LineChartView(Context context) {
        this(context, null, 0);
    }

    public LineChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(WUtils.dip2px(context, 1));
        mPath = new Path();
        lineInfors = new ArrayList<>();
        pointDown = new Point();
        pointStart = new Point();
        pointEnd = new Point();

        cellWidth = WUtils.dip2px(context, 50);


        for (int i = 0; i < 20; i++) {
            lineInfors.add(new LineChatBean(WUtils.randomInt(0, 50)));
        }


    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (lineInfors != null && !lineInfors.isEmpty()) {
            drawLine(lineInfors, mPath, canvas);

        }
    }


    private int downX, downY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (pointDown.x == 0 && pointDown.y == 0) {
            pointDown.x = (int) event.getX();
            pointDown.y = (int) event.getY();
        }

        if (downY==0||downX==0){
            downX = (int) event.getX();
            downY = (int) event.getY();
        }
        
        boolean result = true;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                downX = (int) event.getX();
                downY = (int) event.getY();
                pointDown.x = downX;
                pointDown.y = downY;

                result = true;
                break;
            case MotionEvent.ACTION_MOVE:
                final float moveX = event.getX();
                final float moveY = event.getY();
                pointStart.x += moveX - pointDown.x;
                pointStart.y += moveY - pointDown.y;
                if (Math.abs(moveX - downX) > Math.abs(moveY - downY)) {
                    result = true;
                    if (lineInfors != null && !lineInfors.isEmpty()) {
                        int maxLeft = mWidth - cellWidth * lineInfors.size();
                        if (pointStart.x < maxLeft) {
                            pointStart.x = maxLeft;
                            result=false;
                            getParent().requestDisallowInterceptTouchEvent(false);

                        }

                        if (pointStart.x > 0) {
                            pointStart.x = 0;
                            result=false;
                            getParent().requestDisallowInterceptTouchEvent(false);
                        }

                    }
                    postInvalidate();
                } else {
                    result = false;
                }
                pointDown.x = (int) moveX;
                pointDown.y = (int) moveY;
                pointEnd.x = pointStart.x + mWidth;
                pointEnd.y = pointStart.y + mHeight;
                break;
            default:
                pointDown.x = 0;
                pointDown.y = 0;
                downX = 0;
                downY = 0;
                break;
        }

        JLog.i("result:"+result);

        return result;
    }

    private void drawLine(List<LineChatBean> lineInfor, Path mPath, Canvas canvas) {
        if (lineInfor != null && !lineInfor.isEmpty()) {
            final int y = maxY - minY;
            if (y == 0) {
                return;
            }
            canvas.save();
            mPath.reset();
            for (int i = 0; i < lineInfor.size(); i++) {
                LineChatBean chatBean = lineInfor.get(i);
                int pointX = cellWidth * i + cellWidth / 2;
                int pointY = (int) ((chatBean.value * 1.0 / y) * mHeight);
                pointX += pointStart.x;
                if (i == 0) {
                    mPath.moveTo(pointX, pointY);
                } else {
                    mPath.lineTo(pointX, pointY);
                }
            }
            canvas.drawPath(mPath, mPaint);
            canvas.restore();

        }
    }

    public static class LineChatBean {
        public int value;
        public String title;

        public LineChatBean(int maxValue) {
            this.value = maxValue;
        }
    }

}
