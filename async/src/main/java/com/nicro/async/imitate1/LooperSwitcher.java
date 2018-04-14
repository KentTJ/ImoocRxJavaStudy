package com.nicro.async.imitate1;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by rongwenzhao on 2018/4/14.
 */

public class LooperSwitcher extends Switcher {
    private Handler mHandler;

    public LooperSwitcher(Looper looper) {
        this.mHandler = new Handler(looper);
    }

    @Override
    public Worker createWorker() {
        return new HandlerWorker(mHandler);
    }
}
