package com.zhxh.base.exception;

/**
 * Created by zhxh on 2018/11/1
 */
public class ApplicationTimeOutException extends Exception {

    private static final long serialVersionUID = -1774325754914387942L;

    /**
     * 构造器
     *
     * @param messageId 信息ID
     */
    public ApplicationTimeOutException(String messageId) {
        super(messageId);
    }

}