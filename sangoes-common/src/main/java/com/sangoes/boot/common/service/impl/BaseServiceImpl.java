package com.sangoes.boot.common.service.impl;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangoes.boot.common.msg.PageData;
import com.sangoes.boot.common.msg.Pagination;
import com.sangoes.boot.common.service.IBaseService;

import org.springframework.stereotype.Service;

import cn.hutool.core.util.ObjectUtil;

/**
 * BaseServiceImpl
 */
@Service
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements IBaseService<T> {

    /**
     * 获取分页信息
     */
    @Override
    public PageData<T> selectPage(Map<String, Object> params) {
        // 构建分页
        long current = 1;
        long pageSize = 10;
        Object currentObj = params.get("current");
        Object sizeObj = params.get("pageSize");
        if (ObjectUtil.isNotNull(currentObj)) {
            current = Long.valueOf(currentObj.toString());
        }
        if (ObjectUtil.isNotNull(sizeObj)) {
            pageSize = Long.valueOf(sizeObj.toString());
        }
        // 构建分页
        Page<T> user = new Page<>(current, pageSize);
        // 查询条件
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
        if (params.entrySet().size() > 0) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                queryWrapper.like(entry.getKey(), entry.getValue());
            }
        }
        // 查询
        IPage<T> selectPage = baseMapper.selectPage(user, queryWrapper);
        // 返回结果
        Pagination pagination = new Pagination(selectPage.getTotal(), selectPage.getSize(), selectPage.getCurrent());
        return new PageData<T>(pagination, selectPage.getRecords());
    }

}