package com.zhxh.share.core;

/**
 * <p><b>Package:</b> com.zhxh.share.bean </p>
 * <p><b>Project:</b> XJimuDemo </p>
 * <p><b>Classname:</b> IShareApi </p>
 * <p><b>Description:</b> TODO </p>
 * Created by zhxh on 2018/7/6.
 */
public interface IShareApi {
    void share(AbsShareBean bean);

    void share2QQ(String content);

    void share2Wechat(String content);
}
