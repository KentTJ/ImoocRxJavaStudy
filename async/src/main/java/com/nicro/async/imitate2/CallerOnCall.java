package com.nicro.async.imitate2;

/**
 * Created by rongwenzhao on 2018/3/31.
 * 当打电话的时候使用
 */

public interface CallerOnCall<T> {
    void call(CallerEmitter<T> callerEmitter);
}
