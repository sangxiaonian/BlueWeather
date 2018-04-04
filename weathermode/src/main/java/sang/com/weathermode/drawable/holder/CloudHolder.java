package sang.com.weathermode.drawable.holder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import sang.com.weathermode.R;
import sang.com.baselibrary.utils.WUtils;

/**
 * 作者： ${PING} on 2018/4/3.
 * 多云
 */

public class CloudHolder extends BasicWeatherHolder {


    private Drawable drawable;
    int[] cloudId;

    public CloudHolder(float width, float height, Context mContext) {
        super(width, height, mContext);
        startAlaph = (int) WUtils.randomFloat(20, 150);
        alaph = startAlaph;
        cloudId = new int[]{R.drawable.weather_cloud1
                , R.drawable.weather_cloud2
                , R.drawable.weather_cloud4
                , R.drawable.weather_cloud5
                , R.drawable.weather_cloud3};
        drawable = creatDrawable(cloudId[(int) WUtils.randomInt(0, 4)]);

    }

    @Override
    public void excuse(Canvas canvas, Paint mPaint) {

        canvas.save();
        move(drawable);
        drawable.setAlpha(alaph);
        drawable.draw(canvas);
        if (drawable.getBounds().right <= 0) {
            setBounds(drawable, (int) width);
        }
        canvas.restore();
    }


    private Drawable creatDrawable(int id) {

        Drawable drawable = mContext.getResources().getDrawable(id);
        setBounds(drawable, 0);
        return drawable;
    }


    private void setBounds(Drawable drawable, int left) {
        int size = (int) WUtils.randomFloat(width, width * 3 / 2);
        int x = (int) WUtils.randomFloat(left, width + 1);
        float v = height - size / 2;
        v = v > 0 ? v :  WUtils.randomFloat(0, height);
        int y = (int) WUtils.randomFloat(0, v);
        drawable.setBounds(x, y, x + size, y + size / 2);
    }

    private void move(Drawable drawable) {
        Rect bounds = drawable.getBounds();
        drawable.setBounds(bounds.left - 1, bounds.top, bounds.right - 1, bounds.bottom);
    }


}
