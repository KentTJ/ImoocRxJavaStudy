package com.nicro.async.imitate2;

/**
 * Created by rongwenzhao on 2018/3/31.
 * 打电话的人
 */

public abstract class Caller<T> {

    public static <T> Caller<T> create(CallerOnCall<T> callerOnCall) {
        return new CallerCreate(callerOnCall);
    }

    public void call(Callee<T> callee) {
        callActual(callee);
    }

    protected abstract void callActual(Callee<T> callee);

    public <R> Caller<R> map(Function<T, R> function) {
        return new CallerMap<>(this, function);
    }

    public <R> Caller<R> lift(CallerOperator<R,T> operator){
        return new CallerLift<>(this,operator);
    }
}
