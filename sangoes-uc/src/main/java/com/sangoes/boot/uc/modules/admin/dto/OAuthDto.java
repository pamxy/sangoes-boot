package com.sangoes.boot.uc.modules.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * 授权dto
 *
 * @author jerrychir
 * @date 2018 2018/12/20 2:41 PM
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "授权对象", description = "授权对象")
public class OAuthDto {
    /**
     * 添加授权组
     */
    public interface AddOAuthGroup {

    }

    @NotBlank(message = "客户端id不能为空", groups = {AddOAuthGroup.class})
    @ApiModelProperty(value = "客户端id")
    private String clientId;

    @NotBlank(message = "客户端对应secret不能为空", groups = {AddOAuthGroup.class})
    @ApiModelProperty(value = "客户端对应secret")
    private String clientSecret;

    @ApiModelProperty(value = "资源ids")
    private String resourceIds;

    @NotBlank(message = "授权域不能为空", groups = {AddOAuthGroup.class})
    @ApiModelProperty(value = "范围")
    private String scope;

    @NotBlank(message = "授权模式不能为空", groups = {AddOAuthGroup.class})
    @ApiModelProperty(value = "授权")
    private String authorizedGrantTypes;

    @ApiModelProperty(value = "redirect url")
    private String webServerRedirectUri;

    @ApiModelProperty(value = "authorities")
    private String authorities;

    @ApiModelProperty(value = "access_token_validity")
    private Integer accessTokenValidity;

    @ApiModelProperty(value = "access_token_validity")
    private Integer refreshTokenValidity;

    @ApiModelProperty(value = "additional_information")
    private String additionalInformation;

    @ApiModelProperty(value = "autoapprove")
    private String autoapprove;
}
