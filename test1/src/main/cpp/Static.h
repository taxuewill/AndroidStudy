//
// Created by Will on 4/25/21.
//

#ifndef ANDROIDSTUDY_STATIC_H
#define ANDROIDSTUDY_STATIC_H
#include "java_and_jni.h"
#include <mutex>


namespace will_test{
    extern JniBridge * gJniBridge;
    extern std::mutex gJniBridgeMutex;
}


#endif //ANDROIDSTUDY_STATIC_H
