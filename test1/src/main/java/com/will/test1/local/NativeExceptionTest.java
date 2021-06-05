package com.will.test1.local;

public class NativeExceptionTest {

    native void doIt() throws IllegalArgumentException;

    void callback() throws NullPointerException{
        throw new NullPointerException("NativeExceptionTest.callback");
    }
}
