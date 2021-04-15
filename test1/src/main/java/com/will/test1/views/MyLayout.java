package com.will.test1.views;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * @author: will
 * created on: 21-3-29 上午8:51
 * description:
 */
public class MyLayout extends LinearLayout {

    private static final String TAG = MyLayout.class.getSimpleName();

    public MyLayout(Context context) {
        super(context);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev){
        Log.i(TAG,"dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev){
        Log.i(TAG,"onInterceptTouchEvent");
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev){
        Log.i(TAG,"onTouchEvent");
        return super.onTouchEvent(ev);
    }

}
