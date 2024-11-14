package com.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biz.common.PageParam;
import com.biz.common.PageResult;
import com.biz.entity.BudgetOperateLog;
import com.biz.service.BudgetOperateLogService;
import com.biz.mapper.BudgetOperateLogMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author TangJ
* @description 针对表【budget_operate_log(预算操作记录)】的数据库操作Service实现
* @createDate 2024-11-12 10:40:38
*/
@Service
public class BudgetOperateLogServiceImpl implements BudgetOperateLogService{

    @Resource
    private BudgetOperateLogMapper budgetOperateLogMapper;

    @Override
    public PageResult<BudgetOperateLog> findByConditions(BudgetOperateLog budgetOperateLog, PageParam pageParam) {
        LambdaQueryWrapper<BudgetOperateLog> queryWrapper = new LambdaQueryWrapper<>();


        Page<BudgetOperateLog> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());
        budgetOperateLogMapper.selectPage(page, queryWrapper);
        return new PageResult<>(page.getRecords(),page.getTotal(),pageParam.getPageNum(),pageParam.getPageSize());
    }
}




