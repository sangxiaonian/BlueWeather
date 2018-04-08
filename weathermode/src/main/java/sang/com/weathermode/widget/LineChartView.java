package sang.com.weathermode.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;

import sang.com.baselibrary.utils.WUtils;

/**
 * 作者： ${PING} on 2018/4/4.
 * <p>
 * 折线图
 */

public class LineChartView extends BaseLineChartView implements GestureDetector.OnGestureListener {


    private GestureDetector detector;//手势

    private ValueAnimator animator;

    public LineChartView(Context context) {
        this(context, null, 0);
    }

    public LineChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        super.initView(context, attrs, defStyleAttr);
        detector = new GestureDetector(context, this);
        animator = ValueAnimator.ofFloat();
        animator.setDuration(1000);
        animator.setInterpolator(new DecelerateInterpolator(10));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                pointStart.x = (int) animatedValue;
                checkDistance();
                postInvalidate();
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return detector.onTouchEvent(event);
    }


    @Override
    public boolean onDown(MotionEvent e) {
        animator.pause();
        animator.cancel();
        getParent().requestDisallowInterceptTouchEvent(true);
        return true;
    }


    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        changePointStart(distanceX, distanceY);
        boolean result = true;
        if ((Math.abs(e1.getX() - e2.getX()) < Math.abs(Math.abs(e1.getY() - e2.getY()))) && Math.abs(e1.getY() - e2.getY()) > WUtils.getTouchSlop(getContext())) {
            result = false;
            getParent().requestDisallowInterceptTouchEvent(false);
        }
        return result;
    }

    private void changePointStart(float distanceX, float distanceY) {
        pointStart.x -= distanceX;
        pointStart.y -= distanceY;
        checkDistance();
        pointEnd.x = pointStart.x + mWidth;
        pointEnd.y = pointStart.y + mHeight;
        if (lineInfors != null && !lineInfors.isEmpty()) {
            invalidate();
        }
    }


    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        int start = pointStart.x;
        int maxLength;
        if (velocityX > 0) {
            maxLength = Math.abs(start);
        } else {
            maxLength = Math.abs(start) - Math.abs(getMaxLeft());
        }
        maxLength = (int) (Math.abs(velocityX * 1.0f / 20000) * maxLength);
        animator.setFloatValues(start, start + maxLength);
        animator.start();
        return false;
    }


    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public void onShowPress(MotionEvent e) {

    }


    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }


}
