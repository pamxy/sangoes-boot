package com.sangoes.boot.uc.modules.admin.mapper;

import com.sangoes.boot.uc.modules.admin.entity.SysUser;
import com.sangoes.boot.uc.modules.admin.vo.UserDetailsVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author jerrychir
 * @since 2018-10-29
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 根据用户名查询
     * 
     * @param username
     * @return
     */
    UserDetailsVo selectUserDetailsByUsername(String username);

}
