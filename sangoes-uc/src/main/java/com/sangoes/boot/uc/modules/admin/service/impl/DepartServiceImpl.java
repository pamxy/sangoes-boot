package com.sangoes.boot.uc.modules.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sangoes.boot.common.exception.HandleErrorException;
import com.sangoes.boot.common.service.impl.BaseServiceImpl;
import com.sangoes.boot.common.utils.AuthUtils;
import com.sangoes.boot.uc.modules.admin.dto.DepartDto;
import com.sangoes.boot.uc.modules.admin.entity.Depart;
import com.sangoes.boot.uc.modules.admin.mapper.DepartMapper;
import com.sangoes.boot.uc.modules.admin.service.IDepartService;
import com.sangoes.boot.uc.modules.admin.vo.DepartTree;
import com.sangoes.boot.uc.utils.BuildTreeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author jerrychir
 * @since 2018-12-21
 */
@Service
public class DepartServiceImpl extends BaseServiceImpl<DepartMapper, Depart> implements IDepartService {
    /**
     * 保存部门或公司
     *
     * @param departDto
     */
    @Override
    public void saveDepart(DepartDto departDto) {
        // 复制
        Depart depart = new Depart();
        BeanUtils.copyProperties(departDto, depart);
        // 设置
        depart.setCreator(AuthUtils.getUserName());
        depart.setCreatorId(AuthUtils.getUserId());
        // 保存
        boolean flag = this.save(depart);
        if (!flag) {
            throw new HandleErrorException("保存失败");
        }
    }

    /**
     * 获取部门树形
     *
     * @return
     */
    @Override
    public List<DepartTree> getDepartTree() {
        // 获取部门列表
        List<Depart> departs = this.list(new QueryWrapper<Depart>());
        // 变树形
        List<DepartTree> departTrees = BuildTreeUtil.buildDepartTree(departs, -1L);
        return departTrees;
    }
}
