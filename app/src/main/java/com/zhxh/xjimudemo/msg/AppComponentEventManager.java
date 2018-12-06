package com.zhxh.xjimudemo.msg;

import com.zhxh.xjimudemo.msg.event.EventA;
import com.zhxh.xjimudemo.msg.event.EventB;

import org.github.jimu.msg.bean.ConsumerMeta;
import org.github.jimu.msg.notation.AriseProcess;
import org.github.jimu.msg.notation.Consumer;
import org.github.jimu.msg.notation.Event;

/**
 * <p><b>Package:</b> com.zhxh.xjimudemo.msg </p>
 * <p><b>Project:</b> XJimuDemo </p>
 * <p><b>Classname:</b> AppComponentEventManager </p>
 * <p><b>Description:</b> TODO </p>
 * Created by zhxh on 2018/7/30.
 */
public interface AppComponentEventManager {
    @AriseProcess()
    @Event(clz = EventA.class)
    void subscribeEventA(@Consumer ConsumerMeta meta);

    @AriseProcess(pa = ":remote")
    @Event(clz = EventB.class)
    void subscribeEventB(@Consumer ConsumerMeta meta);
}
