package com.nicro.okhttp3demo.retrofit_part.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理类
 * Created by rongwenzhao on 2018/8/11.
 */

public class Proxy implements InvocationHandler {
    private Object target;//要代理的真实对象

    public Proxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy:" + proxy.getClass().getName());
        System.out.println("before ...");
        method.invoke(target, args);
        System.out.println("after ...");
        return null;
    }
}
