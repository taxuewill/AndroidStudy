#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_will_androidstudy_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello C++";
    return env->NewStringUTF(hello.c_str());
}
