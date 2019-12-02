package com.will.test1.test;

import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

public class Test {


    public static final int MODE_1 = 1;
    public static final int MODE_2 = 2;

    @IntDef(flag = true,value = {
            MODE_1,
            MODE_2,
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface PreferenceMode{}


    public void myYest(@PreferenceMode int mode){
        Log.i("Test","mode is "+mode);

    }
}
