package com.sangoes.boot.common.dto;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * 分页dto
 *
 * @author jerrychir
 * @date 2019 2019/2/13 2:50 PM
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PageDto {


    /**
     * 当前页码
     */
    private long current = 1;

    /**
     * 每页条数
     */
    private long pageSize = 10;

    /**
     * 升序
     */
    private boolean isAsc = true;

    /**
     * 排序名称
     */
    private String sorter;

    /**
     * 处理sorter
     *
     * @param sorter
     */
//    public void setSorter(String sorter) {
//        String[] values = sorter.split("_");
//        this.sorter = StrUtil.toSymbolCase(values[0], '_');
//        if (StrUtil.equals(values[1], "descend")) {
//            this.isAsc = false;
//        }
//
//    }
}
