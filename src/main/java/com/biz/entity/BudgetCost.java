package com.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 预算费用(BudgetCost)表实体类
 *
 * @author TangJ
 * @since 2024-11-11 14:06:59
 */
@Data
public class BudgetCost {

    //主键
    @TableId(type = IdType.AUTO)
    private Long id;

    //费用预算编码
    private String code;

    //费用预算类型
    private Integer type;

    //预算来源
    private Integer source;

    //年份
    private Integer year;

    //季度
    private Integer quarter;

    //月份
    private Integer month;

    //预算科目编码
    private String subjectCode;

    //预算科目名称
    private String subjectName;

    //组织
    private String organization;

    //渠道
    private String channel;

    //客户
    private String client;

    //门店
    private String shop;

    //产品层级
    private Integer productLevel;

    //产品
    private String product;

    //期初金额（现金）
    private BigDecimal openingAmountCash;

    //可用余额（现金）
    private BigDecimal availableBalanceCash;

    //期初金额（物料）
    private BigDecimal openingAmountStores;

    //可用余额（物料）
    private BigDecimal availableBalanceStores;

    //启用状态
    private Integer status;

    //备注
    private String comment;

    //创建时间
    private LocalDateTime createTime;

    //更新时间
    private LocalDateTime updateTime;

    //删除标志
    private Integer deleteFlag;

    @Version
    private Integer version;
}

