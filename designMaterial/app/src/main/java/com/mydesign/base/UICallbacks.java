package com.mydesign.base;

public interface UICallbacks {

    int getLayoutId();

    Class getViewModel();

    BaseNavigator getNavigatorReference();

    void onBinding();
}
