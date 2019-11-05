package com.zsx.aop;

import com.zsx.annotation.CompanyDeal;
import com.zsx.entity.Company;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
public class CompanyAop {

    @Pointcut("@annotation(companyDeal)")
    public void pointcut(CompanyDeal companyDeal) {
        System.out.println("Pointcut==============" + companyDeal.value());
        System.out.println(companyDeal.value());
    }

    @Before("@annotation(companyDeal)")
    public void before(CompanyDeal companyDeal) {
        System.out.println("Before==============" + companyDeal.value());
    }


    @After("@annotation(companyDeal)")
    public void after(JoinPoint point, CompanyDeal companyDeal) throws Exception{
        System.out.println("After==============" + companyDeal.value());
    }

    @Around("@annotation(companyDeal)")
    public Object around(ProceedingJoinPoint point, CompanyDeal companyDeal) throws Throwable {
        Object obj = point.proceed();
        System.out.println("Around==============" + companyDeal.value());
        if (obj == null) {
            return new Company(7L, "Allen", 20, "", null, new Date());
        }
        return obj;
    }

    @AfterReturning("@annotation(companyDeal)")
    public void afterReturning(CompanyDeal companyDeal) {
        System.out.println("AfterReturning==============" + companyDeal.value());
    }

    @AfterThrowing("@annotation(companyDeal)")
    public void afterThrowing(CompanyDeal companyDeal) {
        System.out.println("AfterThrowing==============" + companyDeal.value());
    }
}
