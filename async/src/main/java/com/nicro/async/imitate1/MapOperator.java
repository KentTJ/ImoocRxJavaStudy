package com.nicro.async.imitate1;

/**
 * Created by rongwenzhao on 2018/4/5.
 * map操作符
 */

public class MapOperator<T, R> implements Caller.Operator<R, T> {
    private final Func1<T, R> mapper;

    public MapOperator(Func1<T, R> mapper) {
        this.mapper = mapper;
    }

    @Override
    public Receiver<T> call(Receiver<R> rReceiver) {
        return new MapReceiver<T, R>(rReceiver, this.mapper);
    }
}
