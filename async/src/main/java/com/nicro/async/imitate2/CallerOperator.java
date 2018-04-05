package com.nicro.async.imitate2;

/**
 * Created by rongwenzhao on 2018/4/6.
 * 操作符接口
 */

public interface CallerOperator<T, R> {
    Callee<R> call(Callee<T> callee);
}
