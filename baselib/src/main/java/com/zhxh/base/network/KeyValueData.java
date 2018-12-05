package com.zhxh.base.network;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zhxh on 2018/11/1
 */
public class KeyValueData implements Serializable {

    @SerializedName(value = "Key", alternate = {"key"})
    private String key;
    @SerializedName(value = "Value", alternate = {"value"})
    private String value;

    public KeyValueData(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public KeyValueData(String key, int value) {
        this.key = key;
        this.value = String.valueOf(value);
    }

    public KeyValueData(int key, String value) {
        this.key = String.valueOf(key);
        this.value = value;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
