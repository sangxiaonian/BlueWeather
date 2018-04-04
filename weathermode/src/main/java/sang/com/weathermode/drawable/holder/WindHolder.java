package sang.com.weathermode.drawable.holder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

import sang.com.baselibrary.utils.WUtils;

/**
 * 作者： ${PING} on 2018/4/3.
 */

public class WindHolder extends BasicWeatherHolder {

    private int windColor;
    private int lenth = 10;
    private Point centerPoint;
    private int radio;
    private boolean isNight;

    private int start;

    private RectF rectF;

    private int speech;

    public WindHolder(boolean isNight, float width, float height, Context mContext) {
        super(width, height, mContext);

        windColor = isNight ? 0x33ffffff : 0x66ffffff;

        changParams(height, mContext);
        start = WUtils.randomInt(0, 360);


    }

    private void changParams(float height, Context mContext) {
        startAlaph = (int) WUtils.randomFloat(50, 125);
        alaph = startAlaph;
        rectF = new RectF();
        lenth = WUtils.randomInt(WUtils.dip2px(mContext, lenth) / 2, WUtils.dip2px(mContext, lenth));
        centerPoint = new Point((int) width, WUtils.randomInt((int) -height / 2, 1));
        radio = WUtils.randomInt((int) height, (int) height * 2);
        rectF.left = centerPoint.x - radio / 2;
        rectF.top = centerPoint.y - radio / 2;
        rectF.right = centerPoint.x + radio / 2;
        rectF.bottom = centerPoint.y + radio / 2;
        speech = WUtils.randomInt(1, 3);
    }

    @Override
    public void excuse(Canvas canvas, Paint mPaint) {

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(windColor);

        canvas.save();
        canvas.clipRect(0,0,width,height);
        mPaint.setAlpha(alaph);
//canvas.drawRect(rectF,mPaint);
        canvas.drawArc(rectF, start, lenth, false, mPaint);
//        canvas.drawArc(rectF, start, 180, false, mPaint);

        canvas.restore();
        start -= speech;
        if (start < 0) {
            start = 360;
        }
    }
}
