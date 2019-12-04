package com.will.test1.function.intf;


import com.will.test1.function.param.InParam;
import com.will.test1.function.param.OutParam;

public interface IFunction {

    String getName();

    OutParam runWithParam(InParam inParam);


}
