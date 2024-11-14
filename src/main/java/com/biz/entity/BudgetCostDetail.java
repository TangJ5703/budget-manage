package com.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

/**
 * 费用明细
 * @TableName budget_cost_detail
 */
@TableName(value ="budget_cost_detail")
@Data
@Builder
public class BudgetCostDetail implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 业务编码
     */
    private String serviceCode;

    /**
     * 科目
     */
    private String budgetSubject;

    /**
     * 明细类型
     */
    private String detailType;

    /**
     * 组织
     */
    private String organization;

    /**
     * 渠道
     */
    private String channel;

    /**
     * 客户名称
     */
    private String client;

    /**
     * 门店名称
     */
    private String shop;

    /**
     * 初期金额
     */
    private BigDecimal openingAmount;

    /**
     * 操作前金额
     */
    private BigDecimal beforeOperate;

    /**
     * 操作的金额
     */
    private BigDecimal operateAmount;

    /**
     * 操作后余额
     */
    private BigDecimal afterOperate;

    /**
     * 备注
     */
    private String comment;

    /**
     * 操作人
     */
    private Long operator;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}