package com.sangoes.boot.common.utils;

import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.util.ArrayUtil;

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
}