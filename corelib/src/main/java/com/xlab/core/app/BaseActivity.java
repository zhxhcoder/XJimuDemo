package com.xlab.core.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.luojilab.component.componentlib.service.AutowiredService;
import com.xlab.base.config.ActivityRequestContext;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by zhxh on 2018/8/7
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected CompositeDisposable mDisposables = new CompositeDisposable();

    /**
     * 设置布局
     */
    protected abstract void setLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AutowiredService.Factory.getSingletonImpl().autowire(this);

        setLayout();
        getInitBundle();
    }

    protected ActivityRequestContext initRequest;

    /**
     * 获取Intent传递过来的请求参数
     */
    public void getInitBundle() {

        Intent intent = getIntent();

        if (intent == null)
            return;

        Bundle bundle = intent.getExtras();

        if (bundle == null)
            return;

        initRequest = (ActivityRequestContext)
                bundle.getSerializable("initRequest");

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposables != null) {
            mDisposables.clear();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //返回
    protected void goBack() {
        finish();
    }
}
