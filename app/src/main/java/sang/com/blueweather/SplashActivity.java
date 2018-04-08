package sang.com.blueweather;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import sang.com.baselibrary.base.BaseActivity;
import sang.com.weathermode.activity.WeatherMainActivity;

public class SplashActivity extends BaseActivity {


    private ValueAnimator animator;
    private View img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.img);
        img.setAlpha(0);
        animator = ValueAnimator.ofFloat(0, 1, 0);
//        animator.setInterpolator(new BreatheInterpolator());
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                img.setAlpha((Float) animation.getAnimatedValue());
            }
        });
        animator.addListener(new Animator.AnimatorListener() {


            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(new Intent(SplashActivity.this, WeatherMainActivity.class));
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }


            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        animator.start();

    }
}
