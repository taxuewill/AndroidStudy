//
// Created by will on 21-3-26.
//
#include <jni.h>
#include <android/log.h>
#include <string>

#define TAG "JNI_Global"

#define ALOGD(...) __android_log_write(ANDROID_LOG_ERROR, TAG, ##__VA_ARGS__);


#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__) // 定义LOGD类型
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG ,__VA_ARGS__) // 定义LOGI类型
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,TAG ,__VA_ARGS__) // 定义LOGW类型
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG ,__VA_ARGS__) // 定义LOGE类型
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,TAG ,__VA_ARGS__) // 定义LOGF类型

JavaVM * g_JavaVM;

JNIEXPORT jint JNICALL nativeGetInt(JNIEnv *env, jobject obj){

    return 11;
}

JNIEXPORT jstring JNICALL nativeGetString(JNIEnv * env, jobject ojb,jstring param){
    const char * c_str = NULL;
    c_str = env->GetStringUTFChars(param,NULL);
    LOGD("get param from java %s",c_str);
    std::string result(c_str);
    env->ReleaseStringUTFChars(param,c_str);
    result = result+"123";
    return env->NewStringUTF(result.c_str());
}

static const JNINativeMethod methods[] = {
        {"getIntTest", "()I", reinterpret_cast<void *>(nativeGetInt)},
        {"getStrParam", "(Ljava/lang/String;)Ljava/lang/String;", reinterpret_cast<void *>(nativeGetString)}
};

JNIEXPORT jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env;
    g_JavaVM = vm;
    if (vm->GetEnv(reinterpret_cast<void **>(&env), JNI_VERSION_1_6) != JNI_OK) {
        return JNI_ERR;
    }

// Find your class. JNI_OnLoad is called from the correct class loader context for this to work.
    jclass c = env->FindClass("com/will/test1/mynative/AllJniInterface");
    if (c == NULL) return JNI_ERR;

// Register your class' native methods.

    int rc = env->RegisterNatives(c, methods, sizeof(methods) / sizeof(JNINativeMethod));
    if (rc != JNI_OK) return rc;
    LOGD("JNI_OnLoad %d",1);

    return JNI_VERSION_1_6;
}