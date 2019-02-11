package com.sangoes.boot.uc.modules.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sangoes.boot.uc.modules.admin.entity.Dict;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author jerrychir
 * @since 2018-12-26
 */
@Repository
public interface DictMapper extends BaseMapper<Dict> {

    /**
     * 根据字典类型(dictKey) 获取字典树形
     *
     * @param dictKey
     * @return
     */
    List<Dict> dictOneTree(@Param("dictKey") String dictKey);

    /**
     * 根据字典类型(dictKey) 获取列表
     *
     * @param dictKey
     * @return
     */
    List<Dict> dictOneList(String dictKey);
}
