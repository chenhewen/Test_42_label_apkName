package com.example.test_42_label_apkname.suspension;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.test_42_label_apkname.GlobalHandler;
import com.example.test_42_label_apkname.util.DrawUtils;
import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by chenhewen on 12/25/15.
 */
public class CreeperManager {

    private static final String TAG = "CreeperManager";

    private static final long DIM_DELAY_MILLIS = 5000;

    private Context mContext;

    private static CreeperManager sInstance;

    private int mViewFlags = CreeperState.GONE;

    private Creeper mCreeperView;

    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mWindowParams;
    private WindowManager.LayoutParams mBackupWindowParams;


    private CreeperManager(Context context) {
        mContext = context;
    }

    public static CreeperManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new CreeperManager(context);
        }

        return sInstance;
    }

    private static class CreeperState {

        private static final int GONE = 0x1;

        private static final int VISIBLE_LIGHT = 0x1 << 1;

        private static final int VISIBLE_DIM = 0x1 << 2;

        private static final int ANIM_DIM_TO_LIGHT = 0x1 << 3;

        private static final int ANIM_LIGHT_TO_DIM = 0x1 << 4;
    }

    public boolean create() {
        if ((mViewFlags & CreeperState.GONE) != 0) {
            mViewFlags = CreeperState.VISIBLE_LIGHT;
            initWindowParam();
            mCreeperView = new Creeper(mContext);
            mCreeperView.setGestureListeners(new OnMoreGestureListener(), mOnDoubleTapListener);
            mWindowManager.addView(mCreeperView, mWindowParams);

            DrawUtils.resetForce(mContext);
            return true;
        }

        return false;
    }

    public boolean dim() {
        if ((mViewFlags & CreeperState.VISIBLE_LIGHT) != 0) {
            mViewFlags = CreeperState.VISIBLE_DIM;
            mCreeperView.startAlphaAnim(Creeper.LIGHT_ALPHA, Creeper.DIM_ALPHA, 1000);
            return true;
        }

        return false;
    }

    private Runnable mDimTask = new Runnable() {

        @Override
        public void run() {
            dim();
        }
    };

    public boolean dimDelay() {
        GlobalHandler.postInMainThread(mDimTask, DIM_DELAY_MILLIS);

        return true;
    }

    public boolean light() {
        mViewFlags = CreeperState.VISIBLE_LIGHT;
        mCreeperView.startAlphaAnim(Creeper.DIM_ALPHA, Creeper.LIGHT_ALPHA, 0);
        GlobalHandler.cancelMainThreadPost(mDimTask);
        return true;
    }

    public boolean destroy() {
        if ((mViewFlags & CreeperState.GONE) == 0) {
            Log.d(TAG, "destroy");
            mViewFlags = CreeperState.GONE;
            mWindowManager.removeView(mCreeperView);
            mCreeperView = null;
            mWindowManager = null;
            mWindowParams = null;
            GlobalHandler.cancelMainThreadPost(mDimTask);
            return true;
        }

        return false;
    }

    private static final int CREEP_DIRECTION_LEFT = 1;
    private static final int CREEP_DIRECTION_TOP = 2;
    private static final int CREEP_DIRECTION_RIGHT = 3;
    private static final int CREEP_DIRECTION_BOTTOM = 4;

    private int creepToWall() {
        int direction = getCreepDirection();
        //Log.d(TAG, "creepToWall direction: " + direction);

        int destX = mWindowParams.x;
        int destY = mWindowParams.y;

        int screenWidth = DrawUtils.sWidthPixels;
        int screenHeight = DrawUtils.sHeightPixels;

        if (direction == CREEP_DIRECTION_LEFT) {
            destX = 0;
        } else if (direction == CREEP_DIRECTION_TOP) {
            destY = 0;
        } else if (direction == CREEP_DIRECTION_RIGHT) {
            destX = screenWidth - mCreeperView.getWidth();
        } else {
            destY = screenHeight - mCreeperView.getHeight();
        }

        final int finalX = destX;
        final int finalY = destY;

        mBackupWindowParams.copyFrom(mWindowParams);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(300);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (Float) valueAnimator.getAnimatedValue();
                mWindowParams.x = (int) (mBackupWindowParams.x + (finalX - mBackupWindowParams.x) * animatedValue);
                mWindowParams.y = (int) (mBackupWindowParams.y + (finalY - mBackupWindowParams.y) * animatedValue);
                updateWindowParams();
            }
        });
        valueAnimator.start();

        return  direction;
    }

    private int getCreepDirection() {
        int cx = mWindowParams.x + mCreeperView.getWidth() / 2;
        int cy = mWindowParams.y + mCreeperView.getHeight() / 2;

        int screenWidth = DrawUtils.sWidthPixels;
        int screenHeight = DrawUtils.sHeightPixels;

        int left = cx;
        int top = cy;
        int right = screenWidth - cx;
        int bottom = screenHeight - cy;

        int min = Math.min(Math.min(left, right), Math.min(top, bottom));

        //Log.d(TAG, "screenWidth: " + screenWidth + " screenHeight: " + screenHeight);
        //Log.d(TAG, "left: " + left + " top: " + top + " right: " + right +  " bottom: " + bottom);

        if (min == left) return CREEP_DIRECTION_LEFT;
        if (min == top) return CREEP_DIRECTION_TOP;
        if (min == right) return CREEP_DIRECTION_RIGHT;
        if (min == bottom) return CREEP_DIRECTION_BOTTOM;

        return 0;
    }

    private void initWindowParam() {
        int width = DrawUtils.dip2px(150);
        int height = DrawUtils.dip2px(150);

        mWindowManager = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        mWindowParams = new WindowManager.LayoutParams();
        mWindowParams.alpha = 1.0f;
        mWindowParams.width = width;
        mWindowParams.height = height;
        mWindowParams.x = 0;
        mWindowParams.y = 0;
        mWindowParams.gravity = Gravity.LEFT | Gravity.TOP;
        mWindowParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED;
        mWindowParams.format = PixelFormat.TRANSLUCENT;
        mWindowParams.type = WindowManager.LayoutParams.TYPE_PHONE;
    }

    private void updateWindowParams() {
        mWindowManager.updateViewLayout(mCreeperView, mWindowParams);
    }

    public class OnMoreGestureListener implements GestureDetector.OnGestureListener {
        @Override
        public boolean onDown(MotionEvent motionEvent) {
            mViewFlags = CreeperState.ANIM_DIM_TO_LIGHT;
            light();
            mBackupWindowParams = new WindowManager.LayoutParams();
            mBackupWindowParams.copyFrom(mWindowParams);
            Log.d(TAG, "onDown");
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            Log.d(TAG, "singleTapUp");
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            mWindowParams.x = (int) (motionEvent1.getRawX() - (motionEvent.getRawX() - mBackupWindowParams.x));
            mWindowParams.y = (int) (motionEvent1.getRawY() - (motionEvent.getRawY() - mBackupWindowParams.y));
            //Log.d(TAG, "downX: " + motionEvent.getRawX() + " lastX: " + motionEvent1.getRawX() + " backupX: " + mBackupWindowParams.x);
            updateWindowParams();
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            Log.d(TAG, "onFling");
            return false;
        }

        public boolean onUp(MotionEvent motionEvent) {
            Log.d(TAG, "onUp");
            creepToWall();
            dimDelay();
            return false;
        }
    }

    private GestureDetector.OnDoubleTapListener mOnDoubleTapListener = new GestureDetector.OnDoubleTapListener() {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            return false;
        }
    };
}
