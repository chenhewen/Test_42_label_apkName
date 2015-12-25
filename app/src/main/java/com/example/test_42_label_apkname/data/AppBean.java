package com.example.test_42_label_apkname.data;

import android.content.pm.ApplicationInfo;

import com.example.test_42_label_apkname.util.AppUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by chenhewen on 12/15/15.
 */
public class AppBean {

    private String mPkgName;

    private String mLauncherActivityName;

    private String mLabel;

    private long mSize;

    private String mApkPath;

    private int mIconColor;

    private long mUpdateTime;


    public static List<AppBean> boxAppBeanList(List<ApplicationInfo> applicationInfoList) {

        List<AppBean> appBeanList = new ArrayList<AppBean>();

        for (ApplicationInfo applicationInfo : applicationInfoList) {
            if (applicationInfo != null) {
                AppBean appBean = new AppBean();
                appBean.mPkgName = applicationInfo.packageName;
                appBean.mLabel = AppUtil.getLabel(applicationInfo);
                appBean.mApkPath = applicationInfo.sourceDir;

                appBeanList.add(appBean);
            }
        }

        return  appBeanList;
    }

    public static Map<Set<String>, AppBean> getAppBeanIndexMap(List<ApplicationInfo> applicationInfoList) {
        Map<Set<String>, AppBean> appBeanIndexMap = new HashMap<Set<String>, AppBean>();

        for (ApplicationInfo applicationInfo : applicationInfoList) {
            Set<String> indexSet = new HashSet<String>();
            AppBean appBean = new AppBean();
            appBean.mPkgName = applicationInfo.packageName;
            appBean.mLabel = AppUtil.getLabel(applicationInfo);
            appBean.mApkPath = applicationInfo.sourceDir;

            indexSet.add(appBean.mPkgName);
            indexSet.add(appBean.mLabel);
            indexSet.add(appBean.mApkPath);

            appBeanIndexMap.put(indexSet, appBean);
        }

        return appBeanIndexMap;
    }

    public String getPkgName() {
        return mPkgName;
    }

    public void setPkgName(String pkgName) {
        mPkgName = pkgName;
    }

    public String getLauncherActivityName() {
        return mLauncherActivityName;
    }

    public void setLauncherActivityName(String launcherActivityName) {
        mLauncherActivityName = launcherActivityName;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public long getSize() {
        return mSize;
    }

    public void setSize(long size) {
        mSize = size;
    }

    public String getApkPath() {
        return mApkPath;
    }

    public void setApkPath(String apkPath) {
        mApkPath = apkPath;
    }

    public int getIconColor() {
        return mIconColor;
    }

    public void setIconColor(int iconColor) {
        mIconColor = iconColor;
    }

    public long getUpdateTime() {
        return mUpdateTime;
    }

    public void setUpdateTime(long updateTime) {
        mUpdateTime = updateTime;
    }
}
