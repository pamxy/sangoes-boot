package com.sangoes.boot.uc.modules.admin.service;

import com.sangoes.boot.common.service.IBaseService;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.uc.modules.admin.dto.OAuthDto;
import com.sangoes.boot.uc.modules.admin.entity.OauthClientDetails;

import java.util.Map;

/**
 * <p>
 * 授权表 服务类
 * </p>
 *
 * @author jerrychir
 * @since 2018-12-20
 */
public interface IOauthClientDetailsService extends IBaseService<OauthClientDetails> {

    /**
     * 保存授权
     *
     * @param oauthDto
     */
    void saveOAuth(OAuthDto oauthDto);

    /**
     * 授权分页
     *
     * @param params
     * @return
     */
    PageData<OauthClientDetails> selectOAuthPage(Map<String, Object> params);

    /**
     * 删除授权
     *
     * @param oauthDto
     */
    void deleteOAuth(OAuthDto oauthDto);

    /**
     * 批量删除授权
     *
     * @param oauthDto
     */
    void batchDeleteOAuth(OAuthDto oauthDto);

    /**
     * 更新授权
     *
     * @param oauthDto
     */
    void updateOAuth(OAuthDto oauthDto);

}
