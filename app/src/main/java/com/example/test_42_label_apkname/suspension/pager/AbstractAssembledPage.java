package com.example.test_42_label_apkname.suspension.pager;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;

/**
 * Created by Administrator on 2015/12/27.
 */
public class AbstractAssembledPage extends Page implements Assembled {

    public static final String TAG = "AbstractAssembledPage";

    public AbstractAssembledPage(Context context) {
        super(context);
    }

    private AbstractAssembledItem mItem = new EmptyAssembledItem(getContext());

    @Override
    public View getContentView() {

        ImageView imageView = new ImageView(getContext());
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(100, 100);
        imageView.setLayoutParams(params);
        return updateContentView(imageView);
    }

    private View updateContentView(final ImageView imageView) {
        imageView.setImageDrawable(mItem.getImage());
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.d(TAG, "onLongClick");
                mItem.edit();
                updateContentView((ImageView) view);
                return true;
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick");
                mItem.cancelEdit();
                updateContentView((ImageView) view);
            }
        });

        return imageView;
    }

    @Override
    public void add(int index, AssembledItem item) {

    }

    @Override
    public void remove(int index) {
        mItem = new EmptyAssembledItem(getContext());
    }

    @Override
    public void edit(int index) {
        mItem.edit();
    }
}
