package com.nicro.okhttp3demo.retrofit_part.static_proxy;

/**
 * Created by rongwenzhao on 2018/8/11.
 */

public class RealObject extends AbstractObject {
    @Override
    protected void operation() {
        System.out.println("do operation ...");
    }
}
