/*
 * @Author: jerrychir @sangoes
 * @Date: 2018-11-09 15:32:03
 * @Last Modified by: jerrychir @sangoes
 * @Last Modified time: 2018-11-15 12:29:20
 */
package com.sangoes.boot.uc.modules.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import cn.hutool.crypto.asymmetric.AsymmetricCrypto;
import cn.hutool.crypto.asymmetric.KeyType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sangoes.boot.common.core.componet.AliyunOSSUploader;
import com.sangoes.boot.common.exception.HandleErrorException;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.common.service.impl.BaseServiceImpl;
import com.sangoes.boot.common.utils.ArrayUtils;
import com.sangoes.boot.common.utils.AuthUtils;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.common.utils.page.PageQuery;
import com.sangoes.boot.common.utils.page.Pagination;
import com.sangoes.boot.uc.constants.CaptchaConstants;
import com.sangoes.boot.uc.constants.RSAConstants;
import com.sangoes.boot.uc.modules.admin.dto.SignInDto;
import com.sangoes.boot.uc.modules.admin.dto.SignUpDto;
import com.sangoes.boot.uc.modules.admin.dto.UserDto;
import com.sangoes.boot.uc.modules.admin.entity.SysRole;
import com.sangoes.boot.uc.modules.admin.entity.SysUser;
import com.sangoes.boot.uc.modules.admin.entity.SysUserRole;
import com.sangoes.boot.uc.modules.admin.entity.UserDepart;
import com.sangoes.boot.uc.modules.admin.entity.enums.SignUpEnum;
import com.sangoes.boot.uc.modules.admin.mapper.SysUserMapper;
import com.sangoes.boot.uc.modules.admin.mapper.SysUserRoleMapper;
import com.sangoes.boot.uc.modules.admin.service.IDepartService;
import com.sangoes.boot.uc.modules.admin.service.ISysRoleService;
import com.sangoes.boot.uc.modules.admin.service.ISysUserService;
import com.sangoes.boot.uc.modules.admin.service.IUserDepartService;
import com.sangoes.boot.uc.modules.admin.vo.DepartTree;
import com.sangoes.boot.uc.modules.admin.vo.UserDetailsVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author jerrychir
 * @since 2018-10-29
 */
@Service
@Slf4j
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 权限管理器
     */
    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private AliyunOSSUploader aliyunOSSUploader;

    @Autowired
    private IDepartService departService;

    @Autowired
    private IUserDepartService userDepartService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 根据手机号码注册
     *
     * @param signUpDto
     */
    @Override
    public Result<String> signUpByMobile(SignUpDto signUpDto) {
        // 验证码
        String captchaConstant = CaptchaConstants.CAPTCHA_MOBILE_SMS + signUpDto.getMobile();
        // 检测是否有mobile对应的redis缓存
        boolean hasKey = redisTemplate.hasKey(captchaConstant).booleanValue();
        if (!hasKey) {
            throw new HandleErrorException("验证码不存在或过期");
        }
        // 删除验证码
        redisTemplate.delete(captchaConstant);
        // 验证手机号码是否被注册
        SysUser user = baseMapper
                .selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getMobile, signUpDto.getMobile()));
        if (!ObjectUtil.isNull(user)) {
            throw new HandleErrorException("手机号码已注册");
        }
        // 验证用户名是否被存在
        SysUser userName = baseMapper
                .selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getUsername, signUpDto.getUsername()));
        if (!ObjectUtil.isNull(userName)) {
            throw new HandleErrorException("用户名已注册");
        }
        // 解密密码
        String password = decodePassword(RSAConstants.MOBILE_RSA_PRIVATE_KEY + signUpDto.getMobile(),
                signUpDto.getPassword());

        // 创建user
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(signUpDto, sysUser);
        // 设置随机真实姓名
        sysUser.setRealName(RandomUtil.randomString(8));
        // 加密密码
        // sysUser.setPassword(passwordEncoder.encode(password));
        // 写入数据库
        boolean save = this.save(sysUser);
        if (!save) {
            throw new HandleErrorException("注册失败");
        }
        return Result.success("注册成功");
    }

    /**
     * 根据手机号码登陆
     *
     * @param signInDto
     * @return
     */
    @Override
    public Result<String> signinByMobile(SignInDto signInDto) {
        // 根据mobile查询sys user
        SysUser userDB = this
                .getOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getMobile, signInDto.getMobile()));
        // 判断是否存在
        if (ObjectUtil.isNull(userDB)) {
            throw new HandleErrorException("此号码没有注册,请注册");
        }
        // 待验证验证码
        String captcha = signInDto.getCaptcha();
        // redis中的验证码
        String captchaConstant = CaptchaConstants.CAPTCHA_MOBILE_SMS + signInDto.getMobile();
        // 检测是否有mobile对应的redis缓存
        boolean hasKey = redisTemplate.hasKey(captchaConstant).booleanValue();
        if (!hasKey) {
            throw new HandleErrorException("验证码不存在或过期");
        }
        // 获取redis中的验证码
        String captchaRedis = String.valueOf(redisTemplate.opsForValue().get(captchaConstant));
        // 判断验证码是否相同
        if (!StringUtils.equals(captcha, captchaRedis)) {
            throw new HandleErrorException("验证码错误");
        }
        // 删除captcha
        redisTemplate.delete(captchaConstant);
        try {
            // 登录
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(userDB.getUsername(), userDB.getPassword()));
            // 改变signinType
            userDB.setLoginType(signInDto.getSigninType());
            // 更新
            boolean flag = this.updateById(userDB);
            if (!flag) {
                throw new HandleErrorException("登录失败");
            }
            // 创建token
            // return Result.success(jwtTokenProvider.createToken(userDB.getUsername()),
            // "登录成功");
            return Result.success("ddddd", "登录成功");
        } catch (AuthenticationException ex) {
            log.error(ex.getMessage(), ex);
            throw new HandleErrorException("登陆失败");
        }

    }

    /**
     *
     */
    @Override
    public UserDetailsVo selectUserDetailsByUsername(String username) {
        UserDetailsVo userDetailsVo = baseMapper.selectUserDetailsByUsername(username);
        return userDetailsVo;
    }

    /**
     * 根据mobile查询UserDetailsVo
     *
     * @param mobile
     * @return
     */
    @Override
    public UserDetailsVo selectUserDetailsByMobile(String mobile) {
        return baseMapper.userDetailsByMobile(mobile);
    }

    @Override
    public Result<String> signinByAccount(SignInDto signInDto) {
        // 根据username查询sys user
        SysUser userDB = this
                .getOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getUsername, signInDto.getUsername()));
        // 判断是否存在
        if (ObjectUtil.isNull(userDB)) {
            throw new HandleErrorException("用户不存在");
        }
        // 待验证验证码
        String captcha = signInDto.getCaptcha();
        // redis中的验证码
        String captchaConstant = CaptchaConstants.CAPTCHA_IMAGE + signInDto.getCaptchaRandom();
        // 检测是否有random对应的redis缓存
        boolean hasKey = redisTemplate.hasKey(captchaConstant).booleanValue();
        if (!hasKey) {
            throw new HandleErrorException("验证码不存在或过期");
        }
        // 获取redis中的验证码
        String captchaRedis = String.valueOf(redisTemplate.opsForValue().get(captchaConstant));
        // 判断验证码是否相同
        if (!StringUtils.equals(captcha, captchaRedis)) {
            throw new HandleErrorException("验证码错误");
        }
        // 删除captcha
        redisTemplate.delete(captchaConstant);
        // 解密密码
        String password = decodePassword(RSAConstants.RANDOM_RSA_PRIVATE_KEY + signInDto.getPublicRandom(),
                signInDto.getPassword());
        // 比较密码是否相同
        // if (!passwordEncoder.matches(password, userDB.getPassword())) {
        // throw new HandleErrorException("密码不正确");
        // }
        try {
            // 登录
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(userDB.getUsername(), userDB.getPassword()));
            // 改变signinType
            userDB.setLoginType(signInDto.getSigninType());
            // 更新
            boolean flag = this.updateById(userDB);
            if (!flag) {
                throw new HandleErrorException("登录失败");
            }
            // 创建token
            // return Result.success(jwtTokenProvider.createToken(userDB.getUsername()),
            // "登录成功");
            return Result.success("xxxxxx", "登录成功");
        } catch (AuthenticationException ex) {
            log.error(ex.getMessage(), ex);
            throw new HandleErrorException("登陆失败");
        }
    }

    /**
     * 添加用户
     */
    @Override
    public void addUser(UserDto userDto) {
        // 判断用户名是否存在
        SysUser userNameDB = this
                .getOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getUsername, userDto.getUsername()));
        // 判断是否存在
        if (!ObjectUtil.isNull(userNameDB)) {
            throw new HandleErrorException("用户已存在");
        }
        // 判断手机号码是否存在
        SysUser mobileDB = this
                .getOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getMobile, userDto.getMobile()));
        // 判断是否存在
        if (!ObjectUtil.isNull(mobileDB)) {
            throw new HandleErrorException("此号码已注册");
        }
        // 新建user
        SysUser user = new SysUser();
        // 复制bean
        BeanUtils.copyProperties(userDto, user);
        // 加密密码并设置默认密码
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode("888888"));
        // 设置注册类型
        user.setSignupType(SignUpEnum.ADMIN.getValue());
        // 设置创建者
        user.setCreatorId(AuthUtils.getUserId());
        user.setCreator(AuthUtils.getUserName());
        // 保存到数据库
        boolean save = this.save(user);
        // 添加失败
        if (!save) {
            throw new HandleErrorException("添加失败");
        }

    }

    /**
     * 解密密码
     *
     * @param key
     * @param password
     * @return
     */
    private String decodePassword(String key, String password) {
        // 从缓存中获取privateKey
        String privateKey = String.valueOf(redisTemplate.opsForValue().get(key));
        // 解密密码
        AsymmetricCrypto crypto = new AsymmetricCrypto(AsymmetricAlgorithm.RSA, privateKey, null);
        return StrUtil.str(crypto.decryptFromBase64(password, KeyType.PrivateKey), CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * 分页获取用户
     */
    @Override
    public Result<PageData<SysUser>> selectUserPage(Map<String, Object> params) {
        PageData<SysUser> selectPage = this.selectPage(new PageQuery(params));
        return Result.success(selectPage, "成功");

    }

    /**
     * 查询用户绑定的角色
     */
    @Override
    public Result<Map<String, Object>> infoBindRole(Long id) {
        // 判断id为空
        if (Validator.isNull(id)) {
            throw new HandleErrorException("查询id不能为空");
        }
        // 查询用户绑定的角色
        List<String> keys = ArrayUtils.longListToStringList(userRoleMapper.listRoleIdByUserId(id));
        // 查询所有的角色
        List<SysRole> roles = roleService.list(new QueryWrapper<SysRole>());
        // 包装
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("keys", keys);
        map.put("roles", roles);
        return Result.success(map, "成功");
    }

    /**
     * 绑定角色
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result<String> bindRole(UserDto userDto) {
        // 创建 user role
        SysUserRole sysUserRole = new SysUserRole();
        // 删除为 user id
        userRoleMapper.delete(new QueryWrapper<SysUserRole>().lambda().eq(SysUserRole::getUserId, userDto.getUserId()));
        // 判断 roleIds 是否为空
        if (StrUtil.isNotBlank(userDto.getRoleIds())) {
            // 转换为数组
            String[] roleIds = userDto.getRoleIds().split(",");
            for (String roleId : roleIds) {
                sysUserRole.setRoleId(Long.valueOf(roleId));
                sysUserRole.setUserId(userDto.getUserId());
                sysUserRole.setCreator(AuthUtils.getUserName());
                sysUserRole.setCreatorId(AuthUtils.getUserId());
                userRoleMapper.insert(sysUserRole);
            }
        }
        return Result.success("添加成功");
    }

    /**
     * 获取当前用户信息
     *
     * @param userId
     * @return
     */
//    @Cacheable(value = "user", key = "'user:info:'+#userId")
    @Override
    public SysUser userInfo(Long userId) {
        SysUser user = baseMapper.selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getId, userId));
        return user;
    }


    /**
     * 用户上传头像
     *
     * @param file
     * @return
     */
//    @CacheEvict(value = "user", key = "'user:info:'+#userId")
    @Override
    public String uploadAvatar(Long userId, MultipartFile file) {
        // 根据用户id查询
        SysUser user = baseMapper.selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getId, userId));
        if (ObjectUtil.isNull(user)) {
            throw new HandleErrorException("用户不存在");
        }
        // 上传图片
        String imgUrl = aliyunOSSUploader.uploadMultipartFile(file);
        // 设置头像
        user.setAvatar(imgUrl);
        // 设置更新者
        user.setUpdatorId(userId);
        user.setUpdator(user.getUsername());
        // 更新user
        baseMapper.updateById(user);
        return imgUrl;
    }

    /**
     * 删除用户
     *
     * @param userDto
     */
//    @CacheEvict(value = "user", key = "'user:info:'+#userDto.userId")
    @Override
    public void deleteUser(UserDto userDto) {
        // 删除
        boolean flag = this.removeById(userDto.getUserId());
        if (!flag) {
            throw new HandleErrorException("删除失败");
        }
    }


    /**
     * 更新用户
     *
     * @param userDto
     */
//    @CacheEvict(value = "user", key = "'user:info:'+#userDto.userId")
    @Override
    public void updateUser(UserDto userDto) {
        // 查询
        SysUser userDB = this.getById(userDto.getId());
        if (ObjectUtil.isNull(userDB)) {
            throw new HandleErrorException("用户为空或已删除");
        }
        // 新建
        SysUser user = new SysUser();
        // 复制
        BeanUtil.copyProperties(userDto, user, CopyOptions.create().setIgnoreNullValue(true));
        // 更新
        boolean flag = this.updateById(user);
        if (!flag) {
            throw new HandleErrorException("更新失败");
        }
    }

    /**
     * 批量删除用户
     *
     * @param userDto
     */
//    @CacheEvict(value = "user")
    @Override
    public void batchDeleteUser(UserDto userDto) {
        // 批量删除
        boolean flag = this.removeByIds(userDto.getUserIds());
        if (!flag) {
            throw new HandleErrorException("批量删除失败");
        }
    }

    /**
     * 查询绑定部门树形
     *
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> infoBindDepartTree(Long id) {
        // 判断id为空
        if (Validator.isNull(id)) {
            throw new HandleErrorException("用户为空或已被删除");
        }
        // 查询所有部门
        List<DepartTree> departTree = departService.getDepartTree();
        // 查询被绑定的key
        List<String> keys = ArrayUtils.longListToStringList(userDepartService.listDepartKeysByUserId(id));
        // 包装
        Map<String, Object> map = new HashMap<>();
        map.put("trees", departTree);
        map.put("keys", keys);
        return map;
    }

    /**
     * 绑定部门
     *
     * @param userDto
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void bindDepart(UserDto userDto) {
        // 删除已经绑定的
        userDepartService.remove(new QueryWrapper<UserDepart>().lambda().eq(UserDepart::getUserId, userDto.getUserId()));
        // 遍历
        List<UserDepart> userDeparts = new ArrayList<>();
        userDto.getDepartIds().forEach(id -> {
            UserDepart userDepart = new UserDepart();
            userDepart.setDepartId(id);
            userDepart.setUserId(userDto.getUserId());
            userDepart.setCreator(AuthUtils.getUserName());
            userDepart.setCreatorId(AuthUtils.getUserId());
            userDeparts.add(userDepart);
        });
        // 批量写入
        boolean flag = userDepartService.saveBatch(userDeparts);
        if (!flag) {
            throw new HandleErrorException("绑定失败");
        }

    }

    /**
     * 部门成员列表
     *
     * @param query 部门id
     * @return
     */
    @Override
    public PageData<SysUser> listDepartMembers(PageQuery query) {
        // 获取departId
        Object departIdObj = query.get("departId");
        if (ObjectUtil.isNull(departIdObj)) {
            throw new HandleErrorException("部门主键不能为空");
        }
        // 转换
        Long departId = Long.valueOf(departIdObj.toString());
        // 分页
        Page<SysUser> page = new Page<>(query.getCurrent(), query.getPageSize());
        // 分页查询
        IPage<SysUser> members = baseMapper.listDepartMembers(page, departId);
        // 返回结果
        Pagination pagination = new Pagination(members.getTotal(), members.getSize(), members.getCurrent());
        return new PageData<>(pagination, members.getRecords());
    }

    /**
     * 根据roleCode查询用户
     *
     * @param roleCode
     * @return
     */
    @Override
    public List<SysUser> listByRoleCode(String roleCode) {
        List<SysUser> users = baseMapper.listByRoleCode(roleCode);
        return users;
    }

    /**
     * 修改密码
     *
     * @param userDto
     */
    @Override
    public void changePwd(UserDto userDto) {
        // 当前用户
        Long userId = AuthUtils.getUserId();
        SysUser userDB = this.getById(userId);
        if (ObjectUtil.isNull(userDB)){
            throw new HandleErrorException("没找到当前用户");
        }
        // 密码比较
        if (!passwordEncoder.matches(userDto.getOldPwd(), userDB.getPassword())) {
            throw new HandleErrorException("原始密码不正确");
        }
        // 设置新密码
        userDB.setPassword(passwordEncoder.encode(userDto.getNewPwd()));
        // 更新
        boolean flag = this.updateById(userDB);
        if (!flag){
            throw new HandleErrorException("修改密码失败");
        }
    }
}
