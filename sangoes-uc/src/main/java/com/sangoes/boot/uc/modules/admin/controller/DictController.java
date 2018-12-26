package com.sangoes.boot.uc.modules.admin.controller;


import com.sangoes.boot.common.controller.BaseController;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.uc.modules.admin.dto.DictDto;
import com.sangoes.boot.uc.modules.admin.entity.Dict;
import com.sangoes.boot.uc.modules.admin.service.IDictService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author jerrychir
 * @since 2018-12-26
 */
@RestController
@RequestMapping("/admin/dict")
public class DictController extends BaseController {

    @Autowired
    private IDictService dictService;

    /**
     * 添加字典
     *
     * @param dictDto
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加父字典", notes = "返回添加信息")
    @ResponseBody
    public Result<String> addDict(@RequestBody @Validated(DictDto.AddDictGroup.class) DictDto dictDto) {
        dictService.saveDict(dictDto);
        return Result.success("添加成功");
    }

    /**
     * 字典分页
     *
     * @param params
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "字典分页", notes = "返回分页结果")
    @ResponseBody
    public Result<PageData<Dict>> pageDict(@RequestParam Map<String, Object> params) {
        PageData<Dict> dicts = dictService.pageDict(params);
        return Result.success(dicts, "成功");
    }
}
