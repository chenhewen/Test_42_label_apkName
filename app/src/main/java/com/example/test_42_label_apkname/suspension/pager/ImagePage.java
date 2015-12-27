package com.example.test_42_label_apkname.suspension.pager;

import android.content.Context;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.example.test_42_label_apkname.R;

/**
 * Created by Administrator on 2015/12/27.
 */
public class ImagePage extends Page {

    public ImagePage(Context context) {
        super(context);
    }

    @Override
    public ImageView getContentView() {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.drawable.ic_launcher);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(100, 100);
        imageView.setLayoutParams(params);
        return imageView;
    }
}
