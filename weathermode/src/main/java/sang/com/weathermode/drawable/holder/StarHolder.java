package sang.com.weathermode.drawable.holder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.graphics.Shader;

import sang.com.baselibrary.utils.WUtils;


/**
 * 作者： ${PING} on 2018/4/2.
 * 星星
 */

public class StarHolder extends BasicWeatherHolder {

    private RadialGradient radialGradient;
    private int lenth = 5;
    private int speach;

    public StarHolder(float width, float height, Context mContext) {
        super(width, height, mContext);
        startAlaph = (int) WUtils.randomFloat(50, 255);
        alaph = startAlaph;
        loaction = new Point((int) WUtils.randomFloat(0, this.width), (int) WUtils.randomFloat(0, height));
        lenth = (int) WUtils.randomFloat(WUtils.dip2px(mContext, lenth) / 2, WUtils.dip2px(mContext, lenth));

        radialGradient = new RadialGradient(loaction.x + lenth / 2, loaction.y + lenth / 2, lenth / 2, Color.WHITE, Color.TRANSPARENT, Shader.TileMode.CLAMP);
        speach = (int) WUtils.randomFloat(0, 360);
    }

    @Override
    public void excuse(Canvas canvas, Paint mPaint) {
        if (loaction == null || alaph == 0) {
            return;
        }
        mPaint.setStyle(Paint.Style.FILL);
        speach = speach >= 360 ? 0 : (speach + 2);
        mPaint.setColor(Color.WHITE);
        double v1 = Math.sin(speach * Math.PI / 180) / 2 + 0.5f;
        int v = (int) (alaph * (v1));
        mPaint.setAlpha(v);
        mPaint.setShader(radialGradient);
        canvas.save();
        canvas.drawCircle(loaction.x + lenth / 2, loaction.y + lenth / 2, lenth / 2, mPaint);
        canvas.restore();
        mPaint.setShader(null);

    }
}
