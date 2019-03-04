package com.sangoes.boot.common.core.service;

import com.sangoes.boot.common.utils.template.SmsTemplate;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 *
 * @author jerrychir
 * @date 2019 2019/3/4 1:51 PM
 */
public interface ISmsService {

    /**
     * 发送单体短信
     *
     * @param smsTemplate
     */
    boolean sendSms(SmsTemplate smsTemplate);

}
