package com.zhxh.base.network;

import com.zhxh.base.config.CommonDataManager;
import com.zhxh.base.utils.CommonUtils;
import com.zhxh.base.utils.LogUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zhxh on 2018/11/1
 */
public class OkHttpUtil {
    /**
     * 连接或读取超时
     */
    public static final int CONNECTION_TIMEOUT = 60;

    private static final OkHttpClient mOkHttpClient = new OkHttpClient();

    /***
     * get请求发起
     * @param request
     * @return
     * @throws IOException
     */
    public static Response execute(Request request) throws IOException {

        Builder builder = mOkHttpClient.newBuilder();
        builder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
//		builder.retryOnConnectionFailure(false);
//		builder.followSslRedirects(false);

        return builder.build().newCall(request).execute();
    }

    /***
     * Get获取返回结果
     * @param url
     * @return
     * @throws IOException
     */
    public static byte[] getStringFromServer(String url, int requestID) throws IOException {

        Request request = new Request.Builder().tag(requestID).url(url)
//				.addHeader("Connection", "close")//关闭长连接
                .addHeader("packtype", "" + CommonDataManager.PACKTYPE)
                .addHeader("s", CommonDataManager.CHANNEL)
                .addHeader("version", CommonDataManager.VERSIONNAME)
                .addHeader("userToken", CommonDataManager.userToken)
                .addHeader("screenWidth", "" + CommonDataManager.screenWight)
                .addHeader("screenHeight", "" + CommonDataManager.screenHeight)
                .build();

        Response response = execute(request);
        if (response.isSuccessful()) {
            byte[] responseData = response.body().bytes();
            LogUtils.d("", "********START**********");
            try {
                LogUtils.d("body", (responseData == null) ? "null" :
                        CommonUtils.toUTF8Str(responseData));
            } catch (Exception e) {
                e.printStackTrace();
            }
            LogUtils.d("", "********END*************");
            return responseData;
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /***
     * post请求
     * @param url
     * @param body
     * @return
     * @throws IOException
     */
    public static byte[] post(String url, RequestBody body, int requestID) throws IOException {

        Request request = new Request.Builder().tag(requestID).url(url)
//				.addHeader("Connection", "close")//关闭长连接
                .addHeader("packtype", "" + CommonDataManager.PACKTYPE)
                .addHeader("s", CommonDataManager.CHANNEL)
                .addHeader("version", CommonDataManager.VERSIONNAME)
                .addHeader("userToken", CommonDataManager.userToken)
                .addHeader("screenWidth", "" + CommonDataManager.screenWight)
                .addHeader("screenHeight", "" + CommonDataManager.screenHeight)
                .post(body).build();

        Response response = execute(request);

        if (response.isSuccessful()) {
            byte[] responseData = response.body().bytes();
            LogUtils.d("", "********START POST**********");
            try {
                LogUtils.d("body", (responseData == null) ? "null" :
                        CommonUtils.toUTF8Str(responseData));
            } catch (Exception e) {
                e.printStackTrace();
            }
            LogUtils.d("", "********END  POST*************");
            return responseData;
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /***
     * 取消请求
     * @param requestID 请求ID
     */
    public static void cancelRequest(int requestID) {

        if (mOkHttpClient == null)
            return;

        synchronized (mOkHttpClient.dispatcher().getClass()) {

            try {
                for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
                    int tag = (Integer) call.request().tag();
                    if (tag == requestID)
                        call.cancel();
                }

                for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
                    int tag = (Integer) call.request().tag();
                    if (tag == requestID)
                        call.cancel();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }
}
