package com.xj.arouter;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
    }
}
