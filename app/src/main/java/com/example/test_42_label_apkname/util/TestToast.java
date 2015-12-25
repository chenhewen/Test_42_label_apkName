package com.example.test_42_label_apkname.util;

import android.widget.Toast;

import com.example.test_42_label_apkname.MainApplication;

/**
 * Created by chenhewen on 12/16/15.
 */
public class TestToast {

    /*"手机管家"
    "家管手机"
    "家里"
    "新手机"
    "手里的打火机"
    "哈哈"*/

    public static void makeTextAndShow(String toastString) {
        Toast.makeText(MainApplication.getAppContext(), toastString, Toast.LENGTH_SHORT).show();
    }
}
