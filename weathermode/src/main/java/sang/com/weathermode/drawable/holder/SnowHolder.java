package sang.com.weathermode.drawable.holder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.VectorDrawable;

import sang.com.weathermode.R;
import sang.com.baselibrary.utils.WUtils;

/**
 * 作者： ${PING} on 2018/4/2.
 * <p>
 * 绘制雪花
 */

public class SnowHolder extends BasicWeatherHolder {


    private final VectorDrawable drawable;

    /**
     * 雨滴长度
     */

    public int lenth = 20;

    public int speach = 2;


    public SnowHolder(float width, float height, Context mContext) {
        super(width, height, mContext);
        startAlaph = (int) WUtils.randomFloat(50, 255);
        alaph = startAlaph;
        loaction = new Point((int) WUtils.randomFloat(0, this.width), (int) WUtils.randomFloat(-height, height));
        lenth = (int) WUtils.randomFloat(WUtils.dip2px(mContext, lenth) / 2, WUtils.dip2px(mContext, lenth));
        speach = WUtils.dip2px(mContext, speach);
        speach = (int) WUtils.randomFloat(speach * 2 / 3, speach);
        drawable = (VectorDrawable) mContext.getResources().getDrawable(R.drawable.weather_snow);
    }


    public void excuse(Canvas canvas, Paint mPaint) {
        if (loaction == null) {
            return;
        }mPaint.setStyle(Paint.Style.FILL);
        canvas.save();
        drawable.setBounds(loaction.x, loaction.y, loaction.x + lenth, loaction.y + lenth);
        drawable.draw(canvas);
        drawable.setAlpha(alaph);
        canvas.restore();
        loaction.y += speach;
        if (loaction.y >= height) {
            loaction.y = -lenth;
            loaction.x = (int) WUtils.randomFloat(0, width);
            speach = (int) WUtils.randomFloat(speach * 2 / 3, speach);
        }

    }


}
