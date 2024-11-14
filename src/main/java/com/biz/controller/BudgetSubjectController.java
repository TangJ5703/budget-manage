package com.biz.controller;

import com.biz.common.PageParam;
import com.biz.common.PageResult;
import com.biz.common.Result;
import com.biz.entity.BudgetSubject;
import com.biz.service.BudgetSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author TangJ
 * @since 2024/11/11 14:35
 **/
@RestController
@RequestMapping("/subject")
@Api(tags = "预算科目")
public class BudgetSubjectController {

    @Resource
    private BudgetSubjectService budgetSubjectService;

    @ApiOperation("条件查询")
    @GetMapping("/findByConditions")
    public PageResult<BudgetSubject> findByConditions(@RequestBody BudgetSubject budgetSubject, PageParam pageParam){
        return budgetSubjectService.findByConditions(budgetSubject, pageParam);
    }

    /**
     * 批量删除
     * @return 返回受影响的行数
     */
    @ApiOperation("批量删除")
    @DeleteMapping("/deleteByIds")
    public Result<?> deleteByIds(@RequestBody Integer[] ids) {
        return Result.success(budgetSubjectService.deleteByIds(ids));
    }

    /**
     * 批量禁用
     */
    @ApiOperation("批量禁用")
    @PatchMapping("/disableByIds")
    public Result<?> disableByIds(@RequestBody Integer[] ids) {
        return Result.success(budgetSubjectService.disableByIds(ids));
    }

    /**
     * 批量启用
     */
    @ApiOperation("批量启用")
    @PatchMapping("/enableByIds")
    public Result<?> enableByIds(@RequestBody Integer[] ids) {
        return Result.success(budgetSubjectService.enableByIds(ids));
    }


    /**
     * 新增
     */
    @ApiOperation("新增")
    @PostMapping("/create")
    public Result<?> create(@RequestBody @Valid BudgetSubject budgetSubject) {
        return Result.success(budgetSubjectService.create(budgetSubject));
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PatchMapping("/update")
    public Result<?> update(@RequestBody @Valid BudgetSubject budgetSubject) {
        return Result.success(budgetSubjectService.update(budgetSubject));
    }
}
