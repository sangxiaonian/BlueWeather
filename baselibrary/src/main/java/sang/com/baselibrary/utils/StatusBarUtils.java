package sang.com.baselibrary.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.lang.reflect.Field;

/**
 * 作者： ${PING} on 2018/4/4.
 */

public class StatusBarUtils {
    /**
     * 使状态栏透明
     * <p>
     * 适用于图片作为背景的界面,此时需要图片填充到状态栏
     *
     * @param activity 需要设置的activity
     */
    public static void setTranslucent(AppCompatActivity activity) {
        setTranslucent(activity,false);
    }
    /**
     * 使状态栏透明
     * <p>
     * 适用于图片作为背景的界面,此时需要图片填充到状态栏
     *
     * @param activity 需要设置的activity
     */
    public static void setTranslucent(AppCompatActivity activity,boolean hideactionbar) {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().setNavigationBarColor(Color.TRANSPARENT);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);

        }
        if (hideactionbar) {
            ActionBar actionBar =activity.getSupportActionBar();
            if (actionBar!=null) {
                actionBar.hide();
            }
        }
    }

    /** 设置控件的paddingTop, 使它不被StatusBar覆盖 */
    public static void setStatusBarPadding(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int marginTop = getStatusBarHeight(view.getContext());
            view.setPadding(view.getPaddingLeft(), marginTop,
                    view.getPaddingRight(), view.getPaddingBottom());
            return;
        }
    }
    /**
     * 通过反射的方式获取状态栏高度，
     * 一般为24dp，有些可能较特殊，所以需要反射动态获取
     */
    public static int getStatusBarHeight(Context context) {
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object obj = clazz.newInstance();
            Field field = clazz.getField("status_bar_height");
            int id = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelSize(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------无法获取到状态栏高度");
        }
        return WUtils.dip2px(context,24);
    }

}
