package com.biz.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biz.annotation.Log;
import com.biz.annotation.OperateType;
import com.biz.common.PageParam;
import com.biz.common.PageResult;
import com.biz.constant.AlterType;
import com.biz.constant.BudgetType;
import com.biz.entity.BudgetCost;
import com.biz.entity.BudgetCostDetail;
import com.biz.mapper.BudgetCostDetailMapper;
import com.biz.mapper.BudgetCostMapper;
import com.biz.model.AdjustRequestDTO;
import com.biz.model.AlterRequestDTO;
import com.biz.service.BudgetCostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 预算费用(BudgetCost)表服务实现类
 *
 * @author TangJ
 * @since 2024-11-11 14:07:03
 */
@Service
public class BudgetCostServiceImpl implements BudgetCostService {

    @Resource
    private BudgetCostMapper budgetCostMapper;

    @Resource
    private BudgetCostDetailMapper budgetCostDetailMapper;


    /**
     * @param budgetCost 查询条件
     * @param pageParam 分页参数
     * @return  分页结果
     */
    @Override
    public PageResult<BudgetCost> findByConditions(BudgetCost budgetCost, PageParam pageParam) {
        LambdaQueryWrapper<BudgetCost> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ObjectUtil.isNotNull(budgetCost.getCode()), BudgetCost::getCode, budgetCost.getCode())
                .eq(ObjectUtil.isNotNull(budgetCost.getType()), BudgetCost::getType, budgetCost.getSubjectName())
                .eq(ObjectUtil.isNotNull(budgetCost.getClient()), BudgetCost::getClient, budgetCost.getClient())
                .eq(ObjectUtil.isNotNull(budgetCost.getOrganization()), BudgetCost::getOrganization, budgetCost.getOrganization())
                .eq(ObjectUtil.isNotNull(budgetCost.getChannel()), BudgetCost::getChannel, budgetCost.getChannel());

        // 查询未逻辑删除的数据
        queryWrapper.eq(BudgetCost::getDeleteFlag, 0);

        Page<BudgetCost> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());
        page = budgetCostMapper.selectPage(page, queryWrapper);
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
        LambdaUpdateWrapper<BudgetCost> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(BudgetCost::getDeleteFlag, 1).in(BudgetCost::getId, ids);

        return budgetCostMapper.update(null, updateWrapper);
    }

    /**
     * 批量禁用
     * @param ids 禁用的id
     * @return  影响的行数
     */
    @Override
    @Log(OperateType.DISABLE)
    public Integer disableByIds(Integer[] ids) {
        // 批量修改status属性
        LambdaUpdateWrapper<BudgetCost> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(BudgetCost::getStatus, 0).in(BudgetCost::getId, ids);

        return budgetCostMapper.update(null, updateWrapper);
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
        LambdaUpdateWrapper<BudgetCost> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(BudgetCost::getStatus, 1).in(BudgetCost::getId, ids);

        return budgetCostMapper.update(null, updateWrapper);
    }

    /**
     * 新增
     * @param budgetCost 实体
     * @return  影响的行数
     */
    @Override
    @Log(OperateType.CREATE)
    public Integer create(BudgetCost budgetCost) {
        LambdaQueryWrapper<BudgetCost> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(BudgetCost::getCode).last("LIMIT 1");
        BudgetCost latest = budgetCostMapper.selectOne(queryWrapper);
        if (latest != null) {
            budgetCost.setCode(incrementCode(latest.getCode(), 4, 4));
        }
        else {
            budgetCost.setCode("FYYS0001");
        }
        return budgetCostMapper.insert(budgetCost);
    }


    /**
     * @param code 最新数据的编码
     * @return 最新编码 + 1
     */
    private String incrementCode(String code, Integer prefixLength, Integer numberLength) {
        // 提取前缀和数字部分
        String prefix = code.substring(0, prefixLength);
        String numberPart = code.substring(numberLength);

        // 数字部分转为整数并加1
        int number = Integer.parseInt(numberPart) + 1;

        // 格式化成原来的长度
        String format = "%0" + numberLength + "d"; // 动态生成格式字符串
        String newNumberPart = String.format(format, number);

        // 拼接前缀和新的数字部分
        return prefix + newNumberPart;
    }

    /**
     * 修改
     * @param budgetCost 需要修改的属性
     * @return  影响的行数
     */
    @Override
    @Log(OperateType.UPDATE)
    public Integer update(BudgetCost budgetCost) {
        return budgetCostMapper.updateById(budgetCost);
    }



    /**
     * 调整预算
     * @param adjustRequestDTO 调整请求传输对象
     */
    @Override
    @Transactional
    public Void adjust(AdjustRequestDTO adjustRequestDTO) {

        // 调出方
        BudgetCost out = budgetCostMapper.selectById(adjustRequestDTO.getOut());
        if (ObjectUtil.isNull(out)) {
            throw new RuntimeException("调出方不存在");
        }

        // 调入方
        BudgetCost in = budgetCostMapper.selectById(adjustRequestDTO.getIn());
        if (ObjectUtil.isNull(in)) {
            throw new RuntimeException("调入方不存在");
        }

        BudgetCostDetail costDetail = BudgetCostDetail.builder()
                .operateType(com.biz.constant.OperateType.OUT)
                .budgetSubject(out.getSubjectName())
                .detailType(adjustRequestDTO.getType())
                .organization(out.getOrganization())
                .client(out.getClient())
                .channel(out.getChannel())
                .shop(out.getShop())
                .operateAmount(adjustRequestDTO.getAmount())
                .comment(adjustRequestDTO.getComment())
                .operator(1L)
                .build();

        // 调整余额
        if (BudgetType.CASH.equals(adjustRequestDTO.getType())) {
            // 判断现金余额是否足够
            if (out.getAvailableBalanceCash().compareTo(adjustRequestDTO.getAmount()) < 0) {
                throw new RuntimeException("现金余额不足");
            }

            costDetail.setOpeningAmount(out.getOpeningAmountCash());
            costDetail.setBeforeOperate(out.getAvailableBalanceCash());
            costDetail.setAfterOperate(out.getAvailableBalanceCash().subtract(adjustRequestDTO.getAmount()));

            // 现金调整
            out.setAvailableBalanceCash(out.getAvailableBalanceCash().subtract(adjustRequestDTO.getAmount()));
            in.setAvailableBalanceCash(in.getAvailableBalanceCash().add(adjustRequestDTO.getAmount()));

        } else if (BudgetType.STORES.equals(adjustRequestDTO.getType())) {
            // 判断物料余额是否足够
            if (out.getAvailableBalanceStores().compareTo(adjustRequestDTO.getAmount()) < 0) {
                throw new RuntimeException("物料余额不足");
            }

            costDetail.setOpeningAmount(out.getOpeningAmountStores());
            costDetail.setBeforeOperate(out.getAvailableBalanceStores());
            costDetail.setAfterOperate(out.getAvailableBalanceStores().subtract(adjustRequestDTO.getAmount()));

            //物料
            out.setAvailableBalanceStores(out.getAvailableBalanceStores().subtract(adjustRequestDTO.getAmount()));
            in.setAvailableBalanceStores(in.getAvailableBalanceStores().add(adjustRequestDTO.getAmount()));
        }


        budgetCostDetailMapper.insert(costDetail);

        // 更新数据库
        budgetCostMapper.updateById(out);
        budgetCostMapper.updateById(in);

        return null;
    }


    @Override
    public Void alter(AlterRequestDTO alterRequestDTO) {
        BudgetCost budgetCost = budgetCostMapper.selectById(alterRequestDTO.getCostId());

        if (ObjectUtil.isNull(budgetCost)) {
            throw new RuntimeException("预算费用不存在");
        }

        BudgetCostDetail costDetail = BudgetCostDetail.builder()
                .operateType(alterRequestDTO.getAlterType())
                .budgetSubject(budgetCost.getSubjectName())
                .detailType(alterRequestDTO.getType())
                .organization(budgetCost.getOrganization())
                .client(budgetCost.getClient())
                .channel(budgetCost.getChannel())
                .shop(budgetCost.getShop())
                .operateAmount(alterRequestDTO.getAlterAmount())
                .comment(alterRequestDTO.getComment())
                .operator(1L)
                .build();

        // 现金变更
        if (BudgetType.CASH.equals(alterRequestDTO.getType())){
            if (AlterType.DROP.equals(alterRequestDTO.getAlterType())) {
                if (budgetCost.getAvailableBalanceCash().compareTo(alterRequestDTO.getAlterAmount()) < 0) {
                    throw new RuntimeException("现金余额不足");
                }

                costDetail.setOpeningAmount(budgetCost.getOpeningAmountCash());
                costDetail.setBeforeOperate(budgetCost.getAvailableBalanceCash());
                costDetail.setAfterOperate(budgetCost.getAvailableBalanceCash().subtract(alterRequestDTO.getAlterAmount()));

                budgetCost.setAvailableBalanceCash(budgetCost.getAvailableBalanceCash().subtract(alterRequestDTO.getAlterAmount()));

            }
            if (AlterType.ADD.equals(alterRequestDTO.getAlterType())) {

                costDetail.setOpeningAmount(budgetCost.getOpeningAmountCash());
                costDetail.setBeforeOperate(budgetCost.getAvailableBalanceCash());
                costDetail.setAfterOperate(budgetCost.getAvailableBalanceCash().add(alterRequestDTO.getAlterAmount()));

                budgetCost.setAvailableBalanceCash(budgetCost.getAvailableBalanceCash().add(alterRequestDTO.getAlterAmount()));

            }


        }

        // 物料变更
        if (BudgetType.STORES.equals(alterRequestDTO.getType())){
            if (AlterType.DROP.equals(alterRequestDTO.getAlterType())) {
                if (budgetCost.getAvailableBalanceStores().compareTo(alterRequestDTO.getAlterAmount()) < 0) {
                    throw new RuntimeException("物料余额不足");
                }

                costDetail.setOpeningAmount(budgetCost.getOpeningAmountStores());
                costDetail.setBeforeOperate(budgetCost.getAvailableBalanceStores());
                costDetail.setAfterOperate(budgetCost.getAvailableBalanceStores().subtract(alterRequestDTO.getAlterAmount()));

                budgetCost.setAvailableBalanceStores(budgetCost.getAvailableBalanceCash().subtract(alterRequestDTO.getAlterAmount()));

            }
            if (AlterType.ADD.equals(alterRequestDTO.getAlterType())) {

                costDetail.setOpeningAmount(budgetCost.getOpeningAmountStores());
                costDetail.setBeforeOperate(budgetCost.getAvailableBalanceStores());
                costDetail.setAfterOperate(budgetCost.getAvailableBalanceStores().add(alterRequestDTO.getAlterAmount()));

                budgetCost.setAvailableBalanceStores(budgetCost.getAvailableBalanceStores().add(alterRequestDTO.getAlterAmount()));

            }
        }


        budgetCostDetailMapper.insert(costDetail);

        budgetCostMapper.updateById(budgetCost);

        return null;
    }


}

