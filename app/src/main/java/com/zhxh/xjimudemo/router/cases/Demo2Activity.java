package com.zhxh.xjimudemo.router.cases;

import android.os.Bundle;
import android.widget.TextView;

import com.luojilab.router.facade.annotation.Autowired;
import com.luojilab.router.facade.annotation.RouteNode;
import com.zhxh.xjimudemo.router.UiRouterDemoActivity;

/**
 * <p><b>Package:</b> com.zhxh.xjimudemo.router.cases </p>
 * <p><b>Project:</b> JIMU </p>
 * <p><b>Classname:</b> Demo2Activity </p>
 * <p><b>Description:</b> TODO </p>
 * Created by zhxh on 01/04/2018.
 */
@RouteNode(path = "/uirouter/demo/2", desc = "使用bundle传递参数")
public class Demo2Activity extends TestActivity {
    private static Bundle bundle = new Bundle();

    static {
        bundle.putString("foo", "foo string");
        bundle.putString("EXTRA_STR_BAR", "bar string");
    }

    @Autowired() //不指定名称时将使用变量名，若被混淆可能出现问题，
                // 建议使用name指定key，参考bar的使用
    String foo;

    @Autowired(name = "EXTRA_STR_BAR")
    String bar;

    public static final UiRouterDemoActivity.Case aCase
            = new UiRouterDemoActivity.Case(false,
            "使用bundle传递参数",
            "JIMU://app/uirouter/demo/2",
            bundle);

    @Override
    protected void displayInfo(TextView textView) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("使用bundle传递参数成功\r\n");
        stringBuilder.append("foo:").append(foo).append("\r\n");
        stringBuilder.append("bar:").append(bar).append("\r\n");

        textView.setText(stringBuilder.toString());
    }
}
