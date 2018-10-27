package com.sangoes.boot.common.xss;

import com.sangoes.boot.common.exception.HandleErrorException;
import org.apache.commons.lang3.StringUtils;

/**
 * Copyright (c) 2018
 * SQL过滤
 * @author jerrychir
 * @date 2018/10/28 12:08 AM
 */
public class SQLFilter {

    /**
     * SQL注入过滤
     *
     * @param str 待验证的字符串
     */
    public static String sqlInject(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alter", "drop"};

        //判断是否包含非法字符
        for (String keyword : keywords) {
            if (str.indexOf(keyword) != -1) {
                throw new HandleErrorException("包含非法字符");
            }
        }

        return str;
    }
}
