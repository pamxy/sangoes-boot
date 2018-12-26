package com.sangoes.boot.uc.modules.admin.service;

import com.sangoes.boot.common.service.IBaseService;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.uc.modules.admin.dto.DictDto;
import com.sangoes.boot.uc.modules.admin.entity.Dict;

import java.util.Map;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author jerrychir
 * @since 2018-12-26
 */
public interface IDictService extends IBaseService<Dict> {

    /**
     * 添加字典
     *
     * @param dictDto
     */
    void saveDict(DictDto dictDto);

    /**
     * 字典分页
     *
     * @param params
     * @return
     */
    PageData<Dict> pageDict(Map<String, Object> params);
}
