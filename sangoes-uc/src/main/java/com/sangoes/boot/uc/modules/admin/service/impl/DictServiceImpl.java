package com.sangoes.boot.uc.modules.admin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sangoes.boot.common.exception.HandleErrorException;
import com.sangoes.boot.common.service.impl.BaseServiceImpl;
import com.sangoes.boot.common.utils.AuthUtils;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.common.utils.page.PageQuery;
import com.sangoes.boot.uc.modules.admin.dto.DictDto;
import com.sangoes.boot.uc.modules.admin.entity.Dict;
import com.sangoes.boot.uc.modules.admin.mapper.DictMapper;
import com.sangoes.boot.uc.modules.admin.service.IDictService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author jerrychir
 * @since 2018-12-26
 */
@Service
public class DictServiceImpl extends BaseServiceImpl<DictMapper, Dict> implements IDictService {

    /**
     * 添加字典
     *
     * @param dictDto
     */
    @Override
    public void saveDict(DictDto dictDto) {
        // 根据key查询dict
        Dict dictDB = this.getOne(new QueryWrapper<Dict>().lambda().eq(Dict::getDictKey, dictDto.getDictKey()));
        if (ObjectUtil.isNotNull(dictDB)) {
            throw new HandleErrorException("字典类型已存在");
        }
        // 复制
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictDto, dict);
        dict.setCreator(AuthUtils.getUserName());
        dict.setCreatorId(AuthUtils.getUserId());
        // 保存
        boolean flag = this.save(dict);
        if (!flag) {
            throw new HandleErrorException("字典保存失败");
        }

    }

    /**
     * 字典分页
     *
     * @param params
     * @return
     */
    @Override
    public PageData<Dict> pageDict(Map<String, Object> params) {
        PageData<Dict> pageData = this.selectPage(new PageQuery(params));
        return pageData;
    }
}
