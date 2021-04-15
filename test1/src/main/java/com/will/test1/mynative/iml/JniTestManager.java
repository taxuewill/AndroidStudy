package com.will.test1.mynative.iml;

import android.util.Log;

import com.will.test1.mynative.AllJniInterface;

/**
 * @author: will
 * created on: 21-3-28 下午4:43
 * description:
 */
public class JniTestManager {

    private static final JniTestManager mInstance = new JniTestManager();
    public static final String  TAG = JniTestManager.class.getSimpleName();
    private JniTestManager(){
        Log.i(TAG,"create "+TAG);
    }

    public static JniTestManager getInstance(){
        return mInstance;
    }

    public String getStrFromJni(String param){
        return AllJniInterface.getStrParam(param);
    }


}
