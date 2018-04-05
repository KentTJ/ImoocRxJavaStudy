package com.nicro.async.imitate2;

/**
 * Created by rongwenzhao on 2018/4/6.
 * Caller用于操作符的类
 */

public abstract class CallerWithUpstream<T, R> extends Caller<R> implements CallerSource<T> {
    protected final Caller<T> source;

    protected CallerWithUpstream(Caller<T> source) {
        this.source = source;
    }

    @Override
    public Caller<T> source() {
        return source;
    }
}
