//
// Created by Will on 4/25/21.
//

#ifndef ANDROIDSTUDY_JAVA_AND_JNI_H
#define ANDROIDSTUDY_JAVA_AND_JNI_H



namespace will_test{

    class JniBridge{
    public:
        static JniBridge * self();
        JniBridge();

        void testLocalRef();
        void testGlobalRef();
        void testWeakGlobalRef();

    };


}

#endif //ANDROIDSTUDY_JAVA_AND_JNI_H
