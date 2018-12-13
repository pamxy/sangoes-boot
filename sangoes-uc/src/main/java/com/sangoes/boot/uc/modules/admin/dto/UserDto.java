package com.sangoes.boot.uc.modules.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * UserDto
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "用户对象", description = "用户对象")
public class UserDto {

    /**
     * BindRoleGroup
     */
    public interface BindRoleGroup {

    }

    /**
     * DeleteUserGroup
     * 删除用户
     */
    public interface DeleteUserGroup {

    }

    /**
     * UpdateUserGroup
     * 更新用户
     */
    public interface UpdateUserGroup {

    }

    @NotEmpty(message = "用户名不能为空")
    @NotNull(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,18}", message = "用户名最小6位最大18位英文和数字组合")
    @ApiModelProperty(value = "用户名")
    private String username;

    @NotEmpty(message = "姓名不能为空")
    @NotNull(message = "姓名不能为空")
    @Pattern(regexp = "^[\u4e00-\u9fa5]{2,6}", message = "姓名只能为2-6位汉字")
    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @NotEmpty(message = "手机号码不能为空")
    @NotNull(message = "手机号码不能为空")
    @Pattern(regexp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-9])|(147))\\d{8}$", message = "不符合手机号码格式")
    @ApiModelProperty(value = "手机号")
    private String mobile;

    @NotNull(message = "用户id不能为空", groups = {BindRoleGroup.class, DeleteUserGroup.class})
    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "绑定角色ids")
    private String roleIds;

    @NotNull(message = "用户id不能为空", groups = {UpdateUserGroup.class})
    @ApiModelProperty(value = "用户id")
    private Long id;
}