package sang.com.weathermode.drawable.holder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import sang.com.weathermode.R;

/**
 * 作者： ${PING} on 2018/4/3.
 * 晴天，阳光
 */

public class SunnyHolder extends BasicWeatherHolder {


    private final  Drawable drawable;

    private int roate;

    public SunnyHolder(float width, float height, Context mContext) {
        super(width, height, mContext);
        startAlaph = 20;
        alaph = 20;
        drawable = mContext.getResources().getDrawable(R.drawable.weather_sun);
        int size = (int) (width * 4 / 5);
        drawable.setBounds(0,0, size, size);
    }

    @Override
    public void excuse(Canvas canvas, Paint mPaint) {
        mPaint.setColor(Color.WHITE);
        mPaint.setAlpha(alaph);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.save();
        canvas.drawCircle(0, 0, width * 4 / 5, mPaint);
        canvas.drawCircle(0, 0, width * 3 / 5, mPaint);
        canvas.drawCircle(0, 0, width * 2 / 5, mPaint);
        drawable.setAlpha((int) (255*animatedFraction));
        drawable.draw(canvas);
        drawable.setLevel(roate);
        roate++;
        canvas.restore();
    }
}
