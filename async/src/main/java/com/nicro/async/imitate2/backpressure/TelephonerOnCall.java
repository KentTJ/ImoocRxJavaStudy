package com.nicro.async.imitate2.backpressure;

/**
 * Created by rongwenzhao on 2018/4/3.
 * 当打电话的时候
 */

public interface TelephonerOnCall<T> {
    void call(TelephonerEmitter<T> telephonerEmitter);
}
