package com.zhxh.base.network;

import com.google.gson.Gson;
import com.zhxh.base.utils.LogUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhxh on 2018/10/31
 */
public class RxHttp {

    private final static String TAG = "RxHttp";

    //最终请求函数
    public static <T> Disposable call(boolean isPost, boolean isEncryptBoo, int requestCommand, List<KeyValueData> params, Class<T> t, CallResult<T> callResult, CallError callError) {
        return Observable.create((ObservableOnSubscribe<String>) e -> {
            if (!e.isDisposed()) {
                DefaultPackage pkg = new DefaultPackage(
                        requestCommand, params, isPost, isEncryptBoo);
                Network.processPackage(pkg);
                String dataStr = (String) pkg.getData();
                e.onNext(dataStr);
                e.onComplete();

                LogUtils.d(TAG, "rxUrl\n" + pkg.getRequestUrl() + "?" + pkg.getRequestData());
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(dataStr -> {
                    LogUtils.d(TAG, "rxResponse\n" + dataStr);
                    return new Gson().fromJson(dataStr, t);
                })
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(T data) throws Exception {
                        if (data != null) {
                            callResult.onResult(data);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        if (callError != null) {
                            callError.onError(throwable);
                        }
                    }
                });
    }

    /*get请求返回实体类*/
    public static <T> Disposable call(int requestCommand, List<KeyValueData> params, Class<T> t, CallResult<T> callResult) {
        return call(false, false, requestCommand, params, t, callResult, null);
    }

    /*get请求返回实体类-捕捉异常*/
    public static <T> Disposable call(int requestCommand, List<KeyValueData> params, Class<T> t, CallResult<T> callResult, CallError callError) {
        return call(false, false, requestCommand, params, t, callResult, callError);
    }

    /*post请求返回实体类*/
    public static <T> Disposable call(boolean isPost, boolean isEncryptBoo, int requestCommand, List<KeyValueData> params, Class<T> t, CallResult<T> callResult) {
        return call(isPost, isEncryptBoo, requestCommand, params, t, callResult, null);
    }


    //最终请求函数
    public static Disposable call(boolean isPost, boolean isEncryptBoo, int requestCommand, List<KeyValueData> params, CallResult<String> callResult, CallError callError) {
        return Observable.create((ObservableOnSubscribe<String>) e -> {
            if (!e.isDisposed()) {
                DefaultPackage pkg = new DefaultPackage(
                        requestCommand, params, isPost, isEncryptBoo);
                Network.processPackage(pkg);
                String dataStr = (String) pkg.getData();
                e.onNext(dataStr);
                e.onComplete();
                LogUtils.d(TAG, "rxUrl\n" + pkg.getRequestUrl() + "?" + pkg.getRequestData());
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String data) throws Exception {
                        if (data != null) {
                            callResult.onResult(data);
                            LogUtils.d(TAG, "rxResponse\n" + data);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (callError != null) {
                            callError.onError(throwable);
                        }
                        throwable.printStackTrace();
                    }
                });
    }

    /*get请求返回字符串*/
    public static Disposable call(int requestCommand, List<KeyValueData> params, CallResult<String> callResult) {
        return call(false, false, requestCommand, params, callResult, null);
    }
    /*get请求返回字符串-捕捉异常*/

    public static Disposable call(int requestCommand, List<KeyValueData> params, CallResult<String> callResult, CallError callError) {
        return call(false, false, requestCommand, params, callResult, callError);
    }

    /*post请求返回字符串*/
    public static Disposable call(boolean isPost, boolean isEncryptBoo, int requestCommand, List<KeyValueData> params, CallResult<String> callResult) {
        return call(isPost, isEncryptBoo, requestCommand, params, callResult, null);
    }


    /*get请求返回delay字符串*/
    public static <T> Disposable callDelay(long delay, int requestCommand, List<KeyValueData> params, Class<T> t, CallResult<T> callResult) {
        return callDelay(false, delay, requestCommand, params, t, callResult);
    }

    public static <T> Disposable callDelay(boolean isPost, long delay, int requestCommand, List<KeyValueData> params, Class<T> t, CallResult<T> callResult) {
        return Observable.just(delay).delay(delay, TimeUnit.MILLISECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                call(isPost, false, requestCommand, params, t, callResult, null);
            }
        });
    }

    public static <T> CompositeDisposable callLoop(boolean isPost, long delay, long interval, int requestCommand, List<KeyValueData> params, Class<T> t, CallResult<T> callResult) {
        final CompositeDisposable disposables = new CompositeDisposable();
        disposables.add(Observable.interval(delay, interval, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Long>() {

                    @Override
                    public void onNext(Long value) {
                        call(isPost, false, requestCommand, params, t, callResult, null);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                }));

        return disposables;
    }

    /*自动刷新默认-get请求*/
    public static <T> CompositeDisposable callLoop(long delay, long interval, int requestCommand, List<KeyValueData> params, Class<T> t, CallResult<T> callResult) {
        return callLoop(false, delay, interval, requestCommand, params, t, callResult);
    }

    /*自动刷新-默认get请求 默认立即开始请求*/
    public static <T> CompositeDisposable callLoop(long interval, int requestCommand, List<KeyValueData> params, Class<T> t, CallResult<T> callResult) {
        return callLoop(false, 0, interval, requestCommand, params, t, callResult);
    }

    public interface CallResult<T> {
        void onResult(@NonNull T t);
    }

    public interface CallError {
        void onError(Throwable throwable);
    }
}
