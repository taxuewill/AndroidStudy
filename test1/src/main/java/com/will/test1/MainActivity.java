package com.will.test1;

import androidx.annotation.IntDef;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.will.test1.constant.Color;
import com.will.test1.control.BackThread;
import com.will.test1.presenter.IPresenter;
import com.will.test1.presenter.IView;
import com.will.test1.presenter.ImpPresenter;
import com.will.test1.presenter.IntEnums;

public class MainActivity extends AppCompatActivity implements IView {

    private static final String TAG = "WillTest";

    BackThread mBackThread;

    IPresenter iPresenter;
    @Color int color;

    private static final int SUNDAY = 1;
    private static final int MONDAY = 2;

    @IntDef({SUNDAY,MONDAY})
    public @interface Weekdays{}

    @Weekdays int currentDay = SUNDAY ;

    @BindView(R.id.btn_test)
    Button mBtnTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mBackThread = new BackThread("BackThread");
        mBackThread.start();
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
        Log.i(TAG,"test");
    }
}
