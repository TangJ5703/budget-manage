package com.biz.service;

import com.biz.common.PageParam;
import com.biz.common.PageResult;
import com.biz.entity.BudgetSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

/**
* @author TangJ
* @description 针对表【budget_subject(预算科目)】的数据库操作Service
* @createDate 2024-11-11 14:20:53
*/
public interface BudgetSubjectService  {

    PageResult<BudgetSubject> findByConditions(BudgetSubject budgetSubject, PageParam pageParam);

    Integer deleteByIds(Integer[] ids);

    Integer disableByIds(Integer[] ids);

    Integer enableByIds(Integer[] ids);

    Integer create(BudgetSubject budgetSubject);

    Integer update(BudgetSubject budgetSubject);
}
