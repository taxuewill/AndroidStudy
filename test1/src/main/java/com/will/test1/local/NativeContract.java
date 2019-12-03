package com.will.test1.local;

import java.util.HashMap;

public class NativeContract {

    public static native void dynamicLog();

    public static native void setConfigs(HashMap<String,Integer> configs);
}
