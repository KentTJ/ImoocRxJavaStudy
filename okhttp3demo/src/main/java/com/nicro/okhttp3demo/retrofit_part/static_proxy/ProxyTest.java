package com.nicro.okhttp3demo.retrofit_part.static_proxy;

/**
 * Created by rongwenzhao on 2018/8/11.
 */

public class ProxyTest {
    public static void main(String[] args) {
        ProxyObject proxyObject = new ProxyObject(new RealObject());
        proxyObject.operation();
    }
}
