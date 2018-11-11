package com.sangoes.boot.uc.modules.admin.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.common.service.impl.BaseServiceImpl;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.common.utils.page.PageQuery;
import com.sangoes.boot.common.utils.page.Pagination;
import com.sangoes.boot.uc.modules.admin.dto.AuthDto;
import com.sangoes.boot.uc.modules.admin.entity.SysAuth;
import com.sangoes.boot.uc.modules.admin.mapper.SysAuthMapper;
import com.sangoes.boot.uc.modules.admin.service.ISysAuthService;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.hutool.core.util.ObjectUtil;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jerrychir
 * @since 2018-11-10
 */
@Service
public class SysAuthServiceImpl extends BaseServiceImpl<SysAuthMapper, SysAuth> implements ISysAuthService {

    /**
     * 添加菜单
     */
    @Override
    public Result<String> addAuth(AuthDto authDto) {
        // 判断authCode是否重复
        SysAuth authDB = this
                .getOne(new QueryWrapper<SysAuth>().lambda().eq(SysAuth::getAuthCode, authDto.getAuthCode()));
        if (ObjectUtil.isNotNull(authDB)) {
            return Result.failed("权限编码已存在");
        }
        // 复制
        SysAuth sysAuth = new SysAuth();
        BeanUtils.copyProperties(authDto, sysAuth);
        boolean save = this.save(sysAuth);
        if (!save) {
            return Result.failed("添加失败");
        }
        return Result.success("添加成功");
    }

    /**
     * 根据menuId权限分页
     */
    @Override
    public Result<PageData<SysAuth>> pageAuthByMenuId(Map<String, Object> params) {
        // 转换
        PageQuery pageQuery = new PageQuery(params);

        // 构建分页
        Page<SysAuth> page = new Page<>(pageQuery.getCurrent(), pageQuery.getPageSize());
        // 查询条件
        QueryWrapper<SysAuth> queryWrapper = this.pageQueryCondtion(pageQuery);
        long menuId = pageQuery.getMenuId();
        if (ObjectUtil.isNotNull(menuId)) {
            queryWrapper.lambda().eq(SysAuth::getMenuId, menuId);
        }
        // 查询
        IPage<SysAuth> selectPage = baseMapper.selectPage(page, queryWrapper);
        // 返回结果
        Pagination pagination = new Pagination(selectPage.getTotal(), selectPage.getSize(), selectPage.getCurrent());
        PageData<SysAuth> pageData = new PageData<SysAuth>(pagination, selectPage.getRecords());
        return Result.success(pageData, "成功");
    }

}
