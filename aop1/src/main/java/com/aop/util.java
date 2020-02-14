package com.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
@Aspect
@Component
public class util {
    //抽取切入点表达式，实现重用
    //先定义空方法
    /*@Pointcut("execution(public int com.aop.Mathimp.*(int,int))")
    public void haha(){}*/

    //@Before("haha()")
    @Before("execution(public int com.aop.Mathimp.*(int,int))")
    public static void start(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        System.out.println("开始调用"+name+"他的参数是："+Arrays.asList(args));
    }

    //结果和异常要用参数接受，并且要告诉标志符
    //@AfterReturning(value = "haha()",returning = "result")
    @AfterReturning(value = "execution(public int com.aop.Mathimp.*(int,int))",returning = "result")//returning告诉spring第二个参数是结果
    public static void end(JoinPoint joinPoint,Object result){
        System.out.println(joinPoint.getSignature().getName()+"已经结束他的结果是："+result);
    }

    @AfterThrowing(value = "execution(public int com.aop.Mathimp.*(int,int))",throwing = "e")//throwing告诉spring第二个参数是异常
    public static void yichang(JoinPoint joinPoint,Exception e){
        System.out.println(joinPoint.getSignature().getName()+"出现异常了："+e);
    }
    @After(value = "execution(public int com.aop.Mathimp.*(int,int))")
    public static void zhengchang(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName()+"已经结束");
    }

    //环绕通知，四合一，有了他就不用上面四个了，他类似于java的method.invoke(mathimp, args)

    /* @Around("execution(public int com.aop.Mathimp.*(int,int))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //把前面的通知符注释掉，测试
        Object[] args = proceedingJoinPoint.getArgs();
        Object proceed = null;
        try {
            System.out.println("前置通知");
            proceed = proceedingJoinPoint.proceed(args);
            System.out.println("返回通知");
        } catch (Exception e) {
            System.out.println("异常通知");
        } finally {
            System.out.println("后置通知");
        }
        return proceed;

    }*/
}

