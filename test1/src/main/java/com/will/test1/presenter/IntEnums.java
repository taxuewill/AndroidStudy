package com.will.test1.presenter;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * https://blog.csdn.net/young21234/article/details/49962659
 */


public class IntEnums {

    public static final int START = 1;
    public static final int STOP = 2;

    @IntDef({
            START,STOP
    })
    @Retention(RetentionPolicy.CLASS)
    public @interface UiEvnets{}




    @IntDef({Status.IDLE,Status.DONE})
    @Retention(RetentionPolicy.CLASS)
    public @interface Status{
        int IDLE = 1;
        int DONE = 2;
    }



}


