package com.will.test1.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class BackIntentService extends IntentService {

    public static final String TAG = "BackIntentService";

    public BackIntentService() {
        super("BackIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(TAG,"this is handle in backgroud thread");
    }
}
