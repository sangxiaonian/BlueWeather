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
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

import java.util.ArrayList;
import java.util.List;

import sang.com.baselibrary.utils.JLog;
import sang.com.baselibrary.utils.WUtils;

/**
 * 作者： ${PING} on 2018/4/4.
 * <p>
 * 折线图
 */

public class LineChartView extends View implements GestureDetector.OnGestureListener {


    private Paint mPaint;
    private Path mPath;

    //    List<List<LineChatBean>> lineInfors;
    List<LineChatBean> lineInfors;

    private int maxY = 25;
    private int minY = 10;
    private int mHeight;
    private int mWidth;
    private int cellWidth;

    private int textSize;

    private Rect textRect;//文字测量使用

    private Point pointStart, pointEnd;
    private int lineWidth;
    private GestureDetector detector;//手势

    private Scroller mScroller;

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
        lineWidth = WUtils.dip2px(context, 1);
        mPaint.setStrokeWidth(lineWidth);
        mPath = new Path();
        lineInfors = new ArrayList<>();
        pointStart = new Point();
        pointEnd = new Point();
        textRect = new Rect();
        cellWidth = WUtils.dip2px(context, 80);
        textSize = WUtils.dip2px(context, 12);
        detector = new GestureDetector(context, this);
        mScroller = new Scroller(context);
        for (int i = 0; i < 7; i++) {
            lineInfors.add(new LineChatBean(WUtils.randomInt(minY, maxY)));
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
            canvas.save();
            canvas.clipRect(0, 0, mWidth, mHeight);
            drawLine(lineInfors, mPath, canvas);

            canvas.restore();
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return detector.onTouchEvent(event);
    }


    private void drawLine(List<LineChatBean> lineInfor, Path mPath, Canvas canvas) {
        if (lineInfor != null && !lineInfor.isEmpty()) {

            final int y = maxY - minY;//总共的温度范围
            if (y == 0) {
                return;
            }
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
                drawText(canvas, String.valueOf(chatBean.value).concat("℃"), pointX, pointY - lableHeight / 2, textSize);
            }
            mPaint.setStrokeWidth(lineWidth);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeMiter(20);
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
    private int drawTimeText(Canvas canvas, String s, int pointX, int textSize) {
        int baseY = mHeight - textRect.height() / 2;
        drawText(canvas, s, pointX, baseY, textSize);
        baseY -= mPaint.getFontSpacing();
        drawText(canvas, s, pointX, baseY, textSize);
        baseY -= mPaint.getFontSpacing();
        drawText(canvas, s, pointX, baseY, textSize);
        int y = baseY - getTextHeight(s, textSize);

        return mHeight - y;

    }

    private void drawText(Canvas canvas, String text, int pointX, int pointY, int textSize) {

        mPaint.setTextSize(textSize);
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.CENTER);
        String s = String.valueOf(text);
        mPaint.getTextBounds(s, 0, s.length(), textRect);

        JLog.i(text + ">>>>" + textRect.top);
        canvas.drawText(s, 0, s.length(), pointX, pointY, mPaint);
        JLog.d(text + ">>>>" + textRect.top);
    }

    private int getTextHeight(String text, int textSize) {
        mPaint.setTextSize(textSize);
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.getTextBounds(text, 0, text.length(), textRect);
        return textRect.height();
    }

    @Override
    public boolean onDown(MotionEvent e) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return true;
    }


    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        changePointStart(distanceX, distanceY);
        boolean result = true;
        if ((Math.abs(e1.getX() - e2.getX()) < Math.abs(Math.abs(e1.getY() - e2.getY()))) && Math.abs(e1.getY() - e2.getY()) > WUtils.getTouchSlop(getContext())) {
            result = false;
            getParent().requestDisallowInterceptTouchEvent(false);
        }
        return result;
    }

    private void changePointStart(float distanceX, float distanceY) {
        pointStart.x -= distanceX;
        pointStart.y -= distanceY;
        int maxLeft = mWidth - cellWidth * lineInfors.size();
        if (pointStart.x < maxLeft) {
            pointStart.x = maxLeft;
        }
        if (pointStart.x > 0) {
            pointStart.x = 0;
        }
        pointEnd.x = pointStart.x + mWidth;
        pointEnd.y = pointStart.y + mHeight;
        if (lineInfors != null && !lineInfors.isEmpty()) {
            postInvalidate();
        }
    }


    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return true;
    }

    private int lastX;
    private int lastY;

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            int currX = mScroller.getCurrX();
            int currY = mScroller.getCurrY();
            changePointStart(lastX - currX, lastY - currY);
            lastX = currX;
            lastY = currY;


        }
    }


    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public void onShowPress(MotionEvent e) {

    }


    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }


    public static class LineChatBean {
        public int value;
        public String title;

        public LineChatBean(int maxValue) {
            this.value = maxValue;
        }
    }

}
