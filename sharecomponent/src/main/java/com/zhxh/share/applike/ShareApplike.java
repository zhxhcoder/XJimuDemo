package com.zhxh.share.applike;

import com.luojilab.component.componentlib.applicationlike.IApplicationLike;
import com.luojilab.component.componentlib.router.ui.UIRouter;

/**
 * Created by zhxh on 2018/6/15.
 */

public class ShareApplike implements IApplicationLike {

    UIRouter uiRouter = UIRouter.getInstance();

    @Override
    public void onCreate() {
        uiRouter.registerUI("share");
    }

    @Override
    public void onStop() {
        uiRouter.unregisterUI("share");
    }
}
