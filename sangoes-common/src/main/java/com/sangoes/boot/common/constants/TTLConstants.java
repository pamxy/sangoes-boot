package com.sangoes.boot.common.constants;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * 过期时间常量
 *
 * @author jerrychir
 * @date 2018 2018/12/19 7:41 PM
 */
public interface TTLConstants {
    /**
     * 5分钟过期
     */
    public static final int TTL_FIVE = 300;

    /**
     * 10分钟过期
     */
    public static final int TTL_TEN = 600;

    /**
     * 半小时过期
     */
    public static final int TTL_HALF_HOUR = 1800;

    /**
     * 1小时过期
     */
    public static final int TTL_HOUR = 3600;

    /**
     * 6小时过期
     */
    public static final int TTL_SIX_HOUR = 21600;

    /**
     * 半天(12小时)过期
     */
    public static final int TTL_HALF_DAY = 43200;

    /**
     * 1天(24小时)过期
     */
    public static final int TTL_DAY = 86400;

    /**
     * 2天(48小时)过期
     */
    public static final int TTL_TWO_DAY = 172800;

    /**
     * 一周(7天)过期
     */
    public static final int TTL_WEEK = 604800;

    /**
     * 一个月(30)过期
     */
    public static final int TTL_MONTH = 2592000;

    /**
     * 永久
     */
    public static final int TTL_FOREVER = 0;

    /**
     * list列表
     */
    public static final String LISTS = "lists";

    /**
     * page列表
     */
    public static final String PAGES = "pages";

    /**
     * info列表
     */
    public static final String INFOS = "infos";
}
