package com.biz.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.biz.entity.BudgetCost;
import com.biz.model.AdjustRequestDTO;
import org.mapstruct.Mapper;

/**
 * 预算费用(BudgetCost)表数据库访问层
 *
 * @author makejava
 * @since 2024-11-11 14:06:58
 */
@Mapper
public interface BudgetCostMapper extends BaseMapper<BudgetCost> {

}

