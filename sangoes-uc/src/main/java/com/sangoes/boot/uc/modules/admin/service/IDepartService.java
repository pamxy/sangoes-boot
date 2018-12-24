package com.sangoes.boot.uc.modules.admin.service;

import com.sangoes.boot.common.service.IBaseService;
import com.sangoes.boot.uc.modules.admin.dto.DepartDto;
import com.sangoes.boot.uc.modules.admin.entity.Depart;
import com.sangoes.boot.uc.modules.admin.vo.DepartTree;

import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author jerrychir
 * @since 2018-12-21
 */
public interface IDepartService extends IBaseService<Depart> {

    /**
     * 保存部门或公司
     *
     * @param departDto
     */
    void saveDepart(DepartDto departDto);

    /**
     * 获取部门树形
     *
     * @return
     */
    List<DepartTree> getDepartTree();

}
