package com.example.test_42_label_apkname.suspension.pager;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.test_42_label_apkname.R;

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
        ViewGroup itemView = (ViewGroup) getLayoutInflater().inflate(R.layout.grid_assembled_page_item_layout, null);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.image);
        updateContentView(imageView);
        return itemView;
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
