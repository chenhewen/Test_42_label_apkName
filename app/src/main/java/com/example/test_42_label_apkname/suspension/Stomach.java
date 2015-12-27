package com.example.test_42_label_apkname.suspension;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test_42_label_apkname.R;
import com.example.test_42_label_apkname.suspension.pager.AbstractAssembledPage;

/**
 * Created by Administrator on 2015/12/27.
 */
public class Stomach{

    private static final String TAG = "Stomach";
    private Context mContext;

    private View mContentView;
    private ViewPager mViewPager;

    public Stomach(Context context) {
        mContext = context;
        initContentView();
        initViewPager();
    }

    private void initContentView() {
        mContentView = LayoutInflater.from(mContext).inflate(R.layout.stomach, null);
        mContentView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                    StomachManager.getInstance(mContext).close();
                }
                return false;
            }
        });
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StomachManager.getInstance(mContext).close();
            }
        });

    }

    private void initViewPager() {
        mViewPager = (ViewPager) mContentView.findViewById(R.id.view_pager);
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                /*ImagePage page = new ImagePage(mContext);
                ImageView contentView = page.getContentView();*/
                AbstractAssembledPage page = new AbstractAssembledPage(mContext);
                View contentView = page.getContentView();
                container.addView(contentView);
                return contentView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });
    }

    public View getView() {
        return mContentView;
    }
}
