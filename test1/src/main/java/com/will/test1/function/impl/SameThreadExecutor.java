package com.will.test1.function.impl;

import com.will.test1.function.intf.IExecutor;

public class SameThreadExecutor implements IExecutor {
    @Override
    public void prepare(String threadName) {

    }

    @Override
    public void execRunnable(Runnable runnable) {
        runnable.run();
    }

    @Override
    public void quit() {

    }
}
