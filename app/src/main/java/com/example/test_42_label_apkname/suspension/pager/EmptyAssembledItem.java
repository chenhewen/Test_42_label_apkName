package com.example.test_42_label_apkname.suspension.pager;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.test_42_label_apkname.R;

/**
 * Created by Administrator on 2015/12/27.
 */
public class EmptyAssembledItem extends AbstractAssembledItem {
    public EmptyAssembledItem(Context context) {
        super(context);
    }

    @Override
    public Drawable getNormalImage() {
        return getContext().getResources().getDrawable(R.drawable.plus);
    }

    @Override
    public Drawable getEditImage() {
        return getContext().getResources().getDrawable(R.drawable.ic_launcher);
    }
}
