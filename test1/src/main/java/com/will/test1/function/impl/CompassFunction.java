package com.will.test1.function.impl;

import com.will.test1.function.abs.BaseFunction;
import com.will.test1.function.intf.IExecutor;

public class CompassFunction extends BaseFunction {


    public CompassFunction(IExecutor iExecutor) {
        super(iExecutor);
    }

    @Override
    public void doCmd(int cmd) {
        switch (cmd){
            case 1:
                handle1();
                break;

        }
    }

    void handle1(){

    }
}
