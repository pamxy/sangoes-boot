package com.sangoes.boot.uc.modules.admin.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangoes.boot.common.msg.PageData;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.uc.modules.admin.dto.SignInDto;
import com.sangoes.boot.uc.modules.admin.dto.SignUpDto;
import com.sangoes.boot.uc.modules.admin.dto.UserDto;
import com.sangoes.boot.uc.modules.admin.entity.SysUser;
import com.sangoes.boot.uc.modules.admin.vo.UserDetailsVo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author jerrychir
 * @since 2018-10-29
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 根据手机号码注册
     *
     * @param signUpDto
     */
    Result<String> signUpByMobile(SignUpDto signUpDto);

    /**
     * 根据手机号码登陆
     *
     * @param signInDto
     * @return
     */
    Result<String> signinByMobile(SignInDto signInDto);

    /**
     * 根据username查询UserDeatilsVo
     * 
     * @param username
     * @return
     */
    UserDetailsVo selectUserDetailsByUsername(String username);

    /**
     * 根据用户名登录
     */
    Result<String> signinByAccount(SignInDto signInDto);

    /**
     * 添加用户
     * 
     * @param userDto
     * @return
     */
    Result<String> addUser(UserDto userDto);

    /**
     * 分页获取用户
     * 
     * @param params
     * @return
     */
    Result<PageData<SysUser>> selectUserPage(Map<String, Object> params);

}
