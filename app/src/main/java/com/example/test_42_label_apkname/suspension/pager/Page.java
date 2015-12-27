package com.example.test_42_label_apkname.suspension.pager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Administrator on 2015/12/27.
 */
public abstract class Page {

    private Context mContext;

    private LayoutInflater mLayoutInflater;

    public Page(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    protected Context getContext() {
        return mContext;
    }

    protected LayoutInflater getLayoutInflater() {
        return mLayoutInflater;
    }

    public abstract View getContentView();

}
