package com.zhxh.share.bean;

import com.zhxh.share.core.AbsShareBean;

/**
 * <p><b>Package:</b> com.zhxh.share.bean </p>
 * <p><b>Project:</b> XJimuDemo </p>
 * <p><b>Classname:</b> AppShareBean </p>
 * <p><b>Description:</b> 该Demo演示：大多数情况下我们并不想将一些类下沉如何处理 </p>
 * Created by zhxh on 2018/7/6.
 */
public class AppShareBean extends AbsShareBean {
    private String content;

    public AppShareBean(int shareVia, String content) {
        super(shareVia);
        this.content = content;
    }

    @Override
    protected String getShareContent() {
        return content;
    }
}
