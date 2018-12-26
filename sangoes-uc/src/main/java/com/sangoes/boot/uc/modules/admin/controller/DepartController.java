package com.sangoes.boot.uc.modules.admin.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sangoes.boot.common.controller.BaseController;
import com.sangoes.boot.common.msg.Result;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.common.utils.page.PageQuery;
import com.sangoes.boot.uc.modules.admin.dto.DepartDto;
import com.sangoes.boot.uc.modules.admin.entity.SysUser;
import com.sangoes.boot.uc.modules.admin.service.IDepartService;
import com.sangoes.boot.uc.modules.admin.service.ISysUserService;
import com.sangoes.boot.uc.modules.admin.vo.DepartTree;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @Autowired
    private ISysUserService userService;

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
     * 更新(修改)部门
     *
     * @param departDto
     * @return
     */
    @PutMapping("/update")
    @ApiOperation(value = "更新(修改)部门", notes = "返回更新结果")
    @ResponseBody
    public Result<String> updateDepart(@RequestBody @Validated(DepartDto.UpdateDepartGroup.class) DepartDto departDto) {
        departService.updateDepart(departDto);
        return Result.success("更新成功");
    }

    /**
     * 删除部门
     *
     * @param departDto
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除部门", notes = "返回删除结果")
    @ResponseBody
    public Result<String> deleteMenu(@RequestBody @Validated(DepartDto.DeleteDepartGroup.class) DepartDto departDto) {
        departService.deleteDepart(departDto);
        return Result.success("删除成功");
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


    /**
     * 获取部门成员
     *
     * @param params
     * @return
     */
    @GetMapping("/members/page")
    @ApiOperation(value = "获取部门成员", notes = "返回列表结果")
    @ResponseBody
    public Result<PageData<SysUser>> listDepartMembers(@RequestParam Map<String, Object> params) {

        PageData<SysUser>users = userService.listDepartMembers(new PageQuery(params));
        return Result.success(users, "成功");
    }
}
