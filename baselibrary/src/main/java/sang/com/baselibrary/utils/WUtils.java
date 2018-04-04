package sang.com.baselibrary.utils;

import android.content.Context;

import java.util.Random;

/**
 * 作者： ${PING} on 2018/4/2.
 * 天气绘制的工具类
 */

public class WUtils {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 获取指定范围的随机数
     * @param min
     * @param max
     * @return
     */
    public static float randomFloat(float min,float max){
        Random random = new Random();
        if (min>=max){
            throw new ParamsError("max must greater than min");
        }
        return random.nextFloat()*(max-min) + min;
    }
    /**
     * 获取指定范围的随机数
     * @param min
     * @param max
     * @return
     */
    public static int randomInt(int min, int max){
        Random random = new Random();
        if (min>=max){
            throw new ParamsError("max must greater than min");
        }
        return random.nextInt(max-min) + min;
    }




}
