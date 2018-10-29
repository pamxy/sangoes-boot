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

    private String username;

    @NotNull(groups = MobileGroup.class)
    @NotEmpty
    @Pattern(regexp = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\\\d{8}$")
    private String mobile;

    @NotNull
    @NotEmpty
    private String password;
}
