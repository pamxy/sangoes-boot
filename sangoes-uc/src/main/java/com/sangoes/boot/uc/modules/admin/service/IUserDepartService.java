package com.sangoes.boot.uc.modules.admin.service;

import com.sangoes.boot.uc.modules.admin.entity.UserDepart;
import com.sangoes.boot.common.service.IBaseService;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户部门中间表 服务类
 * </p>
 *
 * @author jerrychir
 * @since 2018-12-25
 */
public interface IUserDepartService extends IBaseService<UserDepart> {

    /**
     * 更具userId查询绑定的部门id
     * @param id
     * @return
     */
    List<Long> listDepartKeysByUserId(Long id);
}
