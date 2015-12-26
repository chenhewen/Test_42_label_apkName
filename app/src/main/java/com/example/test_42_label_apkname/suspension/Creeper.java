package com.example.test_42_label_apkname.suspension;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.example.test_42_label_apkname.R;
import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by chenhewen on 12/25/15.
 */
public class Creeper extends FrameLayout {

    public static final String TAG = "Creeper";

    public static final int LIGHT_ALPHA = 255;
    public static final int DIM_ALPHA = LIGHT_ALPHA / 2;
    private int mBackgroundAlpha;
    private GestureDetector mGestureDetector;
    private CreeperManager.OnMoreGestureListener mMoreGestureListener;
    private Paint mPaint;
    private Drawable mDrawable;

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
        setWillNotDraw(false);
        mBackgroundAlpha = LIGHT_ALPHA;
        mDrawable = context.getResources().getDrawable(R.drawable.ic_launcher);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        /*canvas.saveLayerAlpha(0, 0, getWidth(), getHeight(), mBackgroundAlpha,
                Canvas.ALL_SAVE_FLAG);*/
        mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
        mDrawable.setAlpha(mBackgroundAlpha);
        mDrawable.draw(canvas);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP && mMoreGestureListener != null) {
            mMoreGestureListener.onUp(event);
        }

        return mGestureDetector.onTouchEvent(event);
    }

    public void startAlphaAnim(int startAlpha, int endAlpha, long duration) {

        if (duration <= 0) {
            mBackgroundAlpha = endAlpha;
            invalidate();
            return;
        }

        ValueAnimator v = ValueAnimator.ofInt(startAlpha, endAlpha);
        v.setDuration(duration);
        v.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mBackgroundAlpha = (Integer) valueAnimator.getAnimatedValue();
                Log.d(TAG, "background alpha: " + mBackgroundAlpha);
                invalidate();
            }
        });
        v.start();
    }

    public void setGestureListeners(CreeperManager.OnMoreGestureListener gestureListener, GestureDetector.OnDoubleTapListener doubleTapListener) {
        mMoreGestureListener = gestureListener;
        mGestureDetector = new GestureDetector(getContext(), gestureListener);
        mGestureDetector.setOnDoubleTapListener(doubleTapListener);
    }
}
