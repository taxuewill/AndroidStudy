//
// Created by Will on 5/16/21.
//

#include "jni_utils.h"

void JNU_ThrowByName(JNIEnv * env,const char * name,const char * msg){
    jclass cls = env->FindClass(name);
    if(cls != NULL){
        env->ThrowNew(cls,msg);
    }
    env->DeleteLocalRef(cls);
}