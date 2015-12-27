package com.example.test_42_label_apkname.suspension.pager;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.test_42_label_apkname.R;

/**
 * Created by Administrator on 2015/12/27.
 */
public abstract class AbstractAssembledItem implements AssembledItem {

    private final Context mContext;

    private LayoutInflater mLayoutInflater;

    private ViewGroup mItemView;
    private ImageView mImageView;

    public AbstractAssembledItem(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        initView();
    }

    protected Context getContext() {
        return mContext;
    }

    protected  LayoutInflater getLayoutInflater() {
        return mLayoutInflater;
    }

    private void initView() {
        mItemView = (ViewGroup) mLayoutInflater.inflate(R.layout.grid_assembled_page_item_layout, null);
        mImageView = (ImageView) mItemView.findViewById(R.id.image);
        cancelEdit();
        mImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                edit();
                return true;
            }
        });
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelEdit();
            }
        });
    }
    @Override
    public void add() {
    }

    @Override
    public void remove() {
    }

    @Override
    public void edit() {
        mImageView.setImageDrawable(getEditImage());
    }

    @Override
    public void cancelEdit() {
        mImageView.setImageDrawable(getNormalImage());
    }

    protected abstract Drawable getNormalImage();

    protected abstract Drawable getEditImage();

    public View getView() {
        return mItemView;
    }
}
