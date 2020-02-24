package com.will.test1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.will.test1.backwork.UploadIntentService;
import com.will.test1.config.CommonConfig;
import com.will.test1.presenter.IPresenter;
import com.will.test1.presenter.IView;
import com.will.test1.presenter.ImpPresenter;
import com.will.test1.service.MonitorService;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IView {

    private static final String TAG = "WillTest";

    IPresenter iPresenter;

    @BindView(R.id.btn_test)
    Button mBtnTest;
    @BindView(R.id.btn_startService)
    Button mBtnStartService;

    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        iPresenter = new ImpPresenter(this);

    }

    @Override
    public void update() {
        Log.i(TAG,"update");
    }

    @OnClick(R.id.btn_test)
    void onTest(){
        Log.i(TAG,"test");
        Intent intent = new Intent(this, UploadIntentService.class);
        intent.putExtra(CommonConfig.TEST,count++);
        startService(intent);
    }

    @OnClick(R.id.btn_startService)
    void onStartService(){
        Log.i(TAG,"startService");
        Intent intent = new Intent(this, MonitorService.class);
        startService(intent);
    }


}
