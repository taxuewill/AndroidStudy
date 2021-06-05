package com.will.test1.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.common.bookmanager.BookManager;

public class BookManagerService extends Service {

    BookManager mBookManager = new BookManager();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBookManager;
    }
}
