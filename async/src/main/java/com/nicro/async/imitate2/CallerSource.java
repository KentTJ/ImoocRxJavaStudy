package com.nicro.async.imitate2;

/**
 * Created by rongwenzhao on 2018/4/6.
 * 返回源caller
 */

public interface CallerSource<T> {
    Caller<T> source();
}
