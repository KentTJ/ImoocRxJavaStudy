package com.nicro.async.imitate1;

/**
 * Created by rongwenzhao on 2018/4/5.
 * map操作符的接收者
 */

public class MapReceiver<T, R> extends Receiver<T> {
    private final Receiver<R> actual;
    private final Func1<T, R> mapper;

    public MapReceiver(Receiver<R> actual, Func1<T, R> mapper) {
        this.actual = actual;
        this.mapper = mapper;
    }

    @Override
    public void onCompleted() {
        this.actual.onCompleted();
    }

    @Override
    public void onError(Throwable t) {
        this.actual.onError(t);
    }

    @Override
    public void onReceiver(T t) {
        R tR = this.mapper.call(t);
        this.actual.onReceiver(tR);
    }
}
