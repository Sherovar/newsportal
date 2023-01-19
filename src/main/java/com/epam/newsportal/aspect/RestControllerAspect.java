package com.epam.newsportal.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RestControllerAspect {
    @Pointcut("execution(* com.epam.newsportal.controller.RestContentController.getByIdContent())")
    public void methodExecution(){}

    @AfterReturning(value = "within(com.epam.newsportal.controller.RestContentController)", returning = "returningValue")
    public void checkReturning(Object returningValue){
        System.out.println(returningValue);
    }


    @Before("within(com.epam.newsportal.controller.RestContentController)")
    public void bfoer(){
        System.out.println("BEFORE");
    }

}
