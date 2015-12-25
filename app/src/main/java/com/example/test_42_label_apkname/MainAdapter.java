package com.example.test_42_label_apkname;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test_42_label_apkname.data.AppBean;
import com.example.test_42_label_apkname.util.AppUtil;

import java.util.List;

/**
 * Created by chenhewen on 12/15/15.
 */
public class MainAdapter extends BaseAdapter {

    private Context mContext;

    private LayoutInflater mInflater;

    private List<AppBean> mAppBeanList;

    public MainAdapter(Context context, List<AppBean> t) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mAppBeanList = t;
    }

    @Override
    public int getCount() {
        return mAppBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAppBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AppBean info = (AppBean) getItem(position);
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = mInflater.inflate(R.layout.item, null);
            holder.mImage = (ImageView) convertView.findViewById(R.id.image);
            holder.mText = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.mImage.setImageDrawable(AppUtil.getIcon(info.getPkgName()));
        holder.mText.setText(info.getLabel() + "\n" + info.getApkPath() + "\n" + info.getPkgName());


        return convertView;
    }

    class Holder {
        public ImageView mImage;
        public TextView mText;
    }
}
