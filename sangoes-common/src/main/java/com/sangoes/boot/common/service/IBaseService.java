package com.sangoes.boot.common.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.common.utils.page.PageQuery;

/**
 * IBaseService
 */
public interface IBaseService<T> extends IService<T> {
    /**
     * 获取分页信息
     * 
     * @param pageQuery
     * @return
     */
    PageData<T> selectPage(PageQuery pageQuery);

    /**
     * 分页获取query
     * 
     * @param pageQuery
     * @return
     */
    QueryWrapper<T> pageQueryCondtion(PageQuery pageQuery);
}