package com.will.test1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.will.test1.local.NativeContract;
import com.will.test1.presenter.IPresenter;
import com.will.test1.presenter.IView;

public class MainActivity extends AppCompatActivity implements IView {

    private static final String TAG = "WillTest";


    IPresenter iPresenter;
    Button mBtnTest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnTest = findViewById(R.id.btn_test);
        mBtnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                NativeContract.testLocalRef();
                String [] strArray={"abc","123"};
                NativeContract.testStringArray(strArray);
            }
        });


    }

    @Override
    public void update() {
        Log.i(TAG,"update");
    }




}
