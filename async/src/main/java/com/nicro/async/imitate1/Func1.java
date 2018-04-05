package com.nicro.async.imitate1;

/**
 * Created by rongwenzhao on 2018/4/5.
 * function调用。T表示传入类型，R表示返回类型。
 */

public interface Func1<T, R> {

    R call(T t);
}
