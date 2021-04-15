package com.will.test1;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * @author: will
 * created on: 21-3-26 下午3:01
 * description:
 */
public class TestApplication extends Application {

    private static final String TAG = TestApplication.class.getSimpleName();

    private static Context mContent;

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContent = this;
        Log.i(TAG,"onCreate");

    }
}
