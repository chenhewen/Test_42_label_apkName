package com.example.test_42_label_apkname.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.example.test_42_label_apkname.MainApplication;

import java.util.List;


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

    /**
     * 获取当前前台Activity的应用的包名.<br>
     * 注意:只适用于5.0以下使用.<br>
     *
     * @param context
     * @return 对应的包名或为空当出错时
     */
    public static ComponentName getTopActivity(Context context) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Activity.ACTIVITY_SERVICE);
        // get the info from the currently running task
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
        if (taskInfo == null || taskInfo.isEmpty()) {
            return null;
        }
        ComponentName componentInfo = taskInfo.get(0).topActivity;
        return componentInfo;
    }

}
