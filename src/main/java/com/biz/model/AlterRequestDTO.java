package com.biz.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author TangJ
 * @since 2024/11/13 16:57
 **/
@Data
public class AlterRequestDTO {

    /**
     * 变更的预算费用id
     */
    private Long costId;

    /**
     * 类型（现金或物料）
     */
    private String type;

    /**
     * 变更类型（削减或追加）
     */
    private String alterType;

    /**
     * 变更金额
     */
    private BigDecimal alterAmount;

    /**
     * 备注
     */
    private String comment;
}
