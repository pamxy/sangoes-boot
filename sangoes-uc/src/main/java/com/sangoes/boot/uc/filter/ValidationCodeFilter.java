package com.sangoes.boot.uc.filter;

import cn.hutool.core.util.StrUtil;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.uc.constants.CaptchaConstants;
import com.sangoes.boot.uc.constants.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright (c) 2018
 * 验证码验证过滤器
 *
 * @author jerrychir
 * @date 2018/11/19 5:25 PM
 */
@Slf4j
@Component
public class ValidationCodeFilter extends OncePerRequestFilter {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 过滤
     * filterChain.doFilter(request, response) 必须在最前面 不然会报 java.lang.IllegalStateException: getWriter() has already been called for this response
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取grant_type
        String grantType = request.getParameter(SecurityConstants.GRANT_TYPE);
        // 判断grantType是否为空
        if (StrUtil.isNotBlank(grantType)) {
            // 验证码随机数
            String captchaRandom = request.getParameter(CaptchaConstants.CAPTCHA_RANDOM);
            // 判断验证码随机数
            if (StrUtil.isBlank(captchaRandom)) {
                Result.noReturn("验证码随机数为空", HttpStatus.EXPECTATION_FAILED, response);
                return;
            }
            // 验证码
            String captcha = request.getParameter(CaptchaConstants.CAPTCHA_CAPTCHA);
            // 判断图片验证码
            if (StrUtil.isBlank(captcha)) {
                Result.noReturn("验证码为空", HttpStatus.EXPECTATION_FAILED, response);
                return;
            }
            String key = null;
            // 判断grant_type 是否为 password用户登录
            if (StrUtil.equals(grantType, SecurityConstants.PASSWORD)) {
                // 获取key
                key = CaptchaConstants.CAPTCHA_IMAGE + captchaRandom;
            }
            // 判断grant_type 是否为 mobile手机登录
            if (StrUtil.equals(grantType, SecurityConstants.MOBILE)) {
                // 获取key
                key = CaptchaConstants.CAPTCHA_SMS_CODE + captchaRandom;
            }
            // 判断redis中是否有验证码
            Boolean hasKey = redisTemplate.hasKey(key);
            if (!hasKey) {
                Result.noReturn("验证码为空", HttpStatus.EXPECTATION_FAILED, response);
                return;
            }
            // 获取redis验证码
            String redisCaptcha = redisTemplate.opsForValue().get(key);
            // 判读验证码是否相同
            if (!StrUtil.equals(redisCaptcha, captcha)) {
                Result.noReturn("验证码错误", HttpStatus.EXPECTATION_FAILED, response);
                return;
            }
        }
        // 放行
        filterChain.doFilter(request, response);
    }
}
