package com.example.test_42_label_apkname.suspension;

import android.content.Context;

/**
 * Created by chenhewen on 12/25/15.
 */
public class SuspensionManager {

    private Context mContext;

    private static SuspensionManager sInstance;

    private SuspensionManager(Context context) {
        mContext = context;
    }

    public static SuspensionManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SuspensionManager(context);
        }

        return  sInstance;
    }

    public void createCreeper() {
        CreeperManager.getInstance(mContext).create();
    }

    public void destroyCreeper() {
        CreeperManager.getInstance(mContext).destroy();
    }

}
