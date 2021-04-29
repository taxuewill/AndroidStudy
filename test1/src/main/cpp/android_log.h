//
// Created by Will on 4/29/21.
//

#ifndef ANDROIDSTUDY_ANDROID_LOG_H
#define ANDROIDSTUDY_ANDROID_LOG_H
#include <android/log.h>
//help ref https://blog.csdn.net/shaosunrise/article/details/79838297

#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__) // 定义LOGD类型
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG ,__VA_ARGS__) // 定义LOGI类型
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,TAG ,__VA_ARGS__) // 定义LOGW类型
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG ,__VA_ARGS__) // 定义LOGE类型
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,TAG ,__VA_ARGS__) // 定义LOGF类型
#endif //ANDROIDSTUDY_ANDROID_LOG_H
