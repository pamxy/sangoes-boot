package com.sangoes.boot.uc.modules.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * UserDto
 * @author jerrychir
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
     * AddUserGroup
     * 添加用户
     */
    public interface AddUserGroup {

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

    /**
     * BatchDeleteUserGroup
     * 删除用户
     */
    public interface BatchDeleteUserGroup {

    }

    /**
     * BindDepartGroup
     * 绑定部门
     */
    public interface BindDepartGroup {

    }

    /**
     * ChangePwdGroup
     * 修改密码
     */
    public interface ChangePwdGroup {

    }

    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,18}", message = "用户名最小6位最大18位英文和数字组合")
    @ApiModelProperty(value = "用户名")
    private String username;

    @NotBlank(message = "姓名不能为空", groups = {AddUserGroup.class})
    @Pattern(regexp = "^[\u4e00-\u9fa5]{2,6}", message = "姓名只能为2-6位汉字", groups = {AddUserGroup.class})
    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @NotBlank(message = "手机号码不能为空", groups = {AddUserGroup.class})
    @Pattern(regexp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-9])|(147))\\d{8}$", message = "不符合手机号码格式", groups = {AddUserGroup.class})
    @ApiModelProperty(value = "手机号")
    private String mobile;

    @NotNull(message = "用户id不能为空", groups = {BindRoleGroup.class, DeleteUserGroup.class, BindDepartGroup.class})
    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "绑定角色ids")
    private String roleIds;

    @NotNull(message = "用户id不能为空", groups = {UpdateUserGroup.class})
    @ApiModelProperty(value = "用户id")
    private Long id;

    @NotNull(message = "用户主键集合不能为空", groups = {BatchDeleteUserGroup.class})
    @ApiModelProperty(value = "用户主键集合")
    private List<Long> userIds;

    @NotNull(message = "部门id不能为空")
    @ApiModelProperty(value = "部门id")
    private Long departId;

    @NotNull(message = "部门id集合不能为空", groups = {BindDepartGroup.class})
    @ApiModelProperty(value = "部门id集合")
    private List<Long> departIds;

    @NotBlank(message = "原始密码不能为空", groups = {ChangePwdGroup.class})
    @ApiModelProperty(value = "原始密码")
    private String oldPwd;

    @NotBlank(message = "新密码不能为空", groups = {ChangePwdGroup.class})
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$", message = "必须包含字母和数字的组合，不能使用特殊字符，长度在6-20之间", groups = {ChangePwdGroup.class})
    @ApiModelProperty(value = "新密码")
    private String newPwd;

}