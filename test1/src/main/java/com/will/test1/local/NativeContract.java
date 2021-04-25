package com.will.test1.local;

import java.util.HashMap;

public class NativeContract {

    public static native void dynamicLog();

    public static native void setConfigs(HashMap<String,Integer> configs);

    public static native void testLocalRef();

    public static native void testGlobalRef();

    public static native void testWeakGlobalRef();
}
