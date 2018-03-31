package com.nicro.async.imitate2;

/**
 * Created by rongwenzhao on 2018/3/31.
 * 接电话的人
 */

public interface Callee<T> {
    void OnCall(Release release);

    void OnReceive(T t);

    void OnCompleted();

    void OnError(Throwable t);

}
