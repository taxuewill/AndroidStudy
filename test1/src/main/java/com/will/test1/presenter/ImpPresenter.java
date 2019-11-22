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
    private Sensor accelerometerSensor;
    private Sensor magneticSensor;
    private Sensor gyroscopeSensor;

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
                accelerometerSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                sm.registerListener(myListener,accelerometerSensor,SensorManager.SENSOR_DELAY_NORMAL);
                magneticSensor = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
                sm.registerListener(myListener,magneticSensor,SensorManager.SENSOR_DELAY_NORMAL);

//                gyroscopeSensor = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
//                sm.registerListener(gListener,gyroscopeSensor,SensorManager.SENSOR_DELAY_GAME);
                break;
            }
        }
    }



    SensorEventListener myListener = new SensorEventListener() {
        public void onSensorChanged(SensorEvent sensorEvent) {

            if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
                magneticFieldValues = sensorEvent.values.clone();
//                coordinateTransform(magneticFieldValues);
//                Log.i(TAG,String.format("magnetic x:%f,y:%f,z:%f",magneticFieldValues[0],magneticFieldValues[1],magneticFieldValues[2]));
            }

            if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
                accelerometerValues = sensorEvent.values.clone();
//                coordinateTransform(accelerometerValues);
//                Log.i(TAG,String.format("Accelerometer x:%f,y:%f,z:%f",accelerometerValues[0],accelerometerValues[1],accelerometerValues[2]));
                mBackHandler.sendEmptyMessage(MSG_TEST);
            }



        }
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    };

    SensorEventListener gListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float gyrox = event.values[0];
            float gyroy = event.values[1];
            float gyroz = event.values[2];
            Log.i(TAG,String.format("                                                                                x:%f,y:%f,z:%f",gyrox,gyroy,gyroz));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    Handler.Callback mCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case MSG_TEST:
                    calculatorOrientation();
                    break;
            }
            return false;
        }
    };

    private void calculatorOrientation(){
        if(accelerometerValues != null&& magneticFieldValues != null){
            float[] R = new float[9];
            float[] values = new float[3];

            SensorManager.getRotationMatrix(R, null, accelerometerValues,
                    magneticFieldValues);
            SensorManager.getOrientation(R, values);
            double angle = Math.toDegrees(values[0]);
            Log.i(TAG,String.format("Orientation %f",angle));
        }

    }


    private void coordinateTransform(float [] array){
        float y = array[1];
        float z = array[2];
        array[1] = -z;
        array[2] = y;

    }

}
