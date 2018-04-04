package sang.com.weathermode.drawable;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import sang.com.weathermode.weatherutils.ColorAnimation;

/**
 * 作者： ${PING} on 2018/3/30.
 */

public class WeatherDrawable {


    /**
     * 背景drawable
     */
    protected GradientDrawable skyBgDrawable;
    protected boolean isNight;
    protected Context mContext;

    private ValueAnimator valueAnimator;

    private onValueChangesListener listener;


    public WeatherDrawable(boolean isNight, Context mContext) {
        this.isNight = isNight;
        this.mContext = mContext;
        init();

    }

    private void init() {
        if (skyBgDrawable == null) {
            skyBgDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, getSkyBackColor());
        }
        valueAnimator = ValueAnimator.ofObject(new ColorAnimation.ColorsTypeEvaluator(), WeatherConver.SkyBackground.CLEAR_D.clone(), WeatherConver.SkyBackground.CLEAR_D.clone());
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setColors((Integer[]) animation.getAnimatedValue());
                if (listener != null) {
                    listener.onValueChanges(animation.getAnimatedFraction());
                }
            }
        });

    }

    public Drawable getDrawable() {
        return skyBgDrawable;
    }

    public WeatherDrawable setSize(int width, int height) {
        return setSize(0, 0, width, height);
    }

    private WeatherDrawable setSize(int left, int top, int ringt, int bottom) {
        skyBgDrawable.setBounds(left, top, ringt, bottom);
        return this;
    }


    public WeatherDrawable setAlpha(int alpha) {
        skyBgDrawable.setAlpha(alpha);
        return this;
    }

    public WeatherDrawable setColors(Integer[] colors) {
        int[] color = new int[colors.length];
        for (int i = 0; i < color.length; i++) {
            color[i] = colors[i];
        }
        skyBgDrawable.setColors(color);
        return this;
    }


    public int[] getSkyBackColor() {
        Integer[] integers = isNight ? WeatherConver.SkyBackground.CLEAR_N : WeatherConver.SkyBackground.CLEAR_D;
        int[] colors = new int[2];
        colors[0] = integers[0];
        colors[1] = integers[1];
        return colors;
    }

    public void draw(Canvas mCanvas) {
        if (skyBgDrawable != null) {
            skyBgDrawable.draw(mCanvas);
        }
    }

    public void changToColorsWithAni(Integer[] endColors) {
        Integer[] values = (Integer[]) valueAnimator.getAnimatedValue();
        if (values == null) {
            values = WeatherConver.SkyBackground.CLEAR_D.clone();
        }
        valueAnimator.setObjectValues(values.clone(), endColors.clone());
        valueAnimator.start();
    }


    public void setListener(onValueChangesListener listener) {
        this.listener = listener;
    }

    public interface onValueChangesListener {
        void onValueChanges(float animatedFraction);
    }


}
