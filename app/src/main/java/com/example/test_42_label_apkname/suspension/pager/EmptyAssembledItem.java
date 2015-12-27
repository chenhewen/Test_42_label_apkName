package com.example.test_42_label_apkname.suspension.pager;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.example.test_42_label_apkname.R;

/**
 * Created by Administrator on 2015/12/27.
 */
public class EmptyAssembledItem extends AbstractAssembledItem {
    public EmptyAssembledItem(Context context) {
        super(context);
    }

    @Override
    public View getView() {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.drawable.plus);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(100, 100);
        imageView.setLayoutParams(params);
        return imageView;
    }
}
