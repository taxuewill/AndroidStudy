package com.will.test1;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.will.test1.mynative.AllJniInterface;
import com.will.test1.mynative.iml.JniTestManager;
import com.will.test1.presenter.IPresenter;
import com.will.test1.presenter.IView;
import com.will.test1.presenter.ImpPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IView {

    private static final String TAG = "WillTest";


    IPresenter iPresenter;

    @BindView(R.id.btn_test)
    Button mBtnTest;

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
        Log.i(TAG,"test" + JniTestManager.getInstance().getStrFromJni("abc"));
//        Intent intent = new Intent(this, MyIntentService.class);
//        intent.putExtra("taskName","task1");
//        startService(intent);
    }


}
