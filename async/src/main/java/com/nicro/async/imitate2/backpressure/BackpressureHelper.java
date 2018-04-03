package com.nicro.async.imitate2.backpressure;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by rongwenzhao on 2018/4/3.
 * 处理背压的帮助类
 */

public final class BackpressureHelper {

    public static void add(AtomicLong requested, long n) {
        long r = requested.get();
        if (r == Long.MAX_VALUE) {
            return;
        }
        long u = r + n;
        if (u < 0) {
            u = Long.MAX_VALUE;
        }
        requested.compareAndSet(r, u);
    }

    public static void produced(AtomicLong requested, long n) {
        long current = requested.get();
        if (current == Long.MAX_VALUE) {
            return;
        }
        long update = current - n;
        if (update < 0L) {
            update = 0;//并提示出错
        }
        requested.compareAndSet(current, update);
    }
}
