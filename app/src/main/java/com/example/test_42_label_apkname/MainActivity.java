package com.example.test_42_label_apkname;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.test_42_label_apkname.data.AppBean;
import com.example.test_42_label_apkname.suspension.Creeper;
import com.example.test_42_label_apkname.suspension.SuspensionManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class MainActivity extends Activity {

    private LayoutInflater mInflater;
    private ListView mListView;
    private MainAdapter mAdapter;
    EditText mEditText;

    private Map<Set<String>, AppBean> mAppBeanIndexMap;

    List<AppBean> mShowList = new ArrayList<AppBean>();
    private SearchHandler mSearchHandler;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, TAG + ":onCreate");

        /*Intent intent = new Intent();
        intent.setPackage("com.android.settings");
        List<ResolveInfo> resolveInfos = getApplicationContext().getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

        for (ResolveInfo info : resolveInfos) {
            if (info.loadLabel(getPackageManager()).toString().equals("VPN")) {
                Intent startIntent = new Intent();
                ComponentName cn = new ComponentName("com.android.settings", info.activityInfo.name);
                startIntent.setComponent(cn);
                startActivity(startIntent);
            }
            Log.d(TAG, "label: " + info.loadLabel(getPackageManager()).toString());
        }*/

        SuspensionManager.getInstance(this).createCreeper();

        //loadData();
        //initView();

    }

    private void loadData() {
        mSearchHandler = new SearchHandler();

        GlobalDataHub.getInstance().addGlobalDataLoadedList(new GlobalDataHub.OnGlobalDataLoadedListener() {
            @Override
            public void onGlobalDataLoaded() {
                onDataLoaded();
            }
        });
    }

    private void initView() {
        mInflater = LayoutInflater.from(this);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.list);
        mEditText = (EditText) findViewById(R.id.editText);
        mAdapter = new MainAdapter(this, mShowList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppBean appBean = (AppBean) parent.getAdapter().getItem(position);
            }
        });
        mEditText.addTextChangedListener(mTextWatcher);

    }

    private void onDataLoaded() {
        Log.d(TAG, TAG + ":onDataLoaded");
        mAppBeanIndexMap = GlobalDataHub.getInstance().getAppBeanIndexMap();
        sendSearchMessage("");
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
        @Override
        public void afterTextChanged(Editable s) {

            if (mAppBeanIndexMap != null) {
                sendSearchMessage(s.toString());
            }

            mAdapter.notifyDataSetChanged();
        }

    };

    private void sendSearchMessage(String s) {
        Message message = Message.obtain();
        message.obj = s;
        mSearchHandler.sendMessage(message);
    }

    public class SearchHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            mShowList.clear();

            if (TextUtils.isEmpty((String) msg.obj)) {
                mShowList.addAll(mAppBeanIndexMap.values());
                mAdapter.notifyDataSetChanged();
                return;
            }

            String[] split = ((String) msg.obj).split(" ");

            StringBuffer tempRegex = new StringBuffer();
            for (int i = 0; i < split.length; i++) {
                tempRegex.append("(?=.*?").append(split[i]).append(")");
            }

            Pattern p = Pattern.compile(tempRegex.toString());

            Iterator<Map.Entry<Set<String>, AppBean>> mapIterator = mAppBeanIndexMap.entrySet().iterator();
            while (mapIterator.hasNext()) {
                Map.Entry<Set<String>, AppBean> nextEntry = mapIterator.next();
                Set<String> key = nextEntry.getKey();
                Iterator<String> setIterator = key.iterator();
                while (setIterator.hasNext()) {
                    String nextIndex = setIterator.next();
                    Matcher matcher = p.matcher(nextIndex);
                    if (matcher.find()) {
                        mShowList.add(nextEntry.getValue());
                        break;
                    }
                }
            }
        }
    }
}
