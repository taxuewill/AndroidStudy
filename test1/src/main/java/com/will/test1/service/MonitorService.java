package com.will.test1.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.will.test1.R;
import com.will.test1.config.CommonConfig;

import androidx.annotation.Nullable;

public class MonitorService extends Service {

    private static final String TAG = MonitorService.class.getSimpleName();

    @Override
    public void onCreate(){
        Log.i(TAG,"onCreate...");
        NotificationChannel channel = new NotificationChannel(CommonConfig.PACKAGE_NAME,TAG, NotificationManager.IMPORTANCE_LOW);

        NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        manager.createNotificationChannel(channel);

        Notification notification = new Notification.Builder(this,CommonConfig.PACKAGE_NAME)
                .setContentTitle("TestTitle")
                .setContentText("Test Content")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .build();

        startForeground(110, notification);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
