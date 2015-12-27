package com.example.test_42_label_apkname.suspension.pager;

import android.content.Context;
import android.view.View;

/**
 * Created by Administrator on 2015/12/27.
 */
public class AbstractAssembledPage extends Page implements AssembledItem {

    public static final String TAG = "AbstractAssembledPage";
    public AbstractAssembledPage(Context context) {
        super(context);
    }

    private AbstractAssembledItem mItem = new EmptyAssembledItem(getContext());

    @Override
    public View getContentView() {
        return mItem.getView();
    }

    @Override
    public void add() {

    }

    @Override
    public void remove() {

    }

    @Override
    public void edit() {

    }

    @Override
    public void cancelEdit() {

    }
}
