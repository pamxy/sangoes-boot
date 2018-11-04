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
 * @date 2018/10/29 3:53 PM
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "注册对象", description = "注册对象")
public class SignUpDto {

    public interface MobileGroup {

    }

    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空", groups = MobileGroup.class)
    @Pattern(regexp = "^[a-zA-Z0-9]{6,18}", message = "用户名最小6位最大18位英文和数字组合", groups = MobileGroup.class)
    private String username;

    /**
     * 手机号码
     */
    @NotEmpty(message = "手机号码不能为空", groups = MobileGroup.class)
    @Pattern(regexp = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\\\d{8}$", message = "不符合手机号码格式", groups = MobileGroup.class)
    private String mobile;

    /**
     * 验证码
     */
    @NotEmpty(message = "验证码不能为空", groups = MobileGroup.class)
    private String captcha;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空", groups = MobileGroup.class)
    private String password;
    /**
     * 注册类型
     */
    @NotNull(message = "注册类型不能为空", groups = MobileGroup.class)
    private Integer signupType;
}
