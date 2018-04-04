package sang.com.weathermode.weatherutils;

import android.animation.ArgbEvaluator;
import android.animation.TypeEvaluator;
import android.system.ErrnoException;

/**
 * 作者： ${PING} on 2018/3/30.
 */

public class ColorAnimation {


    public static  class  ColorsTypeEvaluator  implements TypeEvaluator<Integer[]>  {
        Integer[] evaluate = new Integer[2];
        @Override
        public Integer[] evaluate(float fraction, Integer[] startValue, Integer[] endValue) {
            for (int i = 0; i < startValue.length; i++) {
                evaluate[i]=argbevaluate(fraction,startValue[i],endValue[i]);
            }
            return evaluate;
        }



        /**
         * 色值计算
         * @param fraction
         * @param startValue
         * @param endValue
         * @return
         */
        public int argbevaluate(float fraction, Integer startValue, Integer endValue) {
            int startInt = (Integer) startValue;
            float startA = ((startInt >> 24) & 0xff) / 255.0f;
            float startR = ((startInt >> 16) & 0xff) / 255.0f;
            float startG = ((startInt >>  8) & 0xff) / 255.0f;
            float startB = ( startInt        & 0xff) / 255.0f;

            int endInt = (Integer) endValue;
            float endA = ((endInt >> 24) & 0xff) / 255.0f;
            float endR = ((endInt >> 16) & 0xff) / 255.0f;
            float endG = ((endInt >>  8) & 0xff) / 255.0f;
            float endB = ( endInt        & 0xff) / 255.0f;

            // convert from sRGB to linear
            startR = (float) Math.pow(startR, 2.2);
            startG = (float) Math.pow(startG, 2.2);
            startB = (float) Math.pow(startB, 2.2);

            endR = (float) Math.pow(endR, 2.2);
            endG = (float) Math.pow(endG, 2.2);
            endB = (float) Math.pow(endB, 2.2);

            // compute the interpolated color in linear space
            float a = startA + fraction * (endA - startA);
            float r = startR + fraction * (endR - startR);
            float g = startG + fraction * (endG - startG);
            float b = startB + fraction * (endB - startB);

            // convert back to sRGB in the [0..255] range
            a = a * 255.0f;
            r = (float) Math.pow(r, 1.0 / 2.2) * 255.0f;
            g = (float) Math.pow(g, 1.0 / 2.2) * 255.0f;
            b = (float) Math.pow(b, 1.0 / 2.2) * 255.0f;

            return Math.round(a) << 24 | Math.round(r) << 16 | Math.round(g) << 8 | Math.round(b);
        }




    }



}
