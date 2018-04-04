package sang.com.weathermode.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import sang.com.baselibrary.utils.JLog;
import sang.com.baselibrary.utils.WUtils;

/**
 * 作者： ${PING} on 2018/4/4.
 *
 *  折线图
 */

public class LineChartView extends View {


    private Paint mPaint;
    private Path mPath;

    List<List<LineChatBean>> lineInfors;

    private int maxY =50;
    private int minY =-50;
    private int mHeight;
    private int mWidth;
    private int cellWidth;


    public LineChartView(Context context) {
        this(context,null,0);
    }

    public LineChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LineChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs,defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(WUtils.dip2px(context,1));
        mPath =new Path();
        lineInfors=new ArrayList<>();

        List<LineChatBean> line=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            line.add(new LineChatBean(WUtils.randomInt(0,50)));
        }
        lineInfors.add(line);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth=w;
        this.mHeight=h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (lineInfors!=null&&!lineInfors.isEmpty()){
            for (List<LineChatBean> lineInfor : lineInfors) {
                drawLine(lineInfor,mPath,canvas);
            }
        }






    }

    private void drawLine(List<LineChatBean> lineInfor,Path mPath,Canvas canvas) {
        if (lineInfor!=null&&!lineInfor.isEmpty()){
            final int y = maxY-minY;
            if (y==0){
                return;
            }
            cellWidth=mWidth/lineInfor.size();
            canvas.save();
            mPath.reset();
            for (int i = 0; i < lineInfor.size(); i++) {
                LineChatBean chatBean = lineInfor.get(i);

                int pointX = cellWidth * i + cellWidth / 2;
                int pointY = (int) ((chatBean.value*1.0/y)*mHeight);

                if (i==0){
                    mPath.moveTo(pointX,pointY);
                }else {
                    mPath.lineTo(pointX,pointY);
                }
                JLog.i(pointX+">>>"+pointY);
            }
            canvas.drawPath(mPath,mPaint);
            canvas.restore();

        }
    }

    public static class LineChatBean{
        public int value;
        public String title;

        public LineChatBean(int maxValue) {
            this.value = maxValue;
        }
    }

}
