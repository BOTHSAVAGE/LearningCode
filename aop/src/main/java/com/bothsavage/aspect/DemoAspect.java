package com.bothsavage.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect //切面
public class DemoAspect {


    //切入点1：匹配com.bothsavage.service.DemoService类下的方法名以demo开头、参数类型为int的public方法
    @Pointcut("execution(public * com.bothsavage.service.DemoService.demo*(int))")
    public void matchCondition() {}

    //切入点2：匹配com.bothsavage.service.DemoService类下的方法名以demo开头、参数类型为String的public方法
    @Pointcut("execution(public * com.bothsavage.service.DemoService.demo1*(long))")
    public void matchCondition_() {}

    //前置
    @Before("matchCondition()")
    public void before() {
        System.out.println("Before");
    }

    //全局后置
    @After("matchCondition()")
    public void after(){
        System.out.println("after");
    }

    //返回后置
    @AfterReturning("matchCondition()")
    public void afterReturning(){
        System.out.println("afterReturning");
    }

    //抛出后置
    @AfterThrowing("matchCondition()")
    public void afterThrowing(){
        System.out.println("afterThrowing");
    }

    @Around("matchCondition_()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object result = null;
        System.out.println("before");
        try{
            result = joinPoint.proceed(joinPoint.getArgs());//获取参数
            System.out.println("after");
        } catch (Throwable e) {
            System.out.println("after exception");
            e.printStackTrace();
        } finally {
            System.out.println("finally");
        }
        return result;
    }

}