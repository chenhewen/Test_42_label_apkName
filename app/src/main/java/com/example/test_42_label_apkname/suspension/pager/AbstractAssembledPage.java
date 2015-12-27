package com.example.test_42_label_apkname.suspension.pager;

import android.content.Context;
import android.view.View;

/**
 * Created by Administrator on 2015/12/27.
 */
public class AbstractAssembledPage extends Page implements Assembled {
    public AbstractAssembledPage(Context context) {
        super(context);
    }

//    private AbstractAssembledItem mItems[] = new AbstractAssembledItem[1];
    private AbstractAssembledItem mItems[] = {new EmptyAssembledItem(getContext())};

    @Override
    public View getContentView() {

        return mItems[0].getView();
    }

    @Override
    public void add(int index, AssembledItem item) {
        AbstractAssembledItem abItem = (AbstractAssembledItem) item;
        if (abItem.isInEditMode()) {
            mItems[index] = abItem;
            abItem.setHasItem(true);
        }
    }

    @Override
    public void remove(int index, AssembledItem item) {
        AbstractAssembledItem abItem = (AbstractAssembledItem) item;
        if (abItem.isInEditMode()) {
            mItems[index] = new EmptyAssembledItem(getContext());
            abItem.setHasItem(false);
        }
    }

    @Override
    public void edit(int index,  AssembledItem item) {
        AbstractAssembledItem abItem = (AbstractAssembledItem) item;
        if (abItem.isInEditMode()) {
            abItem.setInEditMode(true);
        }
    }
}
