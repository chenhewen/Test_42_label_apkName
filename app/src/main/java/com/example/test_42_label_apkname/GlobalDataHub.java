package com.example.test_42_label_apkname;

import android.content.Context;
import android.os.AsyncTask;

import com.example.test_42_label_apkname.data.AppBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by chenhewen on 12/15/15.
 */
public class GlobalDataHub {

    private boolean mIsGlobalDataLoaded;

    private Context mContext;

    private static GlobalDataHub sInstance;

    private Map<Set<String>, AppBean> mAppBeanIndexMap;

    public static GlobalDataHub initGlobalDataHub(Context context) {
        sInstance = new GlobalDataHub(context);
        return sInstance;
    }

    public static GlobalDataHub getInstance() {
        return sInstance;
    }

    private GlobalDataHub(Context context) {
        mContext = context;
    }

    private List<OnGlobalDataLoadedListener> mListeners;

    public void loadGlobalData() {
        new AsyncTask<String, String, String>(){

            @Override
            protected String doInBackground(String... params) {
                mAppBeanIndexMap = AppBean.getAppBeanIndexMap(MainApplication.getGlobalPM().getInstalledApplications(0));
                return "success";
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                mIsGlobalDataLoaded = true;
                notifyDataLoaded();
            }
        }.execute();
    }

    private void notifyDataLoaded() {
        if (mListeners != null) {
            for (OnGlobalDataLoadedListener l : mListeners) {
                l.onGlobalDataLoaded();
            }
        }
    }

    public Map<Set<String>, AppBean> getAppBeanIndexMap() {
        return mAppBeanIndexMap;
    }

    public boolean isGlobalDataLoaded() {
        return mIsGlobalDataLoaded;
    }

    interface OnGlobalDataLoadedListener {
        void onGlobalDataLoaded();
    }

    public void addGlobalDataLoadedList(OnGlobalDataLoadedListener l) {
        if (mListeners == null) {
            mListeners = new ArrayList<OnGlobalDataLoadedListener>();
        }

        mListeners.add(l);

        if (isGlobalDataLoaded()) {
            notifyDataLoaded();
        }
    }
}
