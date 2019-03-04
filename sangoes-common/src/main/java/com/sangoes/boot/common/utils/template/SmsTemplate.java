package com.sangoes.boot.common.utils.template;

import lombok.Data;

import java.io.Serializable;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * 短信消息模板
 *
 * @author jerrychir
 * @date 2019 2019/3/4 1:46 PM
 */
@Data
public class SmsTemplate implements Serializable {
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 组装后的模板内容JSON字符串
     */
    private String context;

    /**
     * 短信类型(验证码或者通知短信)
     * code:验证码
     * 暂时不用，留着后面存数据库备用吧
     */
    private String type;
    /**
     * 短信签名
     */
    private String signName;
    /**
     * 短信模板
     */
    private String template;

    /**
     * 无参构造
     */
    public SmsTemplate() {
    }

    /**
     * 有参构造函数
     *
     * @param mobile
     * @param context
     * @param signName
     * @param template
     */
    public SmsTemplate(String mobile, String context, String signName, String template) {
        this.mobile = mobile;
        this.context = context;
        this.signName = signName;
        this.template = template;
    }
}
