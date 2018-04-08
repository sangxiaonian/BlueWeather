package sang.com.weathermode.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
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

public class BaseLineChartView  extends View {


    protected Paint mPaint;
    protected Path mPath;

    List<LineChatBean> lineInfors;

    protected int maxY = 25;
    protected int minY = 10;
    protected int mHeight;
    protected int mWidth;
    protected int cellWidth;

    protected int textSize;

    protected Rect textRect;//文字测量使用

    protected Point pointDown, pointStart, pointEnd;
    protected int lineWidth;


    public BaseLineChartView(Context context) {
        this(context, null, 0);
    }

    public BaseLineChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseLineChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        lineWidth = WUtils.dip2px(context, 1);
        mPaint.setStrokeWidth(lineWidth);
        mPath = new Path();
        lineInfors = new ArrayList<>();
        pointDown = new Point();
        pointStart = new Point();
        pointEnd = new Point();
        textRect = new Rect();

        cellWidth = WUtils.dip2px(context, 50);
        textSize = WUtils.dip2px(context, 12);


        for (int i = 0; i < 20; i++) {
            lineInfors.add(new LineChatBean(WUtils.randomInt(minY, maxY)));
        }

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;
    }

    protected void checkDistance( ) {
        int maxLeft =getMaxLeft();
        if (pointStart.x < maxLeft) {
            pointStart.x = maxLeft;
        }
        if (pointStart.x > 0) {
            pointStart.x = 0;
        }
    }

    protected int getMaxLeft() {
        int maxLeft = mWidth - cellWidth * lineInfors.size();

        if (maxLeft>0){
            maxLeft=0;
        }
        return maxLeft;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (lineInfors != null && !lineInfors.isEmpty()) {
            drawLine(lineInfors, mPath, canvas);

        }
    }

    protected void drawLine(List<LineChatBean> lineInfor, Path mPath, Canvas canvas) {
        if (lineInfor != null && !lineInfor.isEmpty()) {

            final int y = maxY - minY;//总共的温度范围
            if (y == 0) {
                return;
            }
            canvas.save();
            mPath.reset();
            for (int i = 0; i < lineInfor.size(); i++) {
                LineChatBean chatBean = lineInfor.get(i);
                int pointX = cellWidth * i + cellWidth / 2;//x中心
                pointX += pointStart.x;


                /**
                 * 描述文字高度
                 */
                int desHeight = drawTimeText(canvas, String.valueOf("晴天"), pointX, textSize);
                //标签高度
                int lableHeight = getTextHeight(String.valueOf("晴天"), textSize);

                int height = mHeight - lableHeight * 2 - desHeight;//折线图所占领的高度

                int pointY = (int) (mHeight - desHeight - lableHeight - ((chatBean.value - minY) * 1.0 / y) * height);

                if (i == 0) {
                    mPath.moveTo(pointX, pointY);
                } else {
                    mPath.lineTo(pointX, pointY);
                }
                drawText(canvas, String.valueOf(chatBean.value).concat("℃"), pointX, pointY-lableHeight/2, textSize);
            }
            mPaint.setStrokeWidth(lineWidth);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeMiter(20);


            canvas.restore();
            canvas.drawPath(mPath, mPaint);

        }
    }

    /**
     * 绘制最下边的描述信息，并且返回文字高度
     *
     * @param canvas
     * @param s
     * @param pointX
     * @param textSize
     * @return
     */
    protected int drawTimeText(Canvas canvas, String s, int pointX, int textSize) {

        int baseY = mHeight - textRect.height() / 2;
        drawText(canvas, s, pointX, baseY, textSize);
        baseY -= mPaint.getFontSpacing();
        drawText(canvas, s, pointX, baseY, textSize);
        baseY -= mPaint.getFontSpacing();
        drawText(canvas, s, pointX, baseY, textSize);
        int y = baseY - getTextHeight(s, textSize);

        return mHeight - y;

    }

    protected void drawText(Canvas canvas, String text, int pointX, int pointY, int textSize) {

        mPaint.setTextSize(textSize);
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.CENTER);
        String s = String.valueOf(text);
        mPaint.getTextBounds(s, 0, s.length(), textRect);
        canvas.drawText(s, 0, s.length(), pointX, pointY, mPaint);
    }

    protected int getTextHeight(String text, int textSize) {
        mPaint.setTextSize(textSize);
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.getTextBounds(text, 0, text.length(), textRect);
        return textRect.height();
    }


    public static class LineChatBean {
        public int value;
        public String title;

        public LineChatBean(int maxValue) {
            this.value = maxValue;
        }
    }

}
