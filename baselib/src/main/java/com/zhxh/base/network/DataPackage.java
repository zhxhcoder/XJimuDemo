package com.zhxh.base.network;

import com.zhxh.base.utils.CommonUtils;

import java.net.URLEncoder;

/**
 * Created by zhxh on 2018/11/1
 */
public abstract class DataPackage {

    /**
     * 属性分割符
     */
    protected static final String PARAMETER_SEPARATOR = "&";

    /**
     * 键值分割符
     */
    protected static final String NAME_VALUE_SEPARATOR = "=";

    /**
     * GET请求
     */
    public static final String REQUEST_GET = "GET";

    /**
     * POST请求
     */
    public static final String REQUEST_POST = "POST";

    /**
     * 返回数据包含有效数据长度,对应股票数,行情状态
     */
    public static final int HEADER_LEN_NUM_STATE = 3;

    /**
     * 返回数据包含有效数据长度,行情状态
     */
    public static final int HEADER_LEN_STATE = 2;

    /**
     * 返回数据包含有效数据长度
     */
    public static final int HEADER_LEN = 1;

    /**
     * 返回数据特殊数据
     */
    public static final int HEADER_EMPTY = 0;

    /**
     * 请求ID
     */
    protected int requestID;

    /**
     * cookie信息
     */
    protected String cookie;

    /**
     * 请求返回状态
     */
    protected int state = -1;

    /**
     * 请求返回数据
     */
    protected byte[] tempData;

    /**
     * 单条记录分割
     */
    protected String COMPARTA = ";";

    /**
     * 记录数据分割
     */
    protected String COMPARTB = ",";

    /**
     * 返回头信息大小默认不包含头信息 返回 0
     * 大盘分析、涨跌排行状态位[有效数据长度,对应版块股票数,行情状态(停盘、开盘等信息)]
     * <p>
     * see HEADER_LEN_NUM_STATE
     * 搜索状态位[有效数据长度,搜索股票数(如果1为精确个股返回分时行情、其他为搜索结果)]
     * see HEADER_LEN_STATE
     * 分时、K线为[有效数据长度,行情状态]
     * see HEADER_EMPTY
     * 其他为默认网页返回
     */
    public abstract int headerSize();

    /**
     * 获取请求方法GET POST
     */
    public abstract String getRequestMethod();

    /**
     * 获取请求数据 由子类根据不同协议组
     */
    public abstract String getRequestData();

    /**
     * 获取返回数据
     */
    public abstract Object getData() throws Exception;

    /**
     * 请求url
     */
    protected String requestUrl;

    private String tag;

    /**
     * 设置请求返回状态
     *
     * @param state 请求返回状态
     */
    public void setState(int state) {

        this.state = state;
    }

    /**
     * 设置请求返回数据
     *
     * @param tempData 服务器返回的数据
     */
    public void setTempData(byte[] tempData) {

        this.tempData = tempData;
    }

    /**
     * 返回请求ID
     */
    public int getRequestID() {

        return requestID;
    }

    /**
     * 返回请求状态默认返回异常
     * 还包括清盘、停盘等状态
     *
     * @return 请求状态
     */
    public int getState() {

        return state;
    }

    /**
     * 返回Cookie信息
     */
    public String getCookie() {

        return cookie;
    }

    protected String[][] requestSplit() throws Exception {

        if (tempData == null)
            return null;

        String responseData = CommonUtils.toUTF8Str(tempData);

        tempData = null;

        if (responseData == null || "".equals(responseData))
            return null;

        String[][] result = null;

        String[] tempSplit = CommonUtils.split(responseData, COMPARTA);

        if (tempSplit != null) {

            result = new String[tempSplit.length][];

            for (int ii = 0; ii < result.length; ii++) {

                result[ii] = CommonUtils.split(tempSplit[ii], COMPARTB);
            }

        }

        return result;
    }

    /***
     * 获取URLEncoder值
     * @param value
     * @return
     */
    protected String getString(String value) {

        String tempValue = "";

        if (null == value)
            return tempValue;

        try {

            tempValue = URLEncoder.encode(value, "utf-8");

        } catch (Exception ex) {

            tempValue = "";
        }

        return tempValue;
    }

    protected String getValue(String value) {

        String tempValue = "";

        if (null == value)
            return tempValue;

        return value;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
