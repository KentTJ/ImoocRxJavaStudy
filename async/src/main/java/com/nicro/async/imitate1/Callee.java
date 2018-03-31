package com.nicro.async.imitate1;

/**
 * Created by rongwenzhao on 2018/3/31.
 * 接电话的人
 */

public interface Callee<T> {
    void onCompleted();

    void onError(Throwable t);

    void onReceiver(T t);
}
