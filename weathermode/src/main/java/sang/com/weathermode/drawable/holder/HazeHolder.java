package sang.com.weathermode.drawable.holder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import sang.com.baselibrary.utils.WUtils;


/**
 * 作者： ${PING} on 2018/4/2.
 * 雾霾
 */

public class HazeHolder extends BasicWeatherHolder {

    private final GradientDrawable drawable;
    private   int speach=4;
    private int lenth = 5;

    public HazeHolder(boolean isNight,float width, float height, Context mContext) {
        super(width, height, mContext);
        startAlaph = (int) WUtils.randomFloat(50, 255);
        alaph = startAlaph;
        speach = (int) WUtils.randomFloat(WUtils.dip2px(mContext, speach) / 2, WUtils.dip2px(mContext, speach));


        loaction = new Point((int) WUtils.randomFloat(0, this.width), (int) WUtils.randomFloat(0, height));
        lenth = (int) WUtils.randomFloat(WUtils.dip2px(mContext, lenth) / 2, WUtils.dip2px(mContext, lenth));
        drawable = new GradientDrawable(GradientDrawable.Orientation.BL_TR,
                isNight ? new int[]{0x55d4ba3f, 0x22d4ba3f} : new int[]{0x88cca667, 0x33cca667});//d4ba3f
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
