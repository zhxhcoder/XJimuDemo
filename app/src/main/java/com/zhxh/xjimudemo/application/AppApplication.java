package com.zhxh.xjimudemo.application;

import com.xlab.core.app.BaseApplication;
import com.luojilab.component.componentlib.log.ILogger;
import org.github.jimu.msg.EventManager;
import com.luojilab.component.componentlib.router.Router;
import com.luojilab.component.componentlib.router.ui.UIRouter;
import com.zhxh.xjimudemo.msg.AppComponentEventManager;

/**
 * Created by zhxh on 2018/6/15.
 */

public class AppApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ILogger.logger.setDefaultTag("JIMU");
        UIRouter.enableDebug();
//        EventManager.appendMapper("com.zhxh.xjimudemo.application", MainProcessMsgService.class);
//        EventManager.appendMapper("com.zhxh.xjimudemo.application:remote", RemoteMsgService.class);

        EventManager.init(this);

        UIRouter.getInstance().registerUI("app");

        //如果isRegisterCompoAuto为false，则需要通过反射加载组件
        Router.registerComponent("com.zhxh.reader.applike.ReaderAppLike");
//        Router.registerComponent("com.zhxh.share.applike.ShareApplike");

        Router.getInstance().addService(AppComponentEventManager.class.getSimpleName(),
                EventManager.create(AppComponentEventManager.class));

    }
}
