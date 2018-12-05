package com.zhxh.reader.runalone.application;

import com.xlab.core.app.BaseApplication;
import com.luojilab.component.componentlib.router.Router;

/**
 * Created by zhxh on 2018/6/20.
 */

public class ReaderApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        //如果isRegisterCompoAuto为false，则需要通过反射加载组件
        Router.registerComponent("com.zhxh.share.applike.ShareApplike");
        Router.registerComponent("com.zhxh.share.kotlin.applike.KotlinApplike");
    }
}
