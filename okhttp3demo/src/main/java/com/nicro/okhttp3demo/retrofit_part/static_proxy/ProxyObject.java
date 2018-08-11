package com.nicro.okhttp3demo.retrofit_part.static_proxy;

/**
 * Created by rongwenzhao on 2018/8/11.
 */

public class ProxyObject extends AbstractObject {

    //对目标类的引用
    private RealObject realObject;

    public ProxyObject(RealObject realObject) {
        this.realObject = realObject;
    }

    @Override
    protected void operation() {
        System.out.println("do something before real operation...");
        if (realObject == null) {
            realObject = new RealObject();
        }
        realObject.operation();
        System.out.println("do something after real operation ...");

    }
}
