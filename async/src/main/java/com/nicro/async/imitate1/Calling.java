package com.nicro.async.imitate1;

/**
 * Created by rongwenzhao on 2018/3/31.
 * 描述打电话
 * 1、挂断电话；
 * 2、有没有被挂断。
 */

public interface Calling {
    void unCall();

    boolean isUnCalled();
}
