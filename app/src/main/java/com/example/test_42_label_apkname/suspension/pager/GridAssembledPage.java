package com.example.test_42_label_apkname.suspension.pager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.test_42_label_apkname.R;

/**
 * Created by Administrator on 2015/12/27.
 */
public class GridAssembledPage extends AbstractAssembledPage {

    private GridView mGridView;

    public GridAssembledPage(Context context) {
        super(context);
    }

    @Override
    public View getContentView() {
        mGridView = new GridView(getContext());
        mGridView.setNumColumns(3);
        final AbstractAssembledItem[] items = new AbstractAssembledItem[9];
        for (int i=0; i<9; i++) {
            items[i] = new EmptyAssembledItem(getContext());
        }

        mGridView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 9;
            }

            @Override
            public Object getItem(int i) {
                return items[i];
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                ViewGroup itemView = (ViewGroup) getLayoutInflater().inflate(R.layout.grid_assembled_page_item_layout, null);
                ImageView image = (ImageView) itemView.findViewById(R.id.image);
                image.setImageDrawable(items[i].getImage());
                return itemView;
            }
        });

        return mGridView;
    }
}
