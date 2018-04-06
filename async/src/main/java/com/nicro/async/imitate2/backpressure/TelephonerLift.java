package com.nicro.async.imitate2.backpressure;


/**
 * Created by rongwenzhao on 2018/4/6.
 * lift操作符
 */

public class TelephonerLift<R, T> extends TelephonerWithUpstream<T, R> {
    private final TelephonerOperator<R, T> mOperator;

    public TelephonerLift(Telephoner<T> source, TelephonerOperator<R, T> mOperator) {
        super(source);
        this.mOperator = mOperator;
    }

    @Override
    protected void callActual(Receiver<R> receiver) {
        Receiver<T> tReceiver = mOperator.call(receiver);
        source.call(tReceiver);
    }
}
