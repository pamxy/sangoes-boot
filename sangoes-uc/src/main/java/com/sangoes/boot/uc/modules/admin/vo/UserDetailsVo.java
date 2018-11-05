/*
 * @Author: 驷爺.J.C
 * @Date: 2018-11-04 23:00:59
 * @Last Modified by: 驷爺.J.C
 * @Last Modified time: 2018-11-04 23:07:36
 */
package com.sangoes.boot.uc.modules.admin.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.sangoes.boot.uc.modules.admin.entity.SysRole;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author jerrychir
 */
@Data
@Accessors(chain = true)
public class UserDetailsVo implements Serializable {

    private static final long serialVersionUID = -6658400055088458377L;

    /**
     * 用户主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
    /**
     * 1启用 0禁用
     */
    private Integer status;
    /**
     * 角色
     */
    private List<SysRole> roles;
}