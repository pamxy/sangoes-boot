package com.sangoes.boot.uc.modules.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sangoes.boot.common.exception.HandleErrorException;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.common.service.impl.BaseServiceImpl;
import com.sangoes.boot.uc.modules.admin.dto.MenuDto;
import com.sangoes.boot.uc.modules.admin.entity.SysMenu;
import com.sangoes.boot.uc.modules.admin.mapper.SysMenuMapper;
import com.sangoes.boot.uc.modules.admin.service.ISysMenuService;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.hutool.core.util.ObjectUtil;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author jerrychir
 * @since 2018-11-09
 */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
    /**
     * 添加菜单
     */
    @Override
    public Result<String> addMenu(MenuDto menuDto) {
        // 判断menuCode是否重复
        SysMenu menuDB = this
                .getOne(new QueryWrapper<SysMenu>().lambda().eq(SysMenu::getMenuCode, menuDto.getMenuCode()));
        if (ObjectUtil.isNotNull(menuDB)) {
            throw new HandleErrorException("菜单编码已存在");
        }
        // 复制
        SysMenu sysMenu = new SysMenu();
        BeanUtils.copyProperties(menuDto, sysMenu);
        // 保存
        boolean save = this.save(sysMenu);
        if (!save) {
            throw new HandleErrorException("添加失败");
        }
        return Result.success("添加成功");
    }

}
