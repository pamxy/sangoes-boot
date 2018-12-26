package com.sangoes.boot.uc.modules.admin.service.impl;

import com.sangoes.boot.uc.modules.admin.entity.UserDepart;
import com.sangoes.boot.uc.modules.admin.mapper.UserDepartMapper;
import com.sangoes.boot.uc.modules.admin.service.IUserDepartService;
import com.sangoes.boot.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户部门中间表 服务实现类
 * </p>
 *
 * @author jerrychir
 * @since 2018-12-25
 */
@Service
public class UserDepartServiceImpl extends BaseServiceImpl<UserDepartMapper, UserDepart> implements IUserDepartService {

    /**
     * 更具userId查询绑定的部门id
     *
     * @param id
     * @return
     */
    @Override
    public List<Long> listDepartKeysByUserId(Long id) {
        return baseMapper.listDepartKeysByUserId(id);
    }
}
