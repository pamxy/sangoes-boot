package com.sangoes.boot.common.utils;

import cn.hutool.core.util.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrayUtils
 */
public class ArrayUtils extends ArrayUtil {
    /**
     * long list 转 string list
     *
     * @param list
     * @return
     */
    public static List<String> longListToStringList(List<Long> list) {
        List<String> strList = new ArrayList<>();
        Object[] array = list.toArray();
        for (Object var : array) {
            strList.add(var.toString());
        }
        return strList;
    }

    /**
     * object[] 转 List<String></String>
     *
     * @param objects
     * @return
     */
    public static List<String> objectArrayToListString(Object[] objects) {
        List<String> strList = new ArrayList<>();
        for (Object var : objects) {
            strList.add(var.toString());
        }
        return strList;
    }
}