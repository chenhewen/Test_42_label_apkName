package com.example.test_42_label_apkname;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by chenhewen on 12/15/15.
 */
public class MessageIntent {


    public static final String ACTION_ON_LOAD = "ACTION_ON_LOAD";

    public static final String ACTION_ON_LOADED = "ACTION_ON_LOADED";

    public static final Intent INTENT_GLOBAL_DATA = new Intent();

    public static final IntentFilter INTENT_FILTER_GLOBAL_DATA = new IntentFilter();

    static {
        INTENT_FILTER_GLOBAL_DATA.addAction(ACTION_ON_LOAD);
        INTENT_FILTER_GLOBAL_DATA.addAction(ACTION_ON_LOADED);
    }
}
