package com.sangoes.boot.uc.modules.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sangoes.boot.uc.modules.admin.entity.SysAuth;
import com.sangoes.boot.uc.modules.admin.vo.AuthVo;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author jerrychir
 * @since 2018-11-10
 */
public interface SysAuthMapper extends BaseMapper<SysAuth> {

    /**
     * 根据角色编码获取权限
     *
     * @param roleCode
     * @return
     */
    List<AuthVo> listAuthByRoleCode(String roleCode);

}
