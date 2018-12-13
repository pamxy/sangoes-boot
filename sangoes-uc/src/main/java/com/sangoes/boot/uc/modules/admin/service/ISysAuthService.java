package com.sangoes.boot.uc.modules.admin.service;

import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.common.service.IBaseService;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.uc.modules.admin.dto.AuthDto;
import com.sangoes.boot.uc.modules.admin.entity.SysAuth;
import com.sangoes.boot.uc.modules.admin.vo.AuthVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jerrychir
 * @since 2018-11-10
 */
public interface ISysAuthService extends IBaseService<SysAuth> {

    /**
     * 添加权限
     *
     * @param authDto
     * @return
     */
    Result<String> addAuth(AuthDto authDto);

    /**
     * 根据menuId权限分页
     *
     * @param params
     * @return
     */
    Result<PageData<SysAuth>> pageAuthByMenuId(Map<String, Object> params);

    /**
     * 根据角色编码查询权限
     *
     * @param roleCode
     * @return
     */
    List<AuthVo> listAuthByRoleCode(String roleCode);

    /**
     * 更新权限
     *
     * @param authDto
     */
    void updateAuth(AuthDto authDto);

    /**
     * 删除权限
     *
     * @param authDto
     */
    void deleteAuth(AuthDto authDto);

    /**
     * 批量删除权限
     *
     * @param authDto
     */
    void batchDeleteAuth(AuthDto authDto);
}
