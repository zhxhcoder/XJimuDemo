package com.xlab.core.app;

import android.app.Application;
import android.support.annotation.Nullable;

public class BaseApplication extends Application {

    private static BaseApplication mAppContext;

    @Nullable
    public static Application getAppContext() {
        return mAppContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this;
    }
}
