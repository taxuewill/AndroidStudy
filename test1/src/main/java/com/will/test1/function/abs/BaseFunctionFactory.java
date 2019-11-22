package com.will.test1.function.abs;

public abstract class BaseFunctionFactory {

    public BaseFunction getFunction(String type){
        BaseFunction baseFunction = createFunction(type);
        baseFunction.prepare();
        return baseFunction;
    }

    protected abstract BaseFunction createFunction(String type);
}
