package com.will.test1.function.impl;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import com.will.test1.function.intf.IExecutor;

public class IsolateThreadExecutor implements IExecutor {

    HandlerThread mThread;
    Handler mHandler;
    Looper mLooper;

    @Override
    public void prepare(String threadName) {
        mThread = new HandlerThread(threadName);
        mThread.start();
        mLooper = mThread.getLooper();
        mHandler = new Handler(mLooper);

    }

    @Override
    public void execRunnable(Runnable runnable) {
        if(mLooper.isCurrentThread()){
            runnable.run();
        }else{
            mHandler.post(runnable);
        }
    }

    @Override
    public void quit() {
        mHandler.removeCallbacksAndMessages(0);
        mThread.quitSafely();
    }
}
