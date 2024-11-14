package com.biz.controller;

import com.biz.common.PageParam;
import com.biz.common.PageResult;
import com.biz.entity.BudgetCostDetail;
import com.biz.service.BudgetCostDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author TangJ
 * @since 2024/11/12 16:59
 **/
@RestController
@RequestMapping("/cost/detail")
@Api(tags = "预算成本明细")
public class BudgetCostDetailController {

    @Resource
    private BudgetCostDetailService budgetCostDetailService;

    @ApiOperation("条件查询")
    @GetMapping("/findByConditions")
    public PageResult<BudgetCostDetail> findByConditions(@RequestBody BudgetCostDetail budgetCostDetail, PageParam pageParam){
        return budgetCostDetailService.findByConditions(budgetCostDetail, pageParam);
    }
}
