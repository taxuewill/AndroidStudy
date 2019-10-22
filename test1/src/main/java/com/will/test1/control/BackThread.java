package com.will.test1.control;

import android.util.Log;

public class BackThread extends Thread {

    private static final String TAG = "BackThread";
    public BackThread(String name){
        super(name);
    }

    @Override
    public void run(){
        while(true){
            Log.i(TAG,"echo .");
            try {
                Thread.sleep(2*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
