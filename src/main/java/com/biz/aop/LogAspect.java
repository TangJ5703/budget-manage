package com.biz.aop;

import com.biz.annotation.Log;
import com.biz.entity.BudgetOperateLog;
import com.biz.mapper.BudgetOperateLogMapper;
import com.biz.model.AdjustRequestDTO;
import com.biz.model.AlterRequestDTO;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
public class LogAspect {

    @Resource
    private BudgetOperateLogMapper budgetOperateLogMapper;

    // 切点，拦截所有标注了 @Log 的方法
    @Pointcut("@annotation(logOperation)")
    public void logPointcut(Log logOperation) {}

    // 前置通知：记录操作前的信息
    @Before("logPointcut(logOperation)")
    public void beforeLog(JoinPoint joinPoint, Log logOperation) {
        Object[] params = joinPoint.getArgs();


        BudgetOperateLog log = new BudgetOperateLog();

        // 真实情况应从ThreadLocal上下文获取用户信息
        log.setOperatorId(getCurrentUserId());
        log.setOperatorName(getCurrentUserName());
        log.setOperateType(logOperation.value().name());


        budgetOperateLogMapper.insert(log);  // 保存到数据库
    }

    private Long getCurrentUserId() {
        // 模拟获取当前用户ID，实际需要从登录上下文或线程中获取
        return 1L;
    }

    private String getCurrentUserName() {
        // 模拟获取当前用户名
        return "Admin";
    }
}
