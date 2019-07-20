package com.rognrrr.springbootdemo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectDemo {

    @Pointcut("execution(* com.rognrrr.springbootdemo.controller.*.*(..))")
    public void LogAspect(){}

    @Before("LogAspect()")
    public void before(){
        System.out.println("before");
    }

    @After("LogAspect()")
    public void after(){
        System.out.println("after");
    }

    @AfterReturning("LogAspect()")
    public void afterReturning(JoinPoint joinPoint){
        System.out.println("afterReturning");
    }

    @AfterThrowing("LogAspect()")
    public void afterThrowing(JoinPoint joinPoint){
        System.out.println("afterThrowing");
    }

    @Around("LogAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around1");
        Object result = joinPoint.proceed();
        System.out.println("around2");
        return result;
    }


}
