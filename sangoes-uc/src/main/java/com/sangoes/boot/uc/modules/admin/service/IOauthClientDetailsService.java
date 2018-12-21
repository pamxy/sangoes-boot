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
}
