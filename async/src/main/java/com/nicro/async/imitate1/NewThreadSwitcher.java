package com.nicro.async.imitate1;

/**
 * Created by rongwenzhao on 2018/4/14.
 */

public class NewThreadSwitcher extends Switcher {
    @Override
    public Worker createWorker() {
        return new NewThreadWorker();
    }
}
