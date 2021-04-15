package com.will.test1.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


/**
 * @author: will
 * created on: 21-3-21 下午9:00
 * description:
 */
public class MyIntentService extends IntentService {

    public static final String TAG = MyIntentService.class.getSimpleName();
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate(){
        Log.i(TAG,"onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId){
        Log.i(TAG,"onStartCommand flag:"+flags);
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy(){
        Log.i(TAG,"onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String taskName = intent.getExtras().getString("taskName");
        switch (taskName){
            case "task1":{
                Log.i(TAG,"onHandleIntent task1");
                break;
            }
            case "task2":{
                Log.i(TAG,"onHandleIntent task2");
                break;
            }
            default:
                break;
        }
    }
}
