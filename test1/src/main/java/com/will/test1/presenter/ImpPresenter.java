package com.will.test1.presenter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import com.will.test1.TestApplication;

public class ImpPresenter implements IPresenter {

    private static final String TAG = ImpPresenter.class.getSimpleName();

    private static final int MSG_TEST = 1000;

    IView iView;


    private SensorManager sm;
    //需要两个Sensor
    private Sensor aSensor;
    private Sensor mSensor;

    float[] accelerometerValues;
    float[] magneticFieldValues;
    HandlerThread mBackThread = new HandlerThread("BackThread");
    Handler mBackHandler;





    public ImpPresenter(IView iView){
        this.iView = iView;
        mBackThread.start();
        mBackHandler = new Handler(mBackThread.getLooper(),mCallback);
    }


    @Override
    public void onEvent(@IntEnums.UiEvnets int event) {
        switch (event){
            case IntEnums.START:{
                Log.i(TAG,"start...");
                sm = (SensorManager) TestApplication.getContext().getSystemService(Context.SENSOR_SERVICE);
                aSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                mSensor = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
                break;
            }
        }
    }



    final SensorEventListener myListener = new SensorEventListener() {
        public void onSensorChanged(SensorEvent sensorEvent) {

            if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
                magneticFieldValues = sensorEvent.values;
            if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
                accelerometerValues = sensorEvent.values;

        }
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    };

    Handler.Callback mCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case MSG_TEST:


                    break;
            }
            return false;
        }
    };

}
