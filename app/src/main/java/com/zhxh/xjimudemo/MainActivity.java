package com.zhxh.xjimudemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.luojilab.component.componentlib.router.Router;
import com.luojilab.component.componentlib.router.ui.UIRouter;
import com.luojilab.router.facade.annotation.RouteNode;
import com.xlab.base.utils.ToastManager;
import com.xlab.componentservice.readerbook.ReadBookService;
import com.xlab.core.app.BaseActivity;
import com.xlab.core.app.BaseApplication;

@RouteNode(path = "/main", desc = "首页")
public class MainActivity extends BaseActivity implements View.OnClickListener {

    Fragment fragment;
    FragmentTransaction ft;

    Button installReadBookBtn;
    Button uninstallReadBtn;

    @Override
    protected void setLayout() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        installReadBookBtn = findViewById(R.id.install_share);
        uninstallReadBtn = findViewById(R.id.uninstall_share);
        installReadBookBtn.setOnClickListener(this);
        uninstallReadBtn.setOnClickListener(this);

        findViewById(R.id.ui_router_demos).setOnClickListener(this);
        findViewById(R.id.ui_msg_demos).setOnClickListener(this);
        showFragment();
    }


    private void showFragment() {
        if (fragment != null) {
            ft = getSupportFragmentManager().beginTransaction();
            ft.remove(fragment).commit();
            fragment = null;
        }
        Router router = Router.getInstance();
        if (router.getService(ReadBookService.class.getSimpleName()) != null) {
            ReadBookService service = (ReadBookService) router.getService(ReadBookService.class.getSimpleName());
            fragment = service.getReadBookFragment();
            ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.tab_content, fragment).commitAllowingStateLoss();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.install_share:
                Router.registerComponent("com.zhxh.share.applike.ShareApplike");
                Router.registerComponent("com.zhxh.share.kotlin.applike.KotlinApplike");
                break;
            case R.id.uninstall_share:
                Router.unregisterComponent("com.zhxh.share.applike.ShareApplike");
                Router.unregisterComponent("com.zhxh.share.kotlin.applike.KotlinApplike");
                break;
            case R.id.ui_router_demos:
                UIRouter.getInstance().openUri(MainActivity.this, "JIMU://app/uirouter/demo", null);
                break;
            case R.id.ui_msg_demos:
                UIRouter.getInstance().openUri(MainActivity.this, "JIMU://app/msg/demo/1", null);
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            ToastManager.show(BaseApplication.getAppContext(), data.getStringExtra("result"));
        }
    }
}
