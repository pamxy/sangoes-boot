package com.sangoes.boot.uc.modules.admin.controller;


import com.sangoes.boot.common.controller.BaseController;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.uc.modules.admin.dto.DictDto;
import com.sangoes.boot.uc.modules.admin.entity.Dict;
import com.sangoes.boot.uc.modules.admin.service.IDictService;
import com.sangoes.boot.uc.modules.admin.vo.DictTree;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
     * 更新(修改)字典
     *
     * @param dictDto
     * @return
     */
    @PutMapping("/update")
    @ApiOperation(value = "更新(修改)字典", notes = "返回更新结果")
    @ResponseBody
    public Result<String> updateUser(@RequestBody @Validated({DictDto.UpdateDictGroup.class}) DictDto dictDto) {
        // 更新
        dictService.updateDict(dictDto);
        return Result.success("更新成功");
    }

    /**
     * 删除字典
     *
     * @param dictDto
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除字典", notes = "返回删除结果")
    @ResponseBody
    public Result<String> deleteDict(@RequestBody @Validated(DictDto.DeleteDictGroup.class) DictDto dictDto) {
        // 删除字典
        dictService.deleteDict(dictDto);
        return Result.success("删除成功");
    }

    /**
     * 批量删除字典
     *
     * @param dictDto
     * @return
     */
    @DeleteMapping("/batch/delete")
    @ApiOperation(value = "批量删除字典", notes = "返回删除结果")
    @ResponseBody
    public Result<String> batchDeleteDict(@RequestBody @Validated(DictDto.BatchDeleteDictGroup.class) DictDto dictDto) {
        // 批量删除字典
        dictService.batchDeleteDict(dictDto);
        return Result.success("删除成功");
    }

    /**
     * 字典分页
     * parentId为-1
     *
     * @param params
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "字典分页", notes = "返回分页结果 parentId为-1")
    @ResponseBody
    public Result<PageData<Dict>> pageDict(@RequestParam Map<String, Object> params) {
        PageData<Dict> dicts = dictService.pageDict(params);
        return Result.success(dicts, "成功");
    }

    /**
     * 获取字典树形结果
     * 当不传dictId查询全部
     *
     * @return
     */
    @GetMapping("/tree/{dictId}")
    @ApiOperation(value = "获取字典树形结果", notes = "返回树形结果 当不传dictId查询全部")
    @ResponseBody
    public Result<List<DictTree>> dictTree(@PathVariable Long dictId) {
        List<DictTree> trees = dictService.dictTree(dictId);
        return Result.success(trees, "获取成功");
    }
}
