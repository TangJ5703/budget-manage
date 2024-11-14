package com.biz.annotation;

import java.lang.annotation.*;

/**
 * @author TangJ
 * @since 2024/11/12 10:42
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Log {
    OperateType value();
}
