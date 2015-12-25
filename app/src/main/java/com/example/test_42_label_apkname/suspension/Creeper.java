package com.example.test_42_label_apkname.suspension;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by chenhewen on 12/25/15.
 */
public class Creeper extends FrameLayout {

    private static final int LIGHT_ALPHA = 255;

    private static final int DIM_ALPHA = LIGHT_ALPHA / 2 ;

    private int mBackgroundAlpha;

    private GestureDetector mGestureDetector;

    public Creeper(Context context) {
        super(context);
        init(context);
    }

    public Creeper(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Creeper(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        setBackgroundColor(0xff0000ff);
        mGestureDetector = new GestureDetector(context, mOnGestureListener);
        mGestureDetector.setOnDoubleTapListener(mOnDoubleTapListener);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.saveLayerAlpha(0, 0, getWidth(), getHeight(), mBackgroundAlpha,
                Canvas.ALL_SAVE_FLAG);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return mGestureDetector.onTouchEvent(event);

    }

    private GestureDetector.OnGestureListener mOnGestureListener = new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    };

    private GestureDetector.OnDoubleTapListener mOnDoubleTapListener = new GestureDetector.OnDoubleTapListener() {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return false;
        }
    };

    public void startDimAnim() {
        ValueAnimator v = ValueAnimator.ofInt(LIGHT_ALPHA, DIM_ALPHA);
        v.setDuration(1000);
        v.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mBackgroundAlpha = (Integer) valueAnimator.getAnimatedValue();
            }
        });
        v.start();
        invalidate();
    }

    public void startLightAnim() {
        ValueAnimator v = ValueAnimator.ofInt(DIM_ALPHA, LIGHT_ALPHA);
        v.setDuration(1000);
        v.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mBackgroundAlpha = (Integer) valueAnimator.getAnimatedValue();
            }
        });
        v.start();
        invalidate();
    }
}
