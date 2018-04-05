package com.nicro.async.imitate2;

/**
 * Created by rongwenzhao on 2018/4/6.
 * lift操作符
 */

public class CallerLift<R, T> extends CallerWithUpstream<T, R> {

    private final CallerOperator<R, T> mOperator;

    public CallerLift(Caller<T> source, CallerOperator<R, T> mOperator) {
        super(source);
        this.mOperator = mOperator;
    }

    @Override
    protected void callActual(Callee callee) {
        Callee<T> tCallee = mOperator.call(callee);
        source.call(tCallee);
    }
}
