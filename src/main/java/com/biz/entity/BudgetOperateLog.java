package com.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 预算操作记录
 * @TableName budget_operate_log
 */
@TableName(value ="budget_operate_log")
@Data
public class BudgetOperateLog  {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 操作人id
     */
    private Long operatorId;

    /**
     * 操作人姓名
     */
    private String operatorName;

    /**
     * 操作时间
     */
    private Date operateTime;

    /**
     * 操作类型
     */
    private String operateType;


}