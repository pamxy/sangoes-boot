package com.sangoes.boot.uc.modules.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sangoes.boot.uc.modules.admin.entity.UserDepart;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户部门中间表 Mapper 接口
 * </p>
 *
 * @author jerrychir
 * @since 2018-12-25
 */
@Repository
public interface UserDepartMapper extends BaseMapper<UserDepart> {

    /**
     * 根据userId查询部门id
     *
     * @param id
     * @return
     */
    List<Long> listDepartKeysByUserId(Long id);
}
