package com.xlab.core.app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xlab.base.config.ActivityRequestContext;
import com.xlab.core.view.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by zhxh on 2018/8/7
 */
public abstract class BaseFragment extends Fragment {
    protected BaseActivity baseActivity;

    protected CompositeDisposable mDisposables = new CompositeDisposable();

    protected View rootView;
    protected ActivityRequestContext initRequest;
    private Unbinder unbinder;
    private LoadingDialog loadingDialog;

    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initView(View view);

    /**
     * 请求数据
     */
    public abstract void requestData();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        baseActivity = (BaseActivity) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false);
            unbinder = ButterKnife.bind(this, rootView);
        }
        unbinder = ButterKnife.bind(this, rootView);
        getInitBundle();
        initView(rootView);
        return rootView;
    }

    /**
     * 获取Intent传递过来的请求参数
     */
    public void getInitBundle() {
        Intent intent = baseActivity.getIntent();
        if (intent == null)
            return;
        Bundle bundle = intent.getExtras();

        if (bundle == null)
            return;
        initRequest = (ActivityRequestContext)
                bundle.getSerializable("initRequest");

    }

    protected Resources getResource() {
        return getContext().getResources();
    }

    public void onNetWorkChange(boolean isConnected) {

    }

    /**
     * 加载框
     */
    public void showLoadingDialog(String info) {
        showLoadingDialog(info, false);
    }

    public void showLoadingDialog(String info, boolean autoCancel) {
        try {
            if (loadingDialog == null || !loadingDialog.isShowing()) {
                loadingDialog = new LoadingDialog.Builder(getContext())
                        .setIconType(LoadingDialog.Builder.ICON_TYPE_LOADING)
                        .setTipWord(info)
                        .create();
                loadingDialog.show();

                if (autoCancel) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            hideLoading();
                        }
                    }, 1000);
                }
            } else {
                hideLoading();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void showLoading() {
        try {
            if (loadingDialog == null || !loadingDialog.isShowing()) {
                loadingDialog = new LoadingDialog.Builder(getContext())
                        .setIconType(LoadingDialog.Builder.ICON_TYPE_LOADING)
                        .setTipWord("正在处理")
                        .create();
                loadingDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 取消加载框
     */
    public void hideLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null)
            unbinder.unbind();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mDisposables != null) {
            mDisposables.clear();
        }
    }
}