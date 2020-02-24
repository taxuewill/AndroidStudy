package com.will.test1.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.will.test1.service.MonitorService;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent monitorService = new Intent(context, MonitorService.class);
        context.startService(monitorService);
    }
}
