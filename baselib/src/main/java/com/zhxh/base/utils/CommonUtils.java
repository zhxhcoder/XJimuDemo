package com.zhxh.base.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Vector;

/**
 * Created by zhxh on 2018/11/1
 */
public class CommonUtils {
    public static boolean isNull(String checkStr) {

        boolean result = false;

        if (null == checkStr) {

            result = true;
        } else {
            if (checkStr.length() == 0) {

                result = true;
            }
        }
        return result;
    }

    public static boolean isNull(List<?> list) {

        boolean result = false;

        if (null == list) {

            result = true;
        } else {
            if (list.size() == 0) {

                result = true;
            }
        }
        return result;
    }

    /**
     * 将字节数组转换为UTF-8字符串
     *
     * @param source 字节数据源
     * @return String UTF-8字符串
     * @throws UnsupportedEncodingException
     */
    public static String toUTF8Str(byte[] source) throws Exception {

        if (source != null) {

            try {

                return new String(source, "UTF-8");

            } catch (UnsupportedEncodingException e) {

                throw new Exception("Unsupported UTF8 Encoding Exception" + e);

            }

        }

        return null;

    }

    /***
     * 获取URLEncoder值
     * @param value
     * @return
     */
    public static String toUTF8Str(String value) {

        String tempValue = "";

        if (null == value)
            return tempValue;

        try {

            tempValue = URLEncoder.encode(value, "utf-8");

        } catch (Exception ex) {

            tempValue = "";
        }

        return tempValue;
    }

    /**
     * 根据给定规则拆分字符串
     *
     * @param source 待拆分字符串
     * @param split  拆分规则
     */
    public static String[] split(String source, String split) {

        if (isNull(source))
            return null;

        if (isNull(split))
            return new String[]{source};

        Vector<String> vector = new Vector<String>();

        int startIndex = 0;

        int endIndex = -1;

        while (true) {

            if ((endIndex = source.indexOf(split, startIndex)) != -1) {

                vector.add(source.substring(startIndex, endIndex));

                startIndex = endIndex + split.length();

            } else {

                if (startIndex <= source.length()) {

                    vector.add(source.substring(startIndex));

                }

                break;
            }

        }

        return vector.toArray(new String[vector.size()]);
    }
}
