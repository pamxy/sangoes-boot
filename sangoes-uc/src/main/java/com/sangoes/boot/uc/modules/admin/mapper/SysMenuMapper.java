package com.sangoes.boot.uc.modules.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sangoes.boot.uc.modules.admin.entity.SysMenu;
import com.sangoes.boot.uc.modules.admin.vo.MenuVo;

import java.util.List;

/**
 * <p>
 * 菜单 Mapper 接口
 * </p>
 *
 * @author jerrychir
 * @since 2018-11-09
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据rolecode查询菜单权限
     *
     * @param roleCode
     * @return
     */
    List<MenuVo> findMenuAuthByRoleCode(String roleCode);

}
