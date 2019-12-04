package com.will.test1.util;

import java.util.TimerTask;


public abstract class ExecutableTask extends TimerTask {

    public String TAG = this.getClass().getSimpleName();
    boolean canceled;

    @Override
    public boolean cancel() {
        canceled = true;
        return super.cancel();
    }

    @Override
    public void run() {
        if (canceled) {
            return;
        }
        execute();
    }

    public abstract void execute();
}
