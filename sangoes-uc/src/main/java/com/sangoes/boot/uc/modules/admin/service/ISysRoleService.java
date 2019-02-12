package com.sangoes.boot.uc.modules.admin.service;

import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.common.service.IBaseService;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.uc.modules.admin.dto.RoleDto;
import com.sangoes.boot.uc.modules.admin.entity.SysRole;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author jerrychir
 * @since 2018-11-04
 */
public interface ISysRoleService extends IBaseService<SysRole> {
    /**
     * 添加角色
     *
     * @param roleDto
     * @return
     */
    Result<String> addRole(RoleDto roleDto);

    /**
     * 角色分页
     *
     * @param params
     * @return
     */
    Result<PageData<SysRole>> selectRolePage(Map<String, Object> params);

    /**
     * 查询绑定菜单权限
     *
     * @param roleId
     * @return
     */
    Result<Map<String, Object>> infoBindMenu(Long roleId);

    /**
     * 查询绑定权限
     *
     * @param menuId
     * @return
     */
    Result<Map<String, Object>> infoBindAuth(Long roleId, Long menuId);

    /**
     * 绑定菜单权限
     */
    void bindMenuAuth(RoleDto roleDto);

    /**
     * 删除角色
     *
     * @param roleDto
     */
    void deleteRole(RoleDto roleDto);

    /**
     * 批量删除角色
     *
     * @param roleDto
     */
    void batchDeleteRole(RoleDto roleDto);

    /**
     * 更新角色
     *
     * @param roleDto
     */
    void updateRole(RoleDto roleDto);

    /**
     * 查询所有角色
     *
     * @return
     */
    List<SysRole> getAllRoles();


}
