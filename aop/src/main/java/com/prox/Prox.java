package com.prox;

import com.aop.Math;
import com.aop.Mathimp;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Prox {
    public static Math getprox(final Math mathimp){
        ClassLoader classLoader =mathimp.getClass().getClassLoader() ;
        Class<?>[] classes = mathimp.getClass().getInterfaces();
        InvocationHandler invocationHandler =new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("我开始掉用"+method.getName()+"方法了，参数是"+ Arrays.asList(args));
                Object invoke = method.invoke(mathimp, args);
                System.out.println("调用完成，结果是"+invoke);
                return invoke;
            }
        } ;
        Object o = Proxy.newProxyInstance(classLoader, classes, invocationHandler );
        return (Math) o;
    }
}
