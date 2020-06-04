package com.example.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;


@Aspect
@Configuration
@Slf4j
public class Useraop {
//    @Pointcut ("execution(* com.example.demo.controller*.*(..))")
//    public void invoke(){
//      log.info("invoked aop");
//    }
    @Before("execution(* com.example.demo.controller.*.*(..))")
      public void beforefunction(JoinPoint joinPoint){
        log.warn("hello !!!!!! before on -- "+joinPoint);
    }
    @After("execution(* com.example.demo.controller.*.*(..))")
      public void afterfunction(JoinPoint joinPoint){
        log.warn("Bye !!!!!!after on -- "+joinPoint);
    }

}
