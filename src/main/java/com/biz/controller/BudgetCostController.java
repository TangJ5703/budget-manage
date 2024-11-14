package com.biz.controller;


import com.biz.common.PageParam;
import com.biz.common.PageResult;
import com.biz.common.Result;
import com.biz.entity.BudgetCost;
import com.biz.model.AdjustRequestDTO;
import com.biz.model.AlterRequestDTO;
import com.biz.service.BudgetCostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;


/**
 * 预算费用(BudgetCost)表控制层
 *
 * @author TangJ
 * @since 2024-11-11 14:06:56
 */
@RestController
@RequestMapping("/cost")
@Api(tags = "预算费用")
public class BudgetCostController {

    @Resource
    private BudgetCostService budgetCostService;

    /**
     * 条件查询
     */
    @ApiOperation("条件查询")
    @GetMapping("/findByConditions")
    public PageResult<BudgetCost> findByConditions(@RequestBody BudgetCost budgetCost, PageParam pageParam) {

        return budgetCostService.findByConditions(budgetCost, pageParam);
    }

    /**
     * 批量删除
     * @return 返回受影响的行数
     */
    @ApiOperation("批量删除")
    @DeleteMapping("/deleteByIds")
    public Result<?> deleteByIds(@RequestBody Integer[] ids) {
        return Result.success(budgetCostService.deleteByIds(ids));
    }

    /**
     * 批量禁用
     */
    @ApiOperation("批量禁用")
    @PatchMapping("/disableByIds")
    public Result<?> disableByIds(@RequestBody Integer[] ids) {
        return Result.success(budgetCostService.disableByIds(ids));
    }

    /**
     * 批量启用
     */
    @ApiOperation("批量启用")
    @PatchMapping("/enableByIds")
    public Result<?> enableByIds(@RequestBody Integer[] ids) {
        return Result.success(budgetCostService.enableByIds(ids));
    }

    /**
     * 新增
     */
    @ApiOperation("新增")
    @PostMapping("/create")
    public Result<?> create(@RequestBody BudgetCost budgetCost) {
        return Result.success(budgetCostService.create(budgetCost));
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PatchMapping("/update")
    public Result<?> update(@RequestBody BudgetCost budgetCost) {
        return Result.success(budgetCostService.update(budgetCost));
    }


    @ApiOperation("调整")
    @PatchMapping("/adjust")
    public Result<?> adjust(@RequestBody AdjustRequestDTO adjustRequestDTO) {
        return Result.success(budgetCostService.adjust(adjustRequestDTO));
    }

    @ApiOperation("变更")
    @PatchMapping("/alter")
    public Result<?> change(@RequestBody AlterRequestDTO alterRequestDTO) {
        return Result.success(budgetCostService.alter(alterRequestDTO));
    }
}

