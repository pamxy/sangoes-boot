package com.sangoes.boot.common.core.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.sangoes.boot.common.core.config.AliyunSmsPropertiesConfig;
import com.sangoes.boot.common.core.service.ISmsService;
import com.sangoes.boot.common.utils.template.SmsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * 短信
 *
 * @author jerrychir
 * @date 2019 2019/3/4 2:23 PM
 */
@Service
public class AliyunSmsServiceImpl implements ISmsService {

    @Autowired
    private AliyunSmsPropertiesConfig smsPropertiesConfig;
    /**
     * 返回成功
     */
    private final static int SUCCESS_CODE = 200;

    /**
     * 发送单体短信
     *
     * @param smsTemplate
     */
    @Override
    public boolean sendSms(SmsTemplate smsTemplate) {
        // 创建DefaultProfile
        DefaultProfile profile = DefaultProfile.getProfile(smsPropertiesConfig.getRegionId(), smsPropertiesConfig.getAccessKeyId(), smsPropertiesConfig.getAccessKeySecret());
        // 构建IAcsClient
        IAcsClient client = new DefaultAcsClient(profile);
        // 创建CommonRequest
        CommonRequest request = new CommonRequest();

        //request.setProtocol(ProtocolType.HTTPS);

        request.setMethod(MethodType.POST);
        request.setDomain(smsPropertiesConfig.getDomain());
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        // 必填:待发送手机号
        request.putQueryParameter("PhoneNumbers", smsTemplate.getMobile());
        // 必填:短信签名-可在短信控制台中找到
        request.putQueryParameter("SignName", smsTemplate.getSignName());
        // 必填:短信模板-可在短信控制台中找到
        request.putQueryParameter("TemplateCode", smsTemplate.getTemplate());
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"
        request.putQueryParameter("TemplateParam", smsTemplate.getContext());
        try {
            // 构建CommonResponse
            CommonResponse response = client.getCommonResponse(request);
            if (!ObjectUtil.equal(response.getHttpStatus(), SUCCESS_CODE)) {
                return false;
            }
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
