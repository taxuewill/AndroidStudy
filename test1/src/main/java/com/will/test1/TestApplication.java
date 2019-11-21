package com.will.test1;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class TestApplication extends Application {


    private static final String TAG = TestApplication.class.getSimpleName();

    private static Context mContext;

    @Override
    public void onCreate(){
        super.onCreate();
        Log.i(TAG,"onCreate ...");
        mContext = this;
    }



    public static Context getContext(){
        return mContext;
    }
}
