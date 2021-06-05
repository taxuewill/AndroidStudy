package com.will.androidstudy.manager;

import android.util.Log;
import android.widget.Toast;

import com.will.androidstudy.StudyApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import dalvik.system.DexClassLoader;

public class PluginManager {

    public static final String TAG = "PluginManager";
    private static final PluginManager mInstance = new PluginManager();
    DexClassLoader pluginClassLoader;

    private PluginManager(){
        extractPlugin();
        init();
    }

    private void extractPlugin(){

//        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            //获取读取sdcard的路径
//            Toast.makeText(StudyApplication.getContext(),"sdcard is "+Environment.getExternalStorageDirectory().getAbsolutePath(),Toast.LENGTH_LONG).show();
//        }
            try {
            InputStream inputStream = StudyApplication.getContext().getAssets().open("test1-debug.apk");
            File filePath = StudyApplication.getContext().getExternalFilesDir("plugin");
//            Toast.makeText(StudyApplication.getContext(),
//                    "file is "+file.getAbsolutePath(),
//                    Toast.LENGTH_LONG).show();
            File file = new File(filePath,"plugin.apk");
            OutputStream outputStream = new FileOutputStream(file);
            int byteCount;
            byte[] byteBuffer = new byte[1024];
            while((byteCount=inputStream.read(byteBuffer) )!= -1){
                outputStream.write(byteBuffer,0,byteCount);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init(){
        Log.i(TAG,"init...");
        File pluginFile = StudyApplication.getContext().getExternalFilesDir("plugin");
        if(pluginFile.exists()){
            showToast(pluginFile.getAbsolutePath());
        }
        String extDirPath = StudyApplication.getContext().getExternalFilesDir("").getAbsolutePath();
//        showToast("extPath:"+extDirPath);
        Log.i(TAG,"extDirPath is "+extDirPath);
        pluginClassLoader = new DexClassLoader(pluginFile.getAbsolutePath()+"/plugin.apk",
                extDirPath,
                extDirPath,this.getClass().getClassLoader());

    }

    public static PluginManager getInstance(){
        return mInstance;
    }

    private static void showToast(String content){
        Toast.makeText(StudyApplication.getContext(),content,Toast.LENGTH_SHORT).show();
    }

    public void test(){
//        Log.i(TAG,"test");
        showToast("start test");
        try {
            Class<?> backThreadClass = pluginClassLoader.loadClass("com.will.test1.control.BackThread");
            if(backThreadClass == null){
                showToast("null");
            }else{
                showToast("find class:"+backThreadClass.toString());

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            showToast("ClassNotFound");
        }
    }
}
