package sang.com.weathermode.drawable.holder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * 作者： ${PING} on 2018/4/2.
 */

public abstract class BasicWeatherHolder {
    protected float width;//天气控件宽高
    protected float height;//天气控件宽高
    protected Context mContext;
    /**
     * 装饰品位置
     */
    public Point loaction;//装饰品的位置

    protected int startAlaph=255;

    /**
     * 装饰品透明度
     */
    public int alaph = 255;

    /**
     * 动画转变过程的透明度(0-1)
     */
    protected float animatedFraction = 1;

    public BasicWeatherHolder(float width, float height, Context mContext) {
        this.width = width;
        this.height = height;
        this.mContext = mContext;
    }

    public abstract void excuse(Canvas canvas, Paint mPaint);

    public void setAnimatedFraction(float animatedFraction) {
        this.animatedFraction = animatedFraction;
        alaph= (int) (startAlaph*animatedFraction);
    }
}
