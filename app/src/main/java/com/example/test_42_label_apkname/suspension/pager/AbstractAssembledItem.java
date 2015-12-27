package com.example.test_42_label_apkname.suspension.pager;

import android.content.Context;
import android.view.View;

/**
 * Created by Administrator on 2015/12/27.
 */
public abstract class AbstractAssembledItem implements AssembledItem {

    private final Context mContext;

    public AbstractAssembledItem(Context context) {
        mContext = context;
    }

    private boolean mHasItem;

    private boolean mInEditMode;

    protected Context getContext() {
        return mContext;
    }

    public boolean hasItem() {
        return mHasItem;
    }

    public void setHasItem(boolean b) {
        mHasItem = b;
    }

    public boolean isInEditMode() {
        return mInEditMode;
    }

    public void setInEditMode(boolean b) {
        mInEditMode = b;
    }
    @Override
    public void add() {
        mHasItem = true;
    }

    @Override
    public void remove() {
        mHasItem = false;
    }

    @Override
    public void edit() {
        if (mInEditMode) {
            mInEditMode = false;
        } else {
            mInEditMode = true;
        }
    }

    public abstract View getView();
}
