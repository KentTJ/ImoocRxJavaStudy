package com.nicro.async.imitate2.backpressure;

/**
 * Created by rongwenzhao on 2018/4/6.
 * 操作符接口
 */

public interface TelephonerOperator<T, R> {
    
    Receiver<R> call(Receiver<T> callee);
}
