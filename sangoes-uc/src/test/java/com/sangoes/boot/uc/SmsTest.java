package com.sangoes.boot.uc;

import com.sangoes.boot.common.core.service.ISmsService;
import com.sangoes.boot.common.utils.template.SmsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 *
 * @author jerrychir
 * @date 2019 2019/3/4 3:18 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SmsTest {

    @Autowired
    private ISmsService smsService;

    @Test
    public void testAliyunSms(){
        SmsTemplate smsTemplate = new SmsTemplate();
        smsTemplate.setMobile("15351229035");
        smsTemplate.setSignName("活动验证");
        smsTemplate.setTemplate("SMS_4040005");
        smsTemplate.setContext("{\"code\":\"22222\",\"product\":\"请输入\"}");
        smsService.sendSms(smsTemplate);
    }
}
