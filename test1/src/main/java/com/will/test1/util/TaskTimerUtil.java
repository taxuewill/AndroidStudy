package com.will.test1.util;


import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class TaskTimerUtil extends Timer {

    private static final String TAG = "TaskTimerUtil";
    private static volatile TaskTimerUtil mTimer;
    private static Map<String, TimerTask> mTaskMap = new HashMap<String, TimerTask>();

    private TaskTimerUtil() {
        super("TaskTimerUtil used thread");
    }

    public static TaskTimerUtil getInstance() {
        if (mTimer == null) {
            synchronized (TaskTimerUtil.class){
                if(mTimer == null){
                    mTimer = new TaskTimerUtil();
                }
            }

        }
        return mTimer;
    }

    public void startTimer(TimerTask task, String taskName, long ms) {
        Log.i(TAG, "startTimer,taskName:" + taskName);
        TimerTask timerTask = mTaskMap.get(taskName);
        if (timerTask != null) {
            timerTask.cancel();
            mTaskMap.remove(taskName);
        }
        mTaskMap.put(taskName, task);
        try {
            mTimer.schedule(task, ms);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public void startTimer(TimerTask task, String taskName, int firstDelay, int msInterval) {
        Log.i(TAG, "startTimer,timer num" + mTaskMap.size());
        TimerTask timerTask = mTaskMap.get(taskName);
        if (timerTask != null) {
            timerTask.cancel();
            mTaskMap.remove(taskName);
        }
        mTaskMap.put(taskName, task);
        try {
            mTimer.schedule(task, firstDelay, msInterval);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public void cancelTimer(String taskName) {
        Log.i(TAG, "cancelTimer,taskName:" + taskName);
        TimerTask timerTask = mTaskMap.get(taskName);
        if (timerTask != null) {
            timerTask.cancel();
            mTaskMap.remove(taskName);
        }
    }


    @Override
    public void cancel(){
        Log.e(TAG,"Don't call this");
    }


}
