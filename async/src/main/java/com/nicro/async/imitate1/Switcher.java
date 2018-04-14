package com.nicro.async.imitate1;

/**
 * Created by rongwenzhao on 2018/4/14.
 * 用于线程切换
 */

public abstract class Switcher {

    public abstract Worker createWorker();

    /**
     * 1、真正执行线程变换的类；
     * 2、通过switches方法执行变换
     */
    public static abstract class Worker implements Calling {
        public abstract Calling switches(Action0 action0);
    }
}
