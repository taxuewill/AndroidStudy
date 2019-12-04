package com.will.test1.receiver.intf;

public interface IScreenListener {
    int SCREEN_ON = 1;
    int SCREEN_OFF = 2;
    void onScreenChange(int state);
}
