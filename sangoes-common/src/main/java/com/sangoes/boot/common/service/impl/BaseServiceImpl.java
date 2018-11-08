package com.sangoes.boot.common.service.impl;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangoes.boot.common.service.IBaseService;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.common.utils.page.PageQuery;
import com.sangoes.boot.common.utils.page.Pagination;

import org.springframework.stereotype.Service;

import cn.hutool.core.util.StrUtil;

/**
 * BaseServiceImpl
 */
@Service
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements IBaseService<T> {

    /**
     * 获取分页信息
     */
    @Override
    public PageData<T> selectPage(PageQuery pageQuery) {
        // 构建分页
        Page<T> page = new Page<>(pageQuery.getCurrent(), pageQuery.getPageSize());
        // 查询条件
        QueryWrapper<T> queryWrapper = this.addQueryCondtion(pageQuery);
        // 查询
        IPage<T> selectPage = baseMapper.selectPage(page, queryWrapper);
        // 返回结果
        Pagination pagination = new Pagination(selectPage.getTotal(), selectPage.getSize(), selectPage.getCurrent());
        return new PageData<T>(pagination, selectPage.getRecords());
    }

    /**
     * 添加查询条件
     * 
     * @param pageQuery
     */
    private QueryWrapper<T> addQueryCondtion(PageQuery pageQuery) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
        if (pageQuery.entrySet().size() > 0) {
            for (Map.Entry<String, Object> entry : pageQuery.entrySet()) {
                // 获取key value
                String key = entry.getKey();
                Object value = entry.getValue();
                if (StrUtil.equals(key, "sorter")) {
                    // 排序
                    String[] values = value.toString().split("_");
                    if (values.length > 1) {
                        String symbolCase = StrUtil.toSymbolCase(values[0], '_');
                        if (StrUtil.equals(values[1], "descend")) {
                            queryWrapper.orderByDesc(symbolCase);
                        }
                        if (StrUtil.equals(values[1], "ascend")) {
                            queryWrapper.orderByAsc(symbolCase);
                        }
                    }
                } else {
                    // 模糊查找
                    queryWrapper.like(key, value);
                }
            }
        }
        return queryWrapper;
    }

}