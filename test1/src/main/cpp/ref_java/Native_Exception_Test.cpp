//
// Created by Will on 5/16/21.
//

#include "Native_Exception_Test.h"
#include "../android_log.h"

#define TAG "jniNativeException"

namespace will_test {

    static const char *java_class = "com/will/test1/local/NativeExceptionTest";
    static jclass gGlobalClass = NULL;

    static void nativeDoIt(JNIEnv *env, jobject obj){
        LOGD("nativeDoIt");


    }
    static JNINativeMethod nativeMethod[] = {
            {"doIt", "()V", (void*)nativeDoIt}
    };

    void registerNativeExceptionTest(JNIEnv * env){
        jclass cls = env->FindClass(java_class);
        if(cls != NULL){
            env->RegisterNatives(cls, nativeMethod, sizeof(nativeMethod)/sizeof(nativeMethod[0]));
            gGlobalClass =(jclass) env->NewGlobalRef(cls);
        }
        env->DeleteLocalRef(cls);
    }

}
