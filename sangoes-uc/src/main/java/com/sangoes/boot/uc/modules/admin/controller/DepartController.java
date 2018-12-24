package com.sangoes.boot.uc.modules.admin.controller;


import com.sangoes.boot.common.controller.BaseController;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.uc.modules.admin.dto.DepartDto;
import com.sangoes.boot.uc.modules.admin.service.IDepartService;
import com.sangoes.boot.uc.modules.admin.vo.DepartTree;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author jerrychir
 * @since 2018-12-21
 */
@RestController
@RequestMapping("/admin/depart")
@Api("部门/公司管理")
@Slf4j
public class DepartController extends BaseController {

    @Autowired
    private IDepartService departService;

    /**
     * 添加部门/公司
     *
     * @param departDto
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加部门/公司", notes = "返回添加信息")
    @ResponseBody
    public Result<String> addDepart(@RequestBody @Validated(DepartDto.AddDepartGroup.class) DepartDto departDto) {
        departService.saveDepart(departDto);
        return Result.success("添加成功");
    }

    /**
     * 获取部门树形结果
     *
     * @return
     */
    @GetMapping("/tree")
    @ApiOperation(value = "获取部门树形结果", notes = "返回树形结果")
    @ResponseBody
    public Result<List<DepartTree>> getDepartTree() {
        List<DepartTree> departTrees = departService.getDepartTree();
        return Result.success(departTrees, "获取成功");
    }

}
