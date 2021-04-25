//
// Created by Will on 4/25/21.
//

#include "Static.h"
#include "java_and_jni.h"
#include <mutex>

namespace will_test{

    JniBridge * gJniBridge;
    std::mutex gJniBridgeMutex;


}