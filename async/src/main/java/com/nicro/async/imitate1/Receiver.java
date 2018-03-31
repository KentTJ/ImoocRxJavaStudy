package com.nicro.async.imitate1;

/**
 * Created by rongwenzhao on 2018/3/31.
 * 接收信息的人
 */

public abstract class Receiver<T> implements Callee<T>, Calling {
    private volatile boolean unCalled;

    @Override
    public void unCall() {
        unCalled = true;
    }

    @Override
    public boolean isUnCalled() {
        return unCalled;
    }
}
