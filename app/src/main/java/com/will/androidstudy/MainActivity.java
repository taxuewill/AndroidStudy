package com.will.androidstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.will.androidstudy.beans.Student;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "AndroidStudy";
    private static final String KEY_AGE = "KEY_AGE";

    private Gson mGson = new Gson();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);

        Student student = new Student();
        student.setAge(10);
        student.setName("Wang");
        Student json = mGson.fromJson(mGson.toJson(student),Student.class);
        tv.setText(json.getName()+","+json.getAge());
        age = 10;

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        Log.i(TAG,"onSaveInstanceState "+savedInstanceState);
        savedInstanceState.putInt(KEY_AGE,age);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.i(TAG,"onRestoreInstanceState " +savedInstanceState );
        super.onRestoreInstanceState(savedInstanceState);
        age = savedInstanceState.getInt(KEY_AGE);
    }
}
