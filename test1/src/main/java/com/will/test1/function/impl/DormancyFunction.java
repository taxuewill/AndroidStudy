package com.will.test1.function.impl;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
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
    AlarmManager mAlarmManager;
    Method mGotoSleep;
    private PowerManager.WakeLock mScreenLock;

    private static final long SLEEP_INTERNAL = 20L*1000;
    private static final String ALARM_ACTION = "segway.alarm.Action";

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
        mAlarmManager = (AlarmManager) TestApplication.getContext().getSystemService(Context.ALARM_SERVICE);
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
            case TestConfig.CMD_ALARM:
                Log.i(TAG,"alarm...");
                handleAlarm();
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
        IntentFilter alarmFilter = new IntentFilter();
        alarmFilter.addAction(ALARM_ACTION);
        TestApplication.getContext().registerReceiver(mAlarmReceiver,alarmFilter);
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

    private void handleAlarm(){
        Log.i(TAG,"start alarm...");
        if(mScreenLock!=null){
            mScreenLock.release();
            mScreenLock = null;
        }
        Intent intent = new Intent(ALARM_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(TestApplication.getContext(),1,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        mAlarmManager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP,SystemClock.elapsedRealtime()+SLEEP_INTERNAL,pendingIntent);
    }

    private BroadcastReceiver mAlarmReceiver = new BroadcastReceiver() {
        @SuppressLint("InvalidWakeLockTag")
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG,"alarmReceiver receive "+intent.getAction());
            if(mScreenLock != null){
                mScreenLock.release();
            }
            mScreenLock = mPm.newWakeLock(PowerManager.FULL_WAKE_LOCK|
                    PowerManager.ACQUIRE_CAUSES_WAKEUP|
                    PowerManager.ON_AFTER_RELEASE,"tag");
            mScreenLock.acquire();
        }
    };
}
