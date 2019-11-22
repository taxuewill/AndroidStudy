package com.will.test1.function.abs;

import com.will.test1.function.intf.IExecutor;

public abstract class BaseFunction {

    IExecutor mExecutor;

    public BaseFunction(IExecutor iExecutor){
        mExecutor = iExecutor;
    }

    public final void prepare(){
        mExecutor.prepare(this.getClass().getSimpleName());
    }

    public abstract void doCmd(int cmd);
}
