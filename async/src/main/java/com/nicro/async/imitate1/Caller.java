package com.nicro.async.imitate1;

/**
 * Created by rongwenzhao on 2018/3/31.
 * 打电话的人
 */

public class Caller<T> {

    final OnCall<T> onCall;

    public Caller(OnCall<T> onCall) {
        this.onCall = onCall;
    }

    public static <T> Caller<T> create(OnCall<T> onCall) {
        return new Caller<>(onCall);
    }

    public Calling call(Receiver<T> receiver) {
        this.onCall.call(receiver);
        return receiver;
    }

    /**
     * 1、当打电话时会调用此接口；
     * 2、作用于Caller，向接电话的人发送通话内容。
     *
     * @param <T>
     */
    public interface OnCall<T> extends Action1<Receiver<T>> {

    }

    /**
     * 1、Operator接口是操作符的抽象接口；
     * 2、各操作符实现Operator接口，用于处理具体的变换
     *
     * @param <R>
     * @param <T>
     */
    public interface Operator<R, T> extends Func1<Receiver<R>, Receiver<T>> {

    }

    public <R> Caller<R> lift(final Operator<R, T> operator) {
        return create(new OnCallLift<T, R>(onCall, operator));
    }

    public final <R> Caller<R> map(Func1<T, R> func) {
        return lift(new MapOperator<T, R>(func));
    }


}
