package com.zhxh.base.config;

import java.io.Serializable;

/**
 * Created by zhxh on 2018/10/25
 */
public class ActivityRequestContext implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private int requestID;
    private int type;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }
}
