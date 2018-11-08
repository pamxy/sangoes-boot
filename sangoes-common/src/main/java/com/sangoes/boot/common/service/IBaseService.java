package com.sangoes.boot.common.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangoes.boot.common.msg.PageData;

/**
 * IBaseService
 */
public interface IBaseService<T> extends IService<T> {
    /**
     * 获取分页信息
     * 
     * @param params
     * @return
     */
    PageData<T> selectPage(Map<String, Object> params);
}