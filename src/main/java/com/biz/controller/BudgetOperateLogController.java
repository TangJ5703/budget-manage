package com.biz.controller;

import com.biz.common.PageParam;
import com.biz.common.PageResult;
import com.biz.entity.BudgetOperateLog;
import com.biz.service.BudgetOperateLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author TangJ
 * @since 2024/11/12 17:10
 **/
@RestController
@RequestMapping("/operate-log")
@Api(tags = "预算操作日志")
public class BudgetOperateLogController {
    @Resource
    private BudgetOperateLogService budgetOperateLogService;

    @ApiOperation("操作日志")
    @GetMapping("/findByConditions")
    public PageResult<BudgetOperateLog> findByConditions(@RequestBody BudgetOperateLog budgetOperateLog, PageParam pageParam) {
        return budgetOperateLogService.findByConditions(budgetOperateLog, pageParam);
    }
}
