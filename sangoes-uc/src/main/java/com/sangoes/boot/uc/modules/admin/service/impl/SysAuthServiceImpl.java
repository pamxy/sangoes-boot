package com.sangoes.boot.uc.modules.admin.service.impl;

import com.sangoes.boot.uc.modules.admin.dto.AuthDto;
import com.sangoes.boot.uc.modules.admin.entity.SysAuth;
import com.sangoes.boot.uc.modules.admin.mapper.SysAuthMapper;
import com.sangoes.boot.uc.modules.admin.service.ISysAuthService;

import java.util.Map;

import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.common.service.impl.BaseServiceImpl;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.common.utils.page.PageQuery;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jerrychir
 * @since 2018-11-10
 */
@Service
public class SysAuthServiceImpl extends BaseServiceImpl<SysAuthMapper, SysAuth> implements ISysAuthService {

    /**
     * 添加菜单
     */
    @Override
    public Result<String> addAuth(AuthDto authDto) {
        // 复制
        SysAuth sysAuth = new SysAuth();
        BeanUtils.copyProperties(authDto, sysAuth);
        boolean save = this.save(sysAuth);
        if (!save) {
            return Result.failed("添加失败");
        }
        return Result.success("添加成功");
    }

    /**
     * 根据menuId权限分页
     */
    @Override
    public Result<PageData<SysAuth>> pageAuthByMenuId(Map<String, Object> params, Long menuId) {
        PageData<SysAuth> selectPage = this.selectPage(new PageQuery(params));
        return Result.success(selectPage, "成功");
    }

}
