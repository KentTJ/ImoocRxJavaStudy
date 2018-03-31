package com.nicro.async.imitate2;

/**
 * Created by rongwenzhao on 2018/3/31.
 * 用于发射数据
 */

public interface Emitter<T> {
    void onReceive(T t);

    void onCompleted();

    void onError(Throwable t);
}
