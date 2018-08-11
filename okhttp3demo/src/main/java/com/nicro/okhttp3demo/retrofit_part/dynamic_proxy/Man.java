package com.nicro.okhttp3demo.retrofit_part.dynamic_proxy;

/**
 * 被代理类
 * Created by rongwenzhao on 2018/8/11.
 */

public class Man implements Subject {
    @Override
    public void shopping() {
        System.out.println("AAA" + "要去买东西");
    }
}
