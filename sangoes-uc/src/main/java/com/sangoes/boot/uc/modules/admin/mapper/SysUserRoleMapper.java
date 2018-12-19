package com.sangoes.boot.uc.modules.admin.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sangoes.boot.uc.modules.admin.entity.SysUserRole;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author jerrychir
 * @since 2018-11-04
 */
@Repository
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 根据user id 查询 role id
     * 
     * @param id
     * @return
     */
    List<Long> listRoleIdByUserId(@Param("id") Long id);
}
