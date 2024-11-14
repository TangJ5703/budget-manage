package com.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.biz.common.PageParam;
import com.biz.common.PageResult;
import com.biz.entity.BudgetCost;
import com.biz.model.AdjustRequestDTO;
import com.biz.model.AlterRequestDTO;

import java.math.BigDecimal;

/**
 * 预算费用(BudgetCost)表服务接口
 *
 * @author TangJ
 * @since 2024-11-11 14:07:02
 */
public interface BudgetCostService  {

    PageResult<BudgetCost> findByConditions(BudgetCost budgetCost, PageParam pageParam);

    Integer deleteByIds(Integer[] ids);

    Integer disableByIds(Integer[] ids);

    Integer enableByIds(Integer[] ids);

    Integer create(BudgetCost budgetCost);

    Integer update(BudgetCost budgetCost);

    Void adjust(AdjustRequestDTO adjustRequestDTO);

    Void alter(AlterRequestDTO alterRequestDTO);
}

