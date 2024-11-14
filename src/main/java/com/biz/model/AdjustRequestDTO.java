package com.biz.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 *  预算费用调整DTO
 *  @author TangJ
 *  @since 2024/11/12 14:21
 **/
@Data
public class AdjustRequestDTO {
    // 调出方ID
    private Long out;

    // 调入方ID
    private Long in;

    // 调整金额
    private BigDecimal amount;

    // 调整类型
    private String type;

    // 备注
    private String comment;
}
