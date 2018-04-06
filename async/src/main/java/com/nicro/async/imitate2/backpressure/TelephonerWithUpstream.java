package com.nicro.async.imitate2.backpressure;

/**
 * Created by rongwenzhao on 2018/4/6.
 * 操作符变换都要实现的抽象类
 */

public abstract class TelephonerWithUpstream<T, R> extends Telephoner<R> implements TelephonerSource<T> {
    protected final Telephoner<T> source;

    protected TelephonerWithUpstream(Telephoner<T> source) {
        this.source = source;
    }

    @Override
    public Telephoner source() {
        return source;
    }
}
