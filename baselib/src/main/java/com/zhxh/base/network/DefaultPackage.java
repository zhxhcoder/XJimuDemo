package com.zhxh.base.network;

import com.zhxh.base.utils.CommonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by zhxh on 2018/11/1
 */
public class DefaultPackage extends DataPackage {

    /**
     * post请求状态
     */
    private boolean isPost;
    /**
     * 加密状态
     */
    private boolean isEncryptBoo;
    /**
     * 请求参数数据集
     */
    private List<KeyValueData> keyValueDatas;

    public DefaultPackage(int requestID, List<KeyValueData> keyValueDatas) {

        this.requestID = requestID;

        this.keyValueDatas = keyValueDatas;

        this.isPost = false;
    }

    public DefaultPackage(int requestID, List<KeyValueData> keyValueDatas, boolean isPost) {

        this.requestID = requestID;

        this.keyValueDatas = keyValueDatas;

        this.isPost = isPost;
    }

    public DefaultPackage(int requestID, List<KeyValueData> keyValueDatas, boolean isPostBoo, boolean isEncryptBoo) {

        this(requestID, keyValueDatas, isPostBoo);
        this.isEncryptBoo = isEncryptBoo;
    }

    @Override
    public int headerSize() {

        return HEADER_EMPTY;
    }

    @Override
    public String getRequestMethod() {

        if (isPost) {

            return REQUEST_POST;
        } else {

            return REQUEST_GET;
        }
    }

    @Override
    public String getRequestData() {

        if (keyValueDatas == null || keyValueDatas.isEmpty()) {
            return null;
        }

        if (isEncryptBoo) {

            JSONObject jsonObj = new JSONObject();

            for (KeyValueData data : keyValueDatas) {

                try {

                    jsonObj.put(data.getKey(), data.getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return jsonObj.toString();

        } else {

            StringBuilder param = new StringBuilder();

            for (KeyValueData data : keyValueDatas) {
                param.append(PARAMETER_SEPARATOR)
                        .append(data.getKey())
                        .append(NAME_VALUE_SEPARATOR)
                        .append(data.getValue());
            }

            return param.toString().substring(1);
        }
    }

    @Override
    public Object getData() throws Exception {

        return CommonUtils.toUTF8Str(tempData);
    }

}
