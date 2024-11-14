package com.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 预算科目
 * @TableName budget_subject
 */
@TableName(value ="budget_subject")
@Data
public class BudgetSubject implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 预算编码
     */
    @NotBlank(message = "预算编码不能为空")
    private String code;

    /**
     * 预算科目名称
     */
    @NotBlank(message = "预算科目名称不能为空")
    @TableField(value = "`name`")
    private String name;

    /**
     * 预算科目类型
     */
    @TableField(value = "`type`")
    private Integer type;

    /**
     * 预算科目分组
     */
    @TableField(value = "`group`")
    private Integer group;

    /**
     * 控制类型
     */
    private Integer controlType;

    /**
     * 启用状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String comment;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除状态
     */
    private Integer deleteFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}