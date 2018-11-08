package com.sangoes.boot.uc.modules.admin.service.impl;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sangoes.boot.common.exception.HandleErrorException;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.common.service.impl.BaseServiceImpl;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.common.utils.page.PageQuery;
import com.sangoes.boot.uc.modules.admin.dto.RoleDto;
import com.sangoes.boot.uc.modules.admin.entity.SysRole;
import com.sangoes.boot.uc.modules.admin.mapper.SysRoleMapper;
import com.sangoes.boot.uc.modules.admin.service.ISysRoleService;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.hutool.core.util.ObjectUtil;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author jerrychir
 * @since 2018-11-04
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    /**
     * 添加角色
     */
    @Override
    public Result<String> addRole(RoleDto roleDto) {
        // 判断roleCode是否重复
        SysRole roleDB = this
                .getOne(new QueryWrapper<SysRole>().lambda().eq(SysRole::getRoleCode, roleDto.getRoleCode()));
        if (ObjectUtil.isNotNull(roleDB)) {
            throw new HandleErrorException("角色编码已存在");
        }
        // 复杂
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(roleDto, sysRole);
        // 保存
        boolean save = this.save(sysRole);
        if (!save) {
            throw new HandleErrorException("添加失败");
        }
        return Result.success("添加成功");
    }

    /**
     * 分页角色
     */
    @Override
    public Result<PageData<SysRole>> selectRolePage(Map<String, Object> params) {
        PageData<SysRole> selectPage = this.selectPage(new PageQuery(params));
        return Result.success(selectPage, "成功");
    }

}
