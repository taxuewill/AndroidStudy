#include <jni.h>
#include <android/log.h>

#define TAG  "native_lib"

//help ref https://blog.csdn.net/shaosunrise/article/details/79838297

#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__) // 定义LOGD类型
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG ,__VA_ARGS__) // 定义LOGI类型
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,TAG ,__VA_ARGS__) // 定义LOGW类型
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG ,__VA_ARGS__) // 定义LOGE类型
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,TAG ,__VA_ARGS__) // 定义LOGF类型

static const char * java_class="com/will/test1/local/NativeContract";


static jmethodID entrySetMID;
static jmethodID iteratorMID;
static jmethodID  hasNextMID;
static jmethodID nextMID;
static jmethodID getKeyMID;
static jmethodID getValueMID;
static jmethodID valueMID;


static void nativeDynamicLog(JNIEnv *env, jclass cla){
    LOGD("hell main");
}

static void nativeSetConfigs(JNIEnv *env, jclass  cla,jobject hashMapInfo){
    LOGD("nativeSetConfigs");
    jobject setObj = env->CallObjectMethod(hashMapInfo,entrySetMID);
    jobject iteratorObj = env->CallObjectMethod(setObj,iteratorMID);

    // 循环检测HashMap中是否还有数据
    while (env->CallBooleanMethod(iteratorObj, hasNextMID)) {
        // 读取一条数据
        jobject entryObj = env->CallObjectMethod(iteratorObj, nextMID);

        // 提取数据中key值：String类型课程名字
        jstring courseJS = (jstring) env->CallObjectMethod(entryObj, getKeyMID);
        if (courseJS == NULL)   // HashMap允许null类型
            continue;
        // jstring转C风格字符串
        const char *courseStr = env->GetStringUTFChars(courseJS, NULL);

        // 提取数据中value值：Integer类型分数，并转为int类型
        jobject scoreObj = env->CallObjectMethod(entryObj, getValueMID);
        if (scoreObj == NULL)
            continue;
        int score = (int) env->CallIntMethod(scoreObj, valueMID);

        // 拼接字符串，sprintf函数返回拼接字符个数
        LOGD("key is %s value is %d",courseStr,score);

        // 释放UTF字符串资源
        env->ReleaseStringUTFChars(courseJS, courseStr);
        // 释放JNI局部引用资源
        env->DeleteLocalRef(entryObj);
        env->DeleteLocalRef(courseJS);
        env->DeleteLocalRef(scoreObj);
    }

    // 释放JNI局部引用: jclass jobject
    env->DeleteLocalRef(setObj);
    env->DeleteLocalRef(iteratorObj);

}


void initNative(JNIEnv * env){
    jclass hashmapClass = env->FindClass("java/util/HashMap");
    // 获取HashMap类entrySet()方法ID
    entrySetMID = env->GetMethodID(hashmapClass, "entrySet", "()Ljava/util/Set;");

    jclass setClass = env->FindClass("java/util/Set");
    iteratorMID = env->GetMethodID(setClass,"iterator","()Ljava/util/Iterator;");

    jclass iteratorClass = env->FindClass("java/util/Iterator");
    hasNextMID = env->GetMethodID(iteratorClass, "hasNext", "()Z");
    // 获取Iterator类中next()方法ID
    // 用于读取HashMap中的每一条数据
    nextMID = env->GetMethodID(iteratorClass, "next", "()Ljava/lang/Object;");

    jclass entryClass = env->FindClass("java/util/Map$Entry");
    getKeyMID = env->GetMethodID(entryClass, "getKey", "()Ljava/lang/Object;");
    getValueMID = env->GetMethodID(entryClass, "getValue", "()Ljava/lang/Object;");

    jclass integerClass = env->FindClass("java/lang/Integer");
    valueMID = env->GetMethodID(integerClass, "intValue", "()I");

    env->DeleteLocalRef(hashmapClass);
    env->DeleteLocalRef(setClass);
    env->DeleteLocalRef(iteratorClass);
    env->DeleteLocalRef(entryClass);
    env->DeleteLocalRef(integerClass);

}


JNINativeMethod nativeMethod[] = {
        {"dynamicLog", "()V", (void*)nativeDynamicLog},
        {"setConfigs", "(Ljava/util/HashMap;)V", (void*)nativeSetConfigs}
        };

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *jvm, void *reserved) {

    JNIEnv *env;
    if (jvm->GetEnv((void**) &env, JNI_VERSION_1_4) != JNI_OK) {

        return -1;
    }
    LOGD("JNI_OnLoad comming");
    jclass clz = env->FindClass(java_class);
    env->RegisterNatives(clz, nativeMethod, sizeof(nativeMethod)/sizeof(nativeMethod[0]));
    initNative(env);
    return JNI_VERSION_1_4;
}