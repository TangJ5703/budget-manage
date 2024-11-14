package com.biz.common;

import lombok.Data;

@Data
public class PageParam {
    // 当前页码
    private Integer pageNum = 1;

    // 每页记录数
    private Integer pageSize = 20;

}
