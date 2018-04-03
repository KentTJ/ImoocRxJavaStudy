package com.nicro.async.imitate2.backpressure;

/**
 * Created by rongwenzhao on 2018/4/3.
 * 丢弃
 */

public interface Drop {
    void request(long n);

    void drop();
}
