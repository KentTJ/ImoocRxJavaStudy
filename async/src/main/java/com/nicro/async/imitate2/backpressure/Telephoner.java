package com.nicro.async.imitate2.backpressure;

import com.nicro.async.imitate2.Function;

/**
 * Created by rongwenzhao on 2018/4/3.
 * 打电话的人
 */

public abstract class Telephoner<T> {

    public static <T> Telephoner<T> create(TelephonerOnCall<T> telephonerOnCall) {
        return new TelephonerCreate<>(telephonerOnCall);
    }

    public void call(Receiver<T> receiver) {
        callActual(receiver);
    }

    protected abstract void callActual(Receiver<T> receiver);

    public <R> Telephoner<R> map(Function<T, R> function) {
        return new TelephonerMap<>(this, function);
    }

    public <R> Telephoner<R> lift(TelephonerOperator<R, T> telephonerOperator) {
        return new TelephonerLift<>(this, telephonerOperator);
    }
}
