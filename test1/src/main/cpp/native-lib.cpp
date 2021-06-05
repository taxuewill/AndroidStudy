#include "global_support.h"
#include "java_and_jni.h"
#include "android_log.h"
#include "ref_java/Java_NativeContract_Jni.h"
#include "ref_java/Native_Exception_Test.h"
#define TAG  "native_lib"



static JavaVM * gJavaVM;

using namespace will_test;


JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *jvm, void *reserved) {
    JNIEnv *env;
    if (jvm->GetEnv((void**) &env, JNI_VERSION_1_4) != JNI_OK) {
        return -1;
    }
    LOGD("JNI_OnLoad");
    gJavaVM = jvm;
    registerNativeContract(env);
    registerNativeExceptionTest(env);
    return JNI_VERSION_1_4;
}

JavaVM * getGlobalJavaVM(){
    return gJavaVM;
}