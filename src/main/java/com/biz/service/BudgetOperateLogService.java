package com.biz.service;

import com.biz.common.PageParam;
import com.biz.common.PageResult;
import com.biz.entity.BudgetOperateLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author TangJ
* @description 针对表【budget_operate_log(预算操作记录)】的数据库操作Service
* @createDate 2024-11-12 10:40:38
*/
public interface BudgetOperateLogService {

    PageResult<BudgetOperateLog> findByConditions(BudgetOperateLog budgetOperateLog, PageParam pageParam);
}
