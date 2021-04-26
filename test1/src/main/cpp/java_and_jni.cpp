//
// Created by Will on 4/25/21.
//

#include "java_and_jni.h"
#include <android/log.h>

#include "Static.h"
#include <mutex>

#define TAG  "JniBridge"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__) // 定义LOGD类型
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG ,__VA_ARGS__) // 定义LOGI类型
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,TAG ,__VA_ARGS__) // 定义LOGW类型
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG ,__VA_ARGS__) // 定义LOGE类型
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,TAG ,__VA_ARGS__) // 定义LOGF类型
using namespace std;


namespace will_test{
    JniBridge * JniBridge::self(){
        unique_lock<mutex> local_lock(gJniBridgeMutex);
        if(gJniBridge != NULL){
            return gJniBridge;
        }
        gJniBridge = new JniBridge();
        return gJniBridge;
    }

    JniBridge::JniBridge(){
        LOGD("create JniBridge");
    }

    void JniBridge::testLocalRef() {
        LOGD("testLocalRef");
    }

    void JniBridge::testGlobalRef() {
        LOGD("testGlobalRef");
    }

    void JniBridge::testWeakGlobalRef() {
        LOGD("testWeakGlobalRef");
    }
}

