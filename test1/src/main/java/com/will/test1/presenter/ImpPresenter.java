package com.will.test1.presenter;

public class ImpPresenter implements IPresenter {

    IView iView;

    public ImpPresenter(IView iView){
        this.iView = iView;
    }


    @Override
    public void onEvent(int event) {

    }
}
