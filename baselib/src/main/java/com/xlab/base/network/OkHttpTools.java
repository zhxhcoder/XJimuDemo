package com.xlab.base.network;

import com.xlab.base.config.CommonDataManager;
import com.xlab.base.exception.ApplicationException;
import com.xlab.base.exception.ApplicationTimeOutException;
import com.xlab.base.utils.CommonUtils;
import com.xlab.base.utils.LogUtils;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by zhxh on 2018/11/1
 */
public class OkHttpTools {

    public static final int timeOutExcepState = 1;

    public static final int appExcepState = 2;
    private TempDataCache tempDataCache = null;

    public OkHttpTools() {
    }

    /***
     * post加密请求
     * @param requestID
     * @return
     */
    public static boolean isPostRequest(int requestID) {

        return true;
    }

    private static String getUrlParams() {
        return "s=" + CommonDataManager.CHANNEL + "&version=" + CommonDataManager.VERSIONNAME + "&packtype=" + CommonDataManager.PACKTYPE;

    }

    public void httpGet(String url, final DataPackage dataPackage)
            throws ApplicationException, ApplicationTimeOutException {

        if (CommonUtils.isNull(url))
            return;

        String queryString = dataPackage.getRequestData();


        if (!CommonUtils.isNull(queryString)) {

            url += ("?" + queryString + "&" + getUrlParams());
        } else {

            url += ("?" + getUrlParams());
        }

        LogUtils.d("geturl", url);

        try {

            tempDataCache = new TempDataCache();

            tempDataCache.state = 0;
            tempDataCache.responseData = OkHttpUtil.getStringFromServer(url, dataPackage.getRequestID());

            tempDataCache.setDataToPackage(dataPackage);

        } catch (SocketTimeoutException sex) {

//			sex.printStackTrace();
            throw new ApplicationTimeOutException(sex.getMessage());

        } catch (UnknownHostException uex) {

//			uex.printStackTrace();
            throw new ApplicationException(uex.getMessage());

        } catch (Exception e) {

//			e.printStackTrace();
            throw new ApplicationException(e.getMessage());

        } finally {

            tempDataCache = null;
        }
    }

    public void httpPost(String url, final DataPackage dataPackage)
            throws ApplicationException, ApplicationTimeOutException {

        if (CommonUtils.isNull(url))
            return;

        try {

            url = url + "?" + getUrlParams();

            MediaType contentType = MediaType.parse("application/x-www-form-urlencoded");

            String content = "";

            String queryString = dataPackage.getRequestData();
            if (!CommonUtils.isNull(queryString)) {

                int requestID = dataPackage.getRequestID();

                if (isPostRequest(requestID)) {
                    content = queryString;
                }

                LogUtils.d("posturl", url + "?" + queryString);
            }

            RequestBody requestBody = RequestBody.create(contentType, content);

            tempDataCache = new TempDataCache();

            tempDataCache.state = 0;
            tempDataCache.responseData = OkHttpUtil.post(url, requestBody, dataPackage.getRequestID());

            tempDataCache.setDataToPackage(dataPackage);

        } catch (SocketTimeoutException sex) {
//			sex.printStackTrace();
            throw new ApplicationTimeOutException(sex.getMessage());

        } catch (UnknownHostException uex) {
//			uex.printStackTrace();
            throw new ApplicationException(uex.getMessage());

        } catch (Exception e) {
//			e.printStackTrace();
            throw new ApplicationException(e.getMessage());

        } finally {

            tempDataCache = null;
        }
    }

    private class TempDataCache {

        int state = -1;

        byte[] responseData = null;

        void setDataToPackage(final DataPackage dataPackage) {

            dataPackage.setState(state);
            dataPackage.setTempData(responseData);
        }
    }

}
