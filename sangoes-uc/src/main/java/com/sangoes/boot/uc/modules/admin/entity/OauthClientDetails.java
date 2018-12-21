package com.sangoes.boot.uc.modules.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.sangoes.boot.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 授权表
 * </p>
 *
 * @author jerrychir
 * @since 2018-12-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="OauthClientDetails对象", description="授权表")
public class OauthClientDetails extends BaseEntity {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "客户端id")
    private String clientId;

    @ApiModelProperty(value = "客户端对应secret")
    private String clientSecret;

    @ApiModelProperty(value = "资源ids")
    private String resourceIds;

    @ApiModelProperty(value = "范围")
    private String scope;

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
