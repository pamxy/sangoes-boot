package com.sangoes.boot.uc.modules.admin.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/11/4 12:36 PM
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "登陆对象", description = "登陆对象")
public class SignInDto {
    public interface SignInMobileGroup {

    }

    public interface SignInAccountGroup {

    }

    /**
     * 手机号码
     */

    @NotEmpty(message = "手机号码不能为空", groups = SignInMobileGroup.class)
    @Pattern(regexp = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\\\d{8}$", message = "不符合手机号码格式", groups = SignUpDto.MobileGroup.class)
    private String mobile;

    /**
     * 验证码
     */
    @NotEmpty(message = "验证码不能为空", groups = { SignInAccountGroup.class, SignInMobileGroup.class })
    private String captcha;

    /**
     * 登陆类型
     */
    @NotNull(message = "登陆类型不能为空", groups = { SignInAccountGroup.class, SignInMobileGroup.class })
    private Integer signinType;

    /**
     * type: account用户名 mobile手机
     */
    // @NotEmpty(message = "账号类型不能为空", groups = { SignInAccountGroup.class,
    // SignInMobileGroup.class })
    // private String type;
    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空", groups = SignInAccountGroup.class)
    private String username;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空", groups = SignInAccountGroup.class)
    private String password;

    /**
     * 公钥随机码
     */
    @NotEmpty(message = "公钥随机码不能为空", groups = SignInAccountGroup.class)
    private String publicRandom;

    /**
     * 验证码随机码
     */
    @NotEmpty(message = "验证码随机码不能为空", groups = SignInAccountGroup.class)
    private String captchaRandom;

    private String grant_type;
}
