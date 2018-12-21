package com.sangoes.boot.uc.modules.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sangoes.boot.common.exception.HandleErrorException;
import com.sangoes.boot.common.service.impl.BaseServiceImpl;
import com.sangoes.boot.common.utils.AuthUtils;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.common.utils.page.PageQuery;
import com.sangoes.boot.uc.modules.admin.dto.OAuthDto;
import com.sangoes.boot.uc.modules.admin.entity.OauthClientDetails;
import com.sangoes.boot.uc.modules.admin.mapper.OauthClientDetailsMapper;
import com.sangoes.boot.uc.modules.admin.service.IOauthClientDetailsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 授权表 服务实现类
 * </p>
 *
 * @author jerrychir
 * @since 2018-12-20
 */
@Service
public class OauthClientDetailsServiceImpl extends BaseServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements IOauthClientDetailsService {
    /**
     * 保存授权
     *
     * @param oauthDto
     */
    @Override
    public void saveOAuth(OAuthDto oauthDto) {
        // clientId查询
        OauthClientDetails oauthDB = this.getOne(new QueryWrapper<OauthClientDetails>().lambda().eq(OauthClientDetails::getClientId, oauthDto.getClientId()));
        if (ObjectUtil.isNotNull(oauthDB)) {
            throw new HandleErrorException("授权已存在");
        }
        // 复制
        OauthClientDetails oauth = new OauthClientDetails();
        BeanUtils.copyProperties(oauthDto, oauth);
        oauth.setCreator(AuthUtils.getUserName());
        oauth.setCreatorId(AuthUtils.getUserId());
        // 保存
        boolean flag = this.save(oauth);
        if (!flag) {
            throw new HandleErrorException("保存失败");
        }
    }

    /**
     * 授权分页
     *
     * @param params
     * @return
     */
    @Override
    public PageData<OauthClientDetails> selectOAuthPage(Map<String, Object> params) {
        return this.selectPage(new PageQuery(params));
    }

    /**
     * 删除授权
     *
     * @param oauthDto
     */
    @Override
    public void deleteOAuth(OAuthDto oauthDto) {
        // 删除授权
        boolean flag = this.removeById(oauthDto.getOauthId());
        if (!flag) {
            throw new HandleErrorException("删除授权失败");
        }
    }

    /**
     * 批量删除授权
     *
     * @param oauthDto
     */
    @Override
    public void batchDeleteOAuth(OAuthDto oauthDto) {
        // 批量删除授权
        boolean flag = this.removeByIds(oauthDto.getOauthIds());
        if (!flag) {
            throw new HandleErrorException("批量删除失败");
        }
    }

    /**
     * 更新授权
     *
     * @param oauthDto
     */
    @Override
    public void updateOAuth(OAuthDto oauthDto) {
        // 查询授权
        OauthClientDetails oauthDB = this.getById(oauthDto.getId());
        if (ObjectUtil.isNull(oauthDB)) {
            throw new HandleErrorException("授权为空或已删除");
        }
        // 复制
        OauthClientDetails oauth = new OauthClientDetails();
        BeanUtil.copyProperties(oauthDto, oauth, CopyOptions.create().setIgnoreNullValue(true));
        // 更新
        boolean flag = this.updateById(oauth);
        if (!flag) {
            throw new HandleErrorException("更新失败");
        }
    }
}
