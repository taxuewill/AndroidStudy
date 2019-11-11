package com.will.test1.constant;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({Color.RED,Color.BLUE})
@Retention(RetentionPolicy.CLASS)
public @interface Color {
     int RED = 1;
     int BLUE = 2;
}
