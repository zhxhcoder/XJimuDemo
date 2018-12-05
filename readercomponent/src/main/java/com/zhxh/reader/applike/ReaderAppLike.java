package com.zhxh.reader.applike;

import com.luojilab.component.componentlib.applicationlike.IApplicationLike;
import com.luojilab.component.componentlib.applicationlike.RegisterCompManual;
import com.luojilab.component.componentlib.router.Router;
import com.luojilab.component.componentlib.router.ui.UIRouter;
import com.xlab.componentservice.readerbook.ReadBookService;
import com.zhxh.reader.serviceimpl.ReadBookServiceImplKotlin;

/**
 * Created by zhxh on 2018/6/15.
 */
@RegisterCompManual
public class ReaderAppLike implements IApplicationLike {

    Router router = Router.getInstance();
    UIRouter uiRouter = UIRouter.getInstance();

    @Override
    public void onCreate() {
        uiRouter.registerUI("reader");
//        router.addService(ReadBookService.class.getSimpleName(), new ReadBookServiceImpl());
        router.addService(ReadBookService.class.getSimpleName(), new ReadBookServiceImplKotlin());
    }

    @Override
    public void onStop() {
        uiRouter.unregisterUI("reader");
        router.removeService(ReadBookService.class.getSimpleName());
    }
}
