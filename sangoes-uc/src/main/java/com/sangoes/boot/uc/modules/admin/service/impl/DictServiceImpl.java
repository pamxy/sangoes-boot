package com.sangoes.boot.uc.modules.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sangoes.boot.common.exception.HandleErrorException;
import com.sangoes.boot.common.service.impl.BaseServiceImpl;
import com.sangoes.boot.common.utils.AuthUtils;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.common.utils.page.PageQuery;
import com.sangoes.boot.common.utils.page.Pagination;
import com.sangoes.boot.uc.modules.admin.dto.DictDto;
import com.sangoes.boot.uc.modules.admin.entity.Dict;
import com.sangoes.boot.uc.modules.admin.mapper.DictMapper;
import com.sangoes.boot.uc.modules.admin.service.IDictService;
import com.sangoes.boot.uc.modules.admin.vo.DictTree;
import com.sangoes.boot.uc.utils.BuildTreeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        // 判断是否添加子字典
        if (!dictDto.isSubDict()) {
            // 根据key查询dict
            Dict dictDB = this.getOne(new QueryWrapper<Dict>().lambda().eq(Dict::getDictKey, dictDto.getDictKey()));
            if (ObjectUtil.isNotNull(dictDB)) {
                throw new HandleErrorException("字典类型已存在");
            }
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
     * 更新字典
     *
     * @param dictDto
     */
    @Override
    public void updateDict(DictDto dictDto) {
        // 查询
        Dict dictDB = this.getById(dictDto.getId());
        if (ObjectUtil.isNull(dictDB)) {
            throw new HandleErrorException("字典删除或者为空");
        }
        // 复制
        Dict dict = new Dict();
        BeanUtil.copyProperties(dictDto, dict, CopyOptions.create().setIgnoreNullValue(true));
        // 更新
        boolean flag = this.updateById(dict);
        if (!flag) {
            throw new HandleErrorException("更新失败");
        }
    }

    /**
     * 删除字典
     *
     * @param dictDto
     */
    @Override
    public void deleteDict(DictDto dictDto) {
        //  删除
        boolean flag = this.removeById(dictDto.getDictId());
        if (!flag) {
            throw new HandleErrorException("字典不存在或已被删除");
        }
    }

    /**
     * 批量删除字典
     *
     * @param dictDto
     */
    @Override
    public void batchDeleteDict(DictDto dictDto) {
        // 批量删除字典
        boolean flag = this.removeByIds(dictDto.getDictIds());
        if (!flag) {
            throw new HandleErrorException("字典不存在或已被删除");
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
        // 查询分页
        PageQuery query = new PageQuery(params);
        Page<Dict> page = new Page<>(query.getCurrent(), query.getPageSize());
        // 构造条件
        LambdaQueryWrapper<Dict> wrapper = new QueryWrapper<Dict>().lambda().eq(Dict::getParentId, -1L);
        // 查询条件
        IPage<Dict> dicts = this.page(page,wrapper);
        // 返回结果
        Pagination pagination = new Pagination(dicts.getTotal(), dicts.getSize(), dicts.getCurrent());
        return new PageData<>(pagination, dicts.getRecords());
    }

    /**
     * 查询字典树形
     *
     * @param dictId
     * @return
     */
    @Override
    public List<DictTree> dictTree(Long dictId) {
        // 构造条件
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        Long root = -1L;
        if (ObjectUtil.isNotNull(dictId)) {
//            root = dictId;
            queryWrapper.lambda().eq(Dict::getId, dictId).or().eq(Dict::getParentId, dictId);
        }
        // 查询字典列表
        List<Dict> list = this.list(queryWrapper);
        // 变树形
        List<DictTree> trees = BuildTreeUtil.buildDictTree(list, root);
        return trees;
    }

    /**
     * 根据字典类型(dictKey) 获取字典树形
     *
     * @param dictKey
     * @return
     */
    @Override
    public List<DictTree> dictOneTree(String dictKey) {
        // 判断dictKey是否为空
        if (StrUtil.isBlank(dictKey)) {
            throw new HandleErrorException("字典类型不能为空");
        }
        //查询dictList
        List<Dict> list = baseMapper.dictOneTree(dictKey);
        // 变树形
        List<DictTree> trees = BuildTreeUtil.buildDictTree(list, -1L);
        return trees;
    }

    /**
     * 根据字典类型(dictKey) 获取列表
     *
     * @param dictKey
     * @return
     */
    @Override
    public List<DictTree> dictOneList(String dictKey) {
        // 判断dictKey是否为空
        if (StrUtil.isBlank(dictKey)) {
            throw new HandleErrorException("字典类型不能为空");
        }
        //查询dictList
        List<Dict> list = baseMapper.dictOneList(dictKey);
        List<DictTree> dicts = new ArrayList<DictTree>();
        DictTree dictTree = null;
        for (Dict dict : list) {
            dictTree = new DictTree();
            BeanUtils.copyProperties(dict, dictTree);
            dictTree.setName(dict.getDictValue());
            dictTree.setKey(dict.getDictKey());
            dictTree.setValue(dict.getDictValue());
            dicts.add(dictTree);
        }
        return dicts;
    }
}
