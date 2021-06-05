package com.will.androidstudy;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class StudyApplication extends Application {

    private static final String TAG = StudyApplication.class.getSimpleName();

    private static Context mContent;

    @Override
    public void onCreate() {
        super.onCreate();
        mContent = this;
        Log.i(TAG,"onCreate");

    }

    public static Context getContext(){
        return mContent;
    }
}
