package com.nicro.async.imitate2.backpressure;

/**
 * Created by rongwenzhao on 2018/4/3.
 * 接电话的人
 */

public interface Receiver<T> {

    void onCall(Drop d);

    void onReceiver(T t);

    void onError(Throwable t);

    void onCompleted();
}
