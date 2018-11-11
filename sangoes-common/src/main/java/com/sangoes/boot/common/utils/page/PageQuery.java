package com.sangoes.boot.common.utils.page;

import java.util.LinkedHashMap;
import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright (c) 2018,四川传球网络科技有限公司 br> All rights reserved.<br>
 * <p>
 * 描述：封装查询参数<br>
 *
 * @author jc
 * @date 25 /02/2018 4:17 PM Date
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PageQuery extends LinkedHashMap<String, Object> {

    private static final long serialVersionUID = 1L;
    // 当前页码
    private long current = 1;
    // 每页条数
    private long pageSize = 10;
    // 菜单主键
    private long menuId;
    // 升序
    boolean isAsc = true;
    // 排序名称
    String sorter;

    /**
     * Instantiates a new Query.
     *
     * @param params the params
     */
    public PageQuery(Map<String, Object> params) {
        this.putAll(params);
        // 分页参数
        Object currentObj = params.get("current");
        Object sizeObj = params.get("pageSize");
        // 菜单
        Object menuIdObj = params.get("menuId");
        // 排序
        Object sorterObj = params.get("sorter");
        // 当前页
        if (ObjectUtil.isNotNull(currentObj)) {
            this.current = Long.valueOf(currentObj.toString());
        }
        //
        if (ObjectUtil.isNotNull(sizeObj)) {
            this.pageSize = Long.valueOf(sizeObj.toString());
        }
        // 菜单
        if (ObjectUtil.isNotNull(menuIdObj)) {
            this.menuId = Long.valueOf(menuIdObj.toString());
        }
        // 排序
        if (ObjectUtil.isNotNull(sorterObj)) {
            String[] values = sorterObj.toString().split("_");
            this.sorter = StrUtil.toSymbolCase(values[0], '_');
            if (StrUtil.equals(values[1], "descend")) {
                this.isAsc = false;
            }
        }
        // 移除
        this.remove("current");
        this.remove("pageSize");
        this.remove("menuId");
        this.remove("sorter");
    }

}
