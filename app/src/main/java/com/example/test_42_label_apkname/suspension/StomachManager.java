package com.example.test_42_label_apkname.suspension;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;

/**
 * Created by Administrator on 2015/12/27.
 */
public class StomachManager {

    private static final String TAG = "StomachManager";

    private Context mContext;

    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mWindowParams;

    private int mViewFlags = StomachState.DESTROYED;

    private static StomachManager sInstance;
    private Stomach mStomach;

    private StomachManager(Context context) {
        mContext = context;
    }

    public static StomachManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new StomachManager(context);
        }
        return  sInstance;
    }

    private static class StomachState {

        private static final int DESTROYED = 0x1;

        //private static final int CREATED = 0x1 << 1;

        private static final int CLOSED = 0x1 << 2;

        private static final int OPEN = 0x1 << 3;

        private static final int ANIM_TO_OPEN = 0x1 << 4;

        private static final int ANIM_TO_CLOSE = 0x01 << 5;
    }

    private boolean create() {
        if ((mViewFlags & StomachState.DESTROYED) != 0) {
            mViewFlags = StomachState.CLOSED;
            mStomach = new Stomach(mContext);
            initWindowParam();
            mWindowManager.addView(mStomach.getView(), mWindowParams);
            return true;
        }

        return false;
    }

    public boolean open() {
        create();
        if ((mViewFlags & StomachState.CLOSED) != 0) {
            mViewFlags = StomachState.OPEN;
            return true;
        }

        return false;
    }

    public boolean close() {
        if ((mViewFlags & StomachState.OPEN) != 0) {
            mViewFlags = StomachState.CLOSED;
            destroy();
            return true;
        }

        return false;
    }

    private boolean destroy() {
        if ((mViewFlags & StomachState.CLOSED) != 0) {

            Log.d(TAG, "destroy");
            mViewFlags = StomachState.DESTROYED;
            mWindowManager.removeView(mStomach.getView());
            mStomach = null;
            mWindowManager = null;
            mWindowParams = null;
            return true;
        }

        return false;
    }

    private void initWindowParam() {

        mWindowManager = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        mWindowParams = new WindowManager.LayoutParams();
        mWindowParams.alpha = 1.0f;
        mWindowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        mWindowParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        mWindowParams.gravity = Gravity.LEFT | Gravity.TOP;
        mWindowParams.flags = WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED;
        mWindowParams.format = PixelFormat.TRANSLUCENT;
        mWindowParams.type = WindowManager.LayoutParams.TYPE_PHONE;


    }
}
