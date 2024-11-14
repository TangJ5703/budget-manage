package com.biz.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biz.annotation.Log;
import com.biz.annotation.OperateType;
import com.biz.common.PageParam;
import com.biz.common.PageResult;
import com.biz.entity.BudgetSubject;
import com.biz.service.BudgetSubjectService;
import com.biz.mapper.BudgetSubjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author TangJ
* @description 针对表【budget_subject(预算科目)】的数据库操作Service实现
* @createDate 2024-11-11 14:20:53
*/
@Service
public class BudgetSubjectServiceImpl implements BudgetSubjectService{

    @Resource
    private BudgetSubjectMapper budgetSubjectMapper;


    /**
     * @param budgetSubject 查询条件
     * @param pageParam 分页参数
     * @return  分页结果
     */
    @Override
    public PageResult<BudgetSubject> findByConditions(BudgetSubject budgetSubject, PageParam pageParam) {
        LambdaQueryWrapper<BudgetSubject> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ObjectUtil.isNotNull(budgetSubject.getName()), BudgetSubject::getName, budgetSubject.getName())
                .eq(ObjectUtil.isNotNull(budgetSubject.getCode()), BudgetSubject::getCode, budgetSubject.getCode())
                .eq(ObjectUtil.isNotNull(budgetSubject.getGroup()), BudgetSubject::getGroup, budgetSubject.getGroup())
                .eq(ObjectUtil.isNotNull(budgetSubject.getType()), BudgetSubject::getType, budgetSubject.getType())
                .eq(ObjectUtil.isNotNull(budgetSubject.getControlType()), BudgetSubject::getControlType, budgetSubject.getControlType())
                .eq(ObjectUtil.isNotNull(budgetSubject.getStatus()), BudgetSubject::getStatus, budgetSubject.getStatus());

        // 查询未逻辑删除的数据
        queryWrapper.eq(BudgetSubject::getDeleteFlag, 0);

        Page<BudgetSubject> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());
        budgetSubjectMapper.selectPage(page, queryWrapper);
        return new PageResult<>(page.getRecords(),page.getTotal(),pageParam.getPageNum(),pageParam.getPageSize());
    }

    /**
     * 批量删除
     * @param ids 要删除的id
     * @return  删除的行数
     */
    @Override
    @Log(OperateType.DELETE)
    public Integer deleteByIds(Integer[] ids) {
        LambdaUpdateWrapper<BudgetSubject> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(BudgetSubject::getDeleteFlag, 1).in(BudgetSubject::getId, ids);

        return budgetSubjectMapper.update(null, updateWrapper);
    }

    /**
     * 批量禁用
     * @param ids 要禁用的id
     * @return  影响的行数
     */
    @Override
    @Log(OperateType.DISABLE)
    public Integer disableByIds(Integer[] ids) {
        // 批量修改status属性
        LambdaUpdateWrapper<BudgetSubject> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(BudgetSubject::getStatus, 0).in(BudgetSubject::getId, ids);

        return budgetSubjectMapper.update(null, updateWrapper);
    }

    /**
     * 批量启用
     * @param ids 要启用的id
     * @return  影响的行数
     */

    @Override
    @Log(OperateType.ENABLE)
    public Integer enableByIds(Integer[] ids) {
        // 批量修改status属性
        LambdaUpdateWrapper<BudgetSubject> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(BudgetSubject::getStatus, 1).in(BudgetSubject::getId, ids);

        return budgetSubjectMapper.update(null, updateWrapper);
    }

    /**
     * 新增
     * @param budgetSubject 实体
     * @return  影响的行数
     */
    @Override
    @Log(OperateType.CREATE)
    public Integer create(BudgetSubject budgetSubject) {
        return budgetSubjectMapper.insert(budgetSubject);
    }


    /**
     * 修改
     * @param budgetSubject 需要修改的属性
     * @return  影响的行数
     */
    @Override
    @Log(OperateType.UPDATE)
    public Integer update(BudgetSubject budgetSubject) {
        return budgetSubjectMapper.updateById(budgetSubject);
    }
}




