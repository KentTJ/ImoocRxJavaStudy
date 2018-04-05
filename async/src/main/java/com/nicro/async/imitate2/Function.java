package com.nicro.async.imitate2;

/**
 * Created by rongwenzhao on 2018/4/6.
 * 操作方法，用于进行数据转换
 */

public interface Function<T, R> {
    R call(T t);
}
