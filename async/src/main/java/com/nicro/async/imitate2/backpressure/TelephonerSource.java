package com.nicro.async.imitate2.backpressure;

/**
 * Created by rongwenzhao on 2018/4/6.
 * 返回源Telephoner
 */

public interface TelephonerSource<T> {

    Telephoner<T> source();
}
