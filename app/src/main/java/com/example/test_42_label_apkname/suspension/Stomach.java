package com.example.test_42_label_apkname.suspension;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test_42_label_apkname.R;
import com.example.test_42_label_apkname.suspension.pager.AbstractAssembledPage;
import com.example.test_42_label_apkname.suspension.pager.GridAssembledPage;

/**
 * Created by Administrator on 2015/12/27.
 */
public class Stomach{

    private static final String TAG = "Stomach";
    private final LayoutInflater mLayoutInflater;
    private Context mContext;

    private View mContentView;
    private ViewPager mViewPager;

    public Stomach(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
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
        /*final AbstractAssembledPage page1 = new AbstractAssembledPage(mContext);
        final AbstractAssembledPage page2 = new AbstractAssembledPage(mContext);
        final AbstractAssembledPage page3 = new AbstractAssembledPage(mContext);
        final AbstractAssembledPage[] pages = {page1, page2, page3};*/
        final GridAssembledPage page1 = new GridAssembledPage(mContext);
        final GridAssembledPage page2 = new GridAssembledPage(mContext);
        final GridAssembledPage page3 = new GridAssembledPage(mContext);
        final GridAssembledPage[] pages = {page1, page2, page3};
        mViewPager.setAdapter(new PagerAdapter() {

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public Object instantiateItem(ViewGroup container, final int position) {
                Log.d(TAG, "instantiateItem");
                ViewGroup stomachContainer = (ViewGroup) mLayoutInflater.inflate(R.layout.stomach_container, null);
                AbstractAssembledPage page = pages[position];
                View contentView = page.getContentView();
                stomachContainer.addView(contentView);
                container.addView(stomachContainer);
                return stomachContainer;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                Log.d(TAG, "destroyItem");
                container.removeView((View) object);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });
        mViewPager.setOffscreenPageLimit(3);
    }

    public View getView() {
        return mContentView;
    }
}
