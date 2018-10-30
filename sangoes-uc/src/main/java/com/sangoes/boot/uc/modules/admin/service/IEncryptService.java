package com.sangoes.boot.uc.modules.admin.service;

/**
 * Copyright (c) 2018
 * 加密 服务类
 *
 * @author jerrychir
 * @date 2018/10/30 7:45 PM
 */
public interface IEncryptService {

    /**
     * 根据手机号码创建RSA的公钥私钥并存入redis
     *
     * @param mobile
     * @return 公钥
     */
    public String createRSAKeyByMobile(String mobile);
}
