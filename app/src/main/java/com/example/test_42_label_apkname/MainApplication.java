package com.example.test_42_label_apkname;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import com.example.test_42_label_apkname.suspension.SuspensionManager;

/**
 * Created by chenhewen on 12/15/15.
 */
public class MainApplication extends Application {

    private static final String TAG = "MainApplication";

    private static MainApplication sInstance;

    private static PackageManager sPackageManager;

    private GlobalDataHub mGlobalDataHub;

    public MainApplication() {
        sInstance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, TAG + ":onCreate");
        sPackageManager = getPackageManager();
        mGlobalDataHub = GlobalDataHub.initGlobalDataHub(this);
        mGlobalDataHub.loadGlobalData();

        SuspensionManager.getInstance(this).createCreeper();
    }

    public static PackageManager getGlobalPM() {
        return sPackageManager;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }


}
