package com.biz.annotation;

/**
 * @author TangJ
 * @since 2024/11/12 10:59
 **/
public enum OperateType {
    CREATE("新增"),
    UPDATE("编辑"),
    DELETE("删除"),
    DISABLE("禁用"),
    ENABLE("启用");

    private final String description;

    OperateType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
