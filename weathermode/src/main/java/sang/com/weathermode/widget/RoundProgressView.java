package sang.com.weathermode.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

import sang.com.baselibrary.utils.JLog;
import sang.com.baselibrary.utils.WUtils;

/**
 * 作者： ${PING} on 2018/4/9.
 */

public class RoundProgressView extends View {

    private int unDoColor, doColor;
    private int max, min, current;
    private int mWidth, mHeight;

    private int angle;

    private int prWidth;

    private Paint mPaint;

    private Rect textRect;
    private RectF ovel;

    private Point minPoint, maxPoint;

    private int textSize;

    private int gap;
    private String centerDes;


    public RoundProgressView(Context context) {
        this(context, null, 0);
    }

    public RoundProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        unDoColor = 0x55ffffff;
        doColor = 0xaaffffff;

        prWidth = WUtils.dip2px(context, 10);
        ovel = new RectF();
        angle = 120;

        max = 100;
        min = 0;
        current = 75;

        gap = WUtils.dip2px(context, 10);
        textSize = WUtils.dip2px(context, 18);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(prWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        ovel = new RectF();
        textRect = new Rect();
        maxPoint = new Point();
        minPoint = new Point();

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        ovel.left = prWidth / 2;
        ovel.top = prWidth / 2;
        ovel.right = mWidth - prWidth / 2;
        ovel.bottom = mHeight - prWidth / 2;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(textSize);
        mPaint.getTextBounds(String.valueOf(max), 0, String.valueOf(max).length(), textRect);


        ovel.left = prWidth / 2 + textRect.height() + gap;
        ovel.top = prWidth / 2 + textRect.height() + gap;
        ovel.right = mWidth - prWidth / 2 - textRect.height() - gap;
        ovel.bottom = mHeight - prWidth / 2 - textRect.height() - gap;


        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(prWidth);
        mPaint.setColor(unDoColor);
        canvas.drawArc(ovel, angle, 300, false, mPaint);
        mPaint.setColor(doColor);
        canvas.drawArc(ovel, angle, 300 * current * 1.0f / (max - min), false, mPaint);


        minPoint.x = (int) (ovel.centerX() - Math.sin((angle - 90) * Math.PI / 180) * Math.min(ovel.width(), ovel.height()) / 2);
        maxPoint.x = (int) (ovel.centerX() + Math.sin((angle - 90) * Math.PI / 180) * Math.min(ovel.width(), ovel.height()) / 2);
        maxPoint.y = (int) (ovel.centerY() + Math.cos((angle - 90) * Math.PI / 180) * Math.min(ovel.width(), ovel.height()) / 2);
        minPoint.y = (int) (ovel.centerY() + Math.cos((angle - 90) * Math.PI / 180) * Math.min(ovel.width(), ovel.height()) / 2);


        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(textSize);
        mPaint.setColor(unDoColor);
        mPaint.getTextBounds(String.valueOf(max), 0, String.valueOf(max).length(), textRect);
        mPaint.setStrokeWidth(1);
        mPaint.setTextAlign(Paint.Align.CENTER);
        int height = minPoint.y + textRect.height() + prWidth / 2 + gap;
        canvas.drawText(String.valueOf(min), 0, String.valueOf(min).length(), minPoint.x, height, mPaint);
        canvas.drawText(String.valueOf(max), 0, String.valueOf(max).length(), maxPoint.x, height, mPaint);

        if (!TextUtils.isEmpty(centerDes)) {
            mPaint.setColor(doColor);
            mPaint.setTextSize(textSize * 2);
            mPaint.getTextBounds(String.valueOf(max), 0, String.valueOf(max).length(), textRect);
            canvas.drawText(String.valueOf(centerDes), 0, String.valueOf(centerDes).length(), ovel.centerX(), ovel.centerY() + textRect.height() / 2, mPaint);
        }
    }


    public void setCurrent(int current) {
        this.current = current;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setMin(int min) {
        this.min = min;
        invalidate();
    }

    public void setCenterDes(String centerDes) {
        this.centerDes = centerDes;
    }


}
