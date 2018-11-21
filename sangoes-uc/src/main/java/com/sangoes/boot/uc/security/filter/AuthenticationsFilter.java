package com.sangoes.boot.uc.security.filter;

import cn.hutool.core.util.StrUtil;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.uc.constants.CaptchaConstants;
import com.sangoes.boot.uc.constants.SecurityConstants;
import com.sangoes.boot.uc.security.token.AuthenticationsToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/11/20 12:54 PM
 */
@Slf4j
public class AuthenticationsFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    /**
     * POST
     */
    private boolean postOnly = true;

    /**
     * 构造匹配
     */
    public AuthenticationsFilter() {
        super(new AntPathRequestMatcher(SecurityConstants.SIGN_IN, SecurityConstants.POST));
    }

    /**
     * 尝试认证
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        log.error("filter:{}", request);
        // 只能POST
        if (postOnly && !StrUtil.equals(request.getMethod(), SecurityConstants.POST)) {
            Result.noReturn("登录只能POST", HttpStatus.EXPECTATION_FAILED, response);
            return null;
        }
        // 登录类型 mobile account
        String type = request.getParameter(SecurityConstants.TYPE);
        // 判断type是否为空
        if (StrUtil.isBlank(type)) {
            log.error("type不能为空");
            Result.noReturn("type不能为空", HttpStatus.EXPECTATION_FAILED, response);
            return null;
        }
        // 手机号码
        String mobile = request.getParameter(SecurityConstants.MOBILE);
        // 用户名
        String username = request.getParameter(SecurityConstants.USER_NAME);
        // 密码
        String password = request.getParameter(SecurityConstants.PASSWORD);
        // 验证码
        String captcha = request.getParameter(CaptchaConstants.CAPTCHA);
        // 验证码随机数
        String captchaRandom = request.getParameter(CaptchaConstants.CAPTCHA_RANDOM);
        // 公共随机数
        String publicRandom = request.getParameter(CaptchaConstants.PUBLIC_RANDOM);
        // 登录类型
        String signinType = request.getParameter(CaptchaConstants.SIGNIN_TYPE);
        // 判断登录类型
        if (StrUtil.isBlank(signinType)) {
            Result.noReturn("登录类型(signType)为空", HttpStatus.EXPECTATION_FAILED, response);
            return null;
        }
        // 判断验证码随机数
        if (StrUtil.isBlank(captchaRandom)) {
            Result.noReturn("验证码随机数(captchaRandom)为空", HttpStatus.EXPECTATION_FAILED, response);
            return null;
        }
        // 判断图片验证码
        if (StrUtil.isBlank(captcha)) {
            Result.noReturn("验证码为空或过期", HttpStatus.EXPECTATION_FAILED, response);
            return null;
        }
        // redis key
        String key;
        // principal
        String principal;
        // credentials
        String credentials;
        // 判断type account
        if (StrUtil.equals(type, SecurityConstants.ACCOUNT)) {
            // 判断公共随机数
            if (StrUtil.isBlank(publicRandom)) {
                Result.noReturn("公共随机数(publicRandom)为空", HttpStatus.EXPECTATION_FAILED, response);
                return null;
            }
            // 判断username
            if (StrUtil.isBlank(username)) {
                Result.noReturn("用户名为空", HttpStatus.EXPECTATION_FAILED, response);
                return null;
            }
            // 判断password
            if (StrUtil.isBlank(password)) {
                Result.noReturn("密码为空", HttpStatus.EXPECTATION_FAILED, response);
                return null;
            }
            // 获取key
            key = CaptchaConstants.CAPTCHA_IMAGE + captchaRandom;
            // 赋值
            principal = username;
            credentials = password;
            //
        } else if (StrUtil.equals(type, SecurityConstants.MOBILE)) {
            // 判断 mobile
            if (StrUtil.isBlank(mobile)) {
                Result.noReturn("手机号为空", HttpStatus.EXPECTATION_FAILED, response);
                return null;
            }
            // 获取key
            key = CaptchaConstants.CAPTCHA_MOBILE_SMS + mobile;
            // 手机
            principal = mobile;
            credentials = captcha;

        } else {
            log.error("type不正确");
            Result.noReturn("type不正确", HttpStatus.EXPECTATION_FAILED, response);
            return null;
        }
        // 判断redis中是否有验证码
        Boolean hasKey = redisTemplate.hasKey(key);
        if (!hasKey) {
            Result.noReturn("验证码为空", HttpStatus.EXPECTATION_FAILED, response);
            return null;
        }
        // 获取redis验证码
        String redisCaptcha = redisTemplate.opsForValue().get(key);
        // 判读验证码是否相同
        if (!StrUtil.equals(redisCaptcha, captcha)) {
            Result.noReturn("验证码错误", HttpStatus.EXPECTATION_FAILED, response);
            return null;
        }
        // token
        AuthenticationsToken token = new AuthenticationsToken(principal, credentials, type, mobile, signinType, publicRandom);
        // 设置details
        token.setDetails(authenticationDetailsSource.buildDetails(request));
        return this.getAuthenticationManager().authenticate(token);
    }
}
