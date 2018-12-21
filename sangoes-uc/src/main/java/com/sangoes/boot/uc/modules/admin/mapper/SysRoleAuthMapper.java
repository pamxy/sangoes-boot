package com.sangoes.boot.uc.modules.admin.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sangoes.boot.uc.modules.admin.entity.SysRoleAuth;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 角色权限中间表 Mapper 接口
 * </p>
 *
 * @author jerrychir
 * @since 2018-11-14
 */
@Repository
public interface SysRoleAuthMapper extends BaseMapper<SysRoleAuth> {
    /**
     * 根据role主键查询权限主键
     * 
     * @param roleId
     * @return
     */
    List<Long> listAuthIdByRoleId(@Param("roleId") Long roleId, @Param("menuId") Long menuId);
}
