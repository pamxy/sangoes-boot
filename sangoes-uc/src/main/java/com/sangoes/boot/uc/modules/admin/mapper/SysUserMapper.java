package com.sangoes.boot.uc.modules.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sangoes.boot.uc.modules.admin.entity.SysUser;
import com.sangoes.boot.uc.modules.admin.vo.UserDetailsVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author jerrychir
 * @since 2018-10-29
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    UserDetailsVo selectUserDetailsByUsername(String username);

    /**
     * 根据手机号查询
     *
     * @param mobile
     * @return
     */
    UserDetailsVo userDetailsByMobile(String mobile);

    /**
     * 部门成员分页
     *
     * @param page
     * @param departId
     * @return
     */
    IPage<SysUser> listDepartMembers(@Param("page") Page<SysUser> page, @Param("departId") Long departId);

    /**
     * 根据roleCode查询用户
     *
     * @param roleCode
     * @return
     */
    List<SysUser> listByRoleCode(@Param("roleCode") String roleCode);
}
