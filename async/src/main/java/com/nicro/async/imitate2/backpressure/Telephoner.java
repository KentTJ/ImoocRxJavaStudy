package com.nicro.async.imitate2.backpressure;

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
}
