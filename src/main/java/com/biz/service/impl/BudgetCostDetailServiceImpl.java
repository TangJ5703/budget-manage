package com.biz.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biz.common.PageParam;
import com.biz.common.PageResult;
import com.biz.entity.BudgetCost;
import com.biz.entity.BudgetCostDetail;
import com.biz.entity.BudgetSubject;
import com.biz.service.BudgetCostDetailService;
import com.biz.mapper.BudgetCostDetailMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author TangJ
* @description 针对表【budget_cost_detail(费用明细)】的数据库操作Service实现
* @createDate 2024-11-12 16:55:42
*/
@Service
public class BudgetCostDetailServiceImpl implements BudgetCostDetailService{

    @Resource
    private BudgetCostDetailMapper budgetCostDetailMapper;

    @Override
    public PageResult<BudgetCostDetail> findByConditions(BudgetCostDetail budgetCostDetail, PageParam pageParam) {
        LambdaQueryWrapper<BudgetCostDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ObjectUtil.isNotNull(budgetCostDetail.getOperateType()), BudgetCostDetail::getOperateType, budgetCostDetail.getOperateType())
                .eq(ObjectUtil.isNotNull(budgetCostDetail.getDetailType()), BudgetCostDetail::getDetailType, budgetCostDetail.getDetailType());



        Page<BudgetCostDetail> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());
        budgetCostDetailMapper.selectPage(page, queryWrapper);
        return new PageResult<>(page.getRecords(),page.getTotal(),pageParam.getPageNum(),pageParam.getPageSize());
    }


}




