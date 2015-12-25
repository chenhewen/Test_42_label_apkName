package com.example.test_42_label_apkname.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.example.test_42_label_apkname.MainApplication;


/**
 * Created by chenhewen on 12/15/15.
 */
public class AppUtil {

    public static Drawable getIcon(String pkgName) {

        ApplicationInfo applicationInfo = null;

        try {
            applicationInfo = MainApplication.getGlobalPM().getApplicationInfo(pkgName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return getIcon(applicationInfo);
    }

    public static Drawable getIcon(ApplicationInfo info) {
        if (info != null) {
            return info.loadIcon(MainApplication.getGlobalPM());
        }

        return null;
    }

    public static String getLabel(String pkgName) {
        ApplicationInfo applicationInfo = null;

        try {
            applicationInfo = MainApplication.getGlobalPM().getApplicationInfo(pkgName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return getLabel(applicationInfo);
    }

    public static String getLabel(ApplicationInfo info) {
        if (info != null) {
            return info.loadLabel(MainApplication.getGlobalPM()).toString();
        }

        return "";
    }


}
