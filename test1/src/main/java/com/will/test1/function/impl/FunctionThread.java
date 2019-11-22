package com.will.test1.function.impl;

import com.will.test1.function.abs.BaseFunction;
import com.will.test1.function.abs.BaseFunctionFactory;

public class FunctionThread extends BaseFunctionFactory {

    public static final String FUN_SAMETHREAD = "";

    private FunctionThread(){}

    static class Inner{
        static final FunctionThread mFunctionThread = new FunctionThread();
    }

    public static FunctionThread getInstance(){
        return Inner.mFunctionThread;
    }

    @Override
    protected BaseFunction createFunction(String type) {
        return null;
    }
}
