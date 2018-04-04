package sang.com.weathermode.drawable.holder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import sang.com.baselibrary.utils.WUtils;

/**
 * 作者： ${PING} on 2018/4/2.
 * <p>
 * 绘制雨滴
 */

public class RainHolder extends BasicWeatherHolder{




    /**
     * 雨滴长度
     */

    public int lenth = 10;

    public int speach = (int) WUtils.randomFloat(40, 50);

    public RainHolder(float width, float height, Context mContext) {
        super(width, height, mContext);
        startAlaph = (int) WUtils.randomFloat(50, 180);
        alaph = startAlaph;
        loaction = new Point((int) WUtils.randomFloat(0, this.width), (int) WUtils.randomFloat(-height, height));
    }


    public void excuse(Canvas canvas, Paint mPaint) {
        if (loaction == null) {
            return;
        }
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
        mPaint.setAlpha(alaph);
        mPaint.setStrokeWidth(WUtils.dip2px(mContext, 2));
        canvas.save();
        canvas.drawLine(loaction.x, loaction.y, loaction.x, loaction.y + WUtils.dip2px(mContext, lenth), mPaint);
        canvas.restore();
        loaction.y += speach;
        if (loaction.y >= height) {
            loaction.y = 0;
            loaction.x = (int) WUtils.randomFloat(0, width);
            speach = (int) WUtils.randomFloat(20, 30);
        }
    }

}
