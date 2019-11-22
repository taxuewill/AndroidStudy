package com.will.test1.function.intf;

public interface IExecutor {

    void prepare(String threadName);
    void execRunnable(Runnable runnable);
    void quit();
}
