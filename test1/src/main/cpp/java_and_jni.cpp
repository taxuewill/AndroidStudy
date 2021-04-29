//
// Created by Will on 4/25/21.
//

#include "java_and_jni.h"
#include "android_log.h"

#include "Static.h"
#include <mutex>
#define TAG  "JniBridge"

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

