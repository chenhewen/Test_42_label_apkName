package com.example.test_42_label_apkname.suspension;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;

import com.example.test_42_label_apkname.util.DrawUtils;

/**
 * Created by chenhewen on 12/25/15.
 */
public class CreeperManager {

    private Context mContext;

    private static CreeperManager sInstance;

    private int mViewFlags = CreeperState.GONE;

    private Creeper mCreeperView;

    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mWindowParams;


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
    }

    public boolean create() {
        if ((mViewFlags & CreeperState.GONE) != 0) {
            mViewFlags = CreeperState.VISIBLE_LIGHT;
            initWindowParam();
            mCreeperView = new Creeper(mContext);
            mWindowManager.addView(mCreeperView, mWindowParams);
            return true;
        }

        return false;
    }

    public boolean dim() {
        if ((mViewFlags & CreeperState.VISIBLE_LIGHT) != 0) {
            mViewFlags = CreeperState.VISIBLE_DIM;
            mCreeperView.startDimAnim();
            return true;
        }

        return false;
    }

    public boolean light() {
        if ((mViewFlags & CreeperState.VISIBLE_DIM) != 0) {
            mViewFlags = CreeperState.VISIBLE_LIGHT;
            mCreeperView.startDimAnim();
            return true;
        }

        return false;
    }

    public boolean destroy() {
        if ((mViewFlags & CreeperState.GONE) == 0) {
            mViewFlags = CreeperState.GONE;
            mWindowManager.removeView(mCreeperView);
            mCreeperView = null;
            mWindowManager = null;
            mWindowParams = null;
            return true;
        }

        return false;
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
        mWindowParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        mWindowParams.flags = WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                | WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED;
        mWindowParams.format = PixelFormat.TRANSLUCENT;
        mWindowParams.type = WindowManager.LayoutParams.TYPE_PHONE;
    }

}
