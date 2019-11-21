package com.will.test1.presenter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import com.will.test1.TestApplication;

public class ImpPresenter implements IPresenter {

    private static final String TAG = ImpPresenter.class.getSimpleName();
    IView iView;


    private SensorManager sm;
    //需要两个Sensor
    private Sensor aSensor;
    private Sensor mSensor;

    float[] accelerometerValues = new float[3];
    float[] magneticFieldValues = new float[3];



    public ImpPresenter(IView iView){
        this.iView = iView;
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

}
