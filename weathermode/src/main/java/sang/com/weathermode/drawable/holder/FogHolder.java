package sang.com.weathermode.drawable.holder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import sang.com.weathermode.drawable.WeatherConver;
import sang.com.baselibrary.utils.WUtils;


/**
 * 作者： ${PING} on 2018/4/2.
 * 大雾天气
 */

public class FogHolder extends BasicWeatherHolder {

    private   GradientDrawable drawable;
    private int lenth = 5;
    private int speach=4;

    public FogHolder(boolean isNight,float width, float height, Context mContext) {
        super(width, height, mContext);
        startAlaph = (int) WUtils.randomFloat(50, 255);
        alaph = startAlaph;
        loaction = new Point((int) WUtils.randomFloat(0, this.width), (int) WUtils.randomFloat(0, height));
        lenth = (int) WUtils.randomFloat(WUtils.dip2px(mContext, lenth) / 2, WUtils.dip2px(mContext, lenth));

        speach = (int) WUtils.randomFloat(WUtils.dip2px(mContext, speach) / 2, WUtils.dip2px(mContext, speach));


        drawable = new GradientDrawable(GradientDrawable.Orientation.BL_TR,
                isNight ? new int[]{Color.WHITE, WeatherConver.SkyBackground.FOG_N[0]} : new int[]{Color.WHITE, WeatherConver.SkyBackground.FOG_D[0]});//d4ba3f
        drawable.setShape(GradientDrawable.OVAL);
        drawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        drawable.setBounds(loaction.x, loaction.y, loaction.x + lenth, loaction.y + lenth);
    }

    @Override
    public void excuse(Canvas canvas, Paint mPaint) {
        if (loaction == null || alaph == 0) {
            return;
        }

        canvas.save();
        drawable.setAlpha(alaph);
        drawable.draw(canvas);
        canvas.restore();
        move(drawable);

    }



    private void move(Drawable drawable) {
        Rect bounds = drawable.getBounds();
        if (bounds.left>width){
            bounds.left=-speach*2;
            bounds.right= lenth-speach*2;
        }
        drawable.setBounds(bounds.left + speach, bounds.top, bounds.right + speach, bounds.bottom);
    }
}
