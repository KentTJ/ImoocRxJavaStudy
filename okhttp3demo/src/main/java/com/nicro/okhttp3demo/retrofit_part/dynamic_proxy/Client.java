package com.nicro.okhttp3demo.retrofit_part.dynamic_proxy;

/**
 * Created by rongwenzhao on 2018/8/11.
 */

public class Client {
    public static void main(String[] args) {
        Subject man = new Man();
        Proxy p = new Proxy(man);
        //通过java.lang.reflect.Proxy.newProxyInstance(...)方法获取真实对象的代理对象
        Subject subject = (Subject) java.lang.reflect.Proxy.
                newProxyInstance(man.getClass().getClassLoader(),
                        man.getClass().getInterfaces(), p);
        //通过代理对象调用真实对象相关接口中实现的方法，这个时候就会跳转到这个代理对象所关联的handler的invoke()方法
        subject.shopping();
        //获取真实对象所对应的代理对象所对应的Class对象的名称，用字符串表示
        System.out.println(subject.getClass().getName());
    }
}
