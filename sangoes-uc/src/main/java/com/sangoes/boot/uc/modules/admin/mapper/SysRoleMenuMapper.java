package com.sangoes.boot.uc.modules.admin.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sangoes.boot.uc.modules.admin.entity.SysRoleMenu;

import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 角色菜单中间表 Mapper 接口
 * </p>
 *
 * @author jerrychir
 * @since 2018-11-13
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
    /**
     * 根据role主键查询菜单主键
     * 
     * @param id
     * @return
     */
    List<Long> listMenuIdByRoleId(@Param("id") Long id);

}
