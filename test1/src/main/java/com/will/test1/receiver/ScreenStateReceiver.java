package com.will.test1.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.will.test1.receiver.intf.IScreenListener;


public class ScreenStateReceiver extends BroadcastReceiver {

    IScreenListener mScreenListener;
    public ScreenStateReceiver(IScreenListener iScreenListener){
        super();
        mScreenListener = iScreenListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            mScreenListener.onScreenChange(IScreenListener.SCREEN_OFF);
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            mScreenListener.onScreenChange(IScreenListener.SCREEN_ON);
        }
    }
}
