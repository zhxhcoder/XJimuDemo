package com.zhxh.base.exception;

/**
 * Created by zhxh on 2018/11/1
 */
public class ApplicationException extends Exception {

    private static final long serialVersionUID = 999788617835501445L;

    /**
     * 构造器
     *
     * @param messageId 信息ID
     */
    public ApplicationException(String messageId) {
        super(messageId);
    }

}