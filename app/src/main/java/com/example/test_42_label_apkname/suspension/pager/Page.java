package com.example.test_42_label_apkname.suspension.pager;

import android.content.Context;
import android.view.View;

/**
 * Created by Administrator on 2015/12/27.
 */
public abstract class Page {

    private Context mContext;

    public Page(Context context) {
        mContext = context;
    }

    protected Context getContext() {
        return mContext;
    }


    public abstract View getContentView();

}
