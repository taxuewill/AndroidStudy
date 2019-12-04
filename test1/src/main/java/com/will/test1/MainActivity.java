package com.will.test1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.will.test1.constant.Color;
import com.will.test1.control.BackThread;
import com.will.test1.local.NativeContract;
import com.will.test1.presenter.IPresenter;
import com.will.test1.presenter.IView;
import com.will.test1.presenter.ImpPresenter;
import com.will.test1.presenter.IntEnums;
import com.will.test1.test.TestConfig;
import com.will.test1.util.ExecutableTask;
import com.will.test1.util.TaskTimerUtil;

import java.util.HashMap;

import androidx.annotation.IntDef;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IView {

    private static final String TAG = "WillTest";


    IPresenter iPresenter;
    @Color int color;

    private static final int SUNDAY = 1;
    private static final int MONDAY = 2;

    @IntDef({SUNDAY,MONDAY})
    public @interface Weekdays{}

    @Weekdays int currentDay = SUNDAY ;

    @BindView(R.id.btn_test)
    Button mBtnTest;
    @BindView(R.id.btn_sleep)
    Button mBtnSleep;
    @BindView(R.id.btn_wake)
    Button mBtnWake;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        iPresenter = new ImpPresenter(this);
        iPresenter.onEvent(IntEnums.START);
        updateDay(MONDAY);
    }

    @Override
    public void update() {
        Log.i(TAG,"update");
    }

    private void setColor(@Color int color){
        this.color = color;
    }

    private void updateDay(@Weekdays int weekday){

    }

    @OnClick(R.id.btn_test)
    public void onClick(){
        Log.i(TAG,"test_start");
        long start = System.currentTimeMillis();
        NativeContract.dynamicLog();
        HashMap<String,Integer> configs=new HashMap<>();
        configs.put("Key",1);
        configs.put("fisheye",1280);
        NativeContract.setConfigs(configs);
        Log.i(TAG,"test_end ,cost "+(System.currentTimeMillis()-start));
        Toast.makeText(this,"cust time "+((System.currentTimeMillis()-start)),Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.btn_sleep,R.id.btn_wake})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_sleep:
                Log.i(TAG,"start sleep in 10s");
                TaskTimerUtil.getInstance().startTimer(new DelaySleep(),DelaySleep.class.getSimpleName(),10*1000);
                break;
            case R.id.btn_wake:
                iPresenter.sendCmd(TestConfig.CMD_WAKE);
                break;
        }
    }

    class DelaySleep extends ExecutableTask{
        @Override
        public void execute() {
            Log.i(TAG,"run sleep...");
            iPresenter.sendCmd(TestConfig.CMD_SLEEP);
        }
    }
}
