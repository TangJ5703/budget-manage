package com.biz.service;

import com.biz.common.PageParam;
import com.biz.common.PageResult;
import com.biz.entity.BudgetCostDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author TangJ
* @description 针对表【budget_cost_detail(费用明细)】的数据库操作Service
* @createDate 2024-11-12 16:55:42
*/
public interface BudgetCostDetailService {

    PageResult<BudgetCostDetail> findByConditions(BudgetCostDetail budgetCostDetail, PageParam pageParam);
}
