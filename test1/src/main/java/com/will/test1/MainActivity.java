package com.will.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.will.test1.control.BackThread;

public class MainActivity extends AppCompatActivity {

    BackThread mBackThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBackThread = new BackThread("BackThread");
        mBackThread.start();

    }
}
