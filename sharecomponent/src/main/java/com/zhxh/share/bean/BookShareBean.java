package com.zhxh.share.bean;

import com.zhxh.share.core.AbsShareBean;

/**
 * <p><b>Package:</b> com.zhxh.share.bean </p>
 * <p><b>Project:</b> XJimuDemo </p>
 * <p><b>Classname:</b> BookShareBean </p>
 * <p><b>Description:</b> TODO </p>
 * Created by zhxh on 2018/7/6.
 */
public class BookShareBean extends AbsShareBean {
    private String book;
    private String author;
    private String county;
    private String reason;


    public BookShareBean(int shareVia, String book, String author, String county, String reason) {
        super(shareVia);
        this.book = book;
        this.author = author;
        this.county = county;
        this.reason = reason;
    }

    @Override
    protected String getShareContent() {
        return toString();
    }

    @Override
    public String toString() {
        return "BookShareBean{" +
                "book='" + book + '\'' +
                ", author='" + author + '\'' +
                ", county='" + county + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
