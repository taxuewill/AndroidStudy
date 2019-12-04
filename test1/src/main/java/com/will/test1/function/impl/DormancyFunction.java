package com.will.test1.function.impl;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;

import com.will.test1.TestApplication;
import com.will.test1.function.intf.IFunction;
import com.will.test1.function.param.InParam;
import com.will.test1.function.param.OutParam;
import com.will.test1.receiver.ScreenStateReceiver;
import com.will.test1.receiver.intf.IScreenListener;
import com.will.test1.test.TestConfig;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DormancyFunction implements IFunction {

    private static final String TAG = DormancyFunction.class.getSimpleName();
    PowerManager mPm;
    Method mGotoSleep;

    private IScreenListener mListener = new IScreenListener() {
        @Override
        public void onScreenChange(int state) {
            switch (state){
                case IScreenListener.SCREEN_ON:
                    Log.i(TAG,"SCREEN_ON");
                    break;
                case IScreenListener.SCREEN_OFF:
                    Log.i(TAG,"SCREEN_OFF");
                    break;
            }
        }
    };
    private ScreenStateReceiver mScreenStateReceiver;

    public DormancyFunction(){
        mScreenStateReceiver = new ScreenStateReceiver(mListener);
        mPm = (PowerManager) TestApplication.getContext().getSystemService(Context.POWER_SERVICE);
        Class powerManagerClass = mPm.getClass();
        try {
            mGotoSleep = powerManagerClass.getDeclaredMethod("goToSleep",long.class);
            if(mGotoSleep!=null){
                mGotoSleep.setAccessible(true);
            }else{
                Log.i(TAG,"not find goToSleep");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        registerScreenReceiver();
    }

    @Override
    public String getName() {
        return TAG;
    }

    @Override
    public OutParam runWithParam(InParam inParam) {
        Log.i(TAG, "runWithParam " + inParam.iParam);
        switch (inParam.iParam) {
            case TestConfig.CMD_SLEEP:
                handleSleep();
                break;
            case TestConfig.CMD_WAKE:
                handleWake();
                break;
        }

        return null;
    }

    private void handleSleep() {
        gotoSleep();
    }

    private void handleWake() {

    }

    private void registerScreenReceiver(){
        IntentFilter screenStateFilter = new IntentFilter();
        screenStateFilter.addAction(Intent.ACTION_SCREEN_ON);
        screenStateFilter.addAction(Intent.ACTION_SCREEN_OFF);
        TestApplication.getContext().registerReceiver(mScreenStateReceiver,screenStateFilter);
    }

    private void gotoSleep(){
        if(mGotoSleep!=null){
            Log.i(TAG,"goToSleep...");
            try {
                mGotoSleep.invoke(mPm, SystemClock.uptimeMillis());
            } catch (IllegalAccessException e) {
                Log.i(TAG,"IllegalAccessException "+e.toString());
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                Log.i(TAG,"InvocationTargetException "+e.toString());
                e.printStackTrace();
            }
        }

    }
}
