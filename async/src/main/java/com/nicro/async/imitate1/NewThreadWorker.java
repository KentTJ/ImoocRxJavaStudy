package com.nicro.async.imitate1;

import android.support.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by rongwenzhao on 2018/4/14.
 * 1、有一个只有一个线程的线程池；
 * 2、实现切换线程的switches方法；
 * 3、将真正的操作用Runnable包裹丢入线程池执行。
 */

public class NewThreadWorker extends Switcher.Worker {

    private volatile boolean mIsUnCalled;

    private final ExecutorService mExecutor = Executors.newScheduledThreadPool(1, new ThreadFactory() {
        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            return new Thread(runnable, "Async");
        }
    });

    @Override
    public void unCall() {
        mIsUnCalled = true;
    }

    @Override
    public boolean isUnCalled() {
        return mIsUnCalled;
    }

    @Override
    public Calling switches(Action0 action0) {
        SwitcherAction tSwitcherAction = new SwitcherAction(action0);
        mExecutor.submit(tSwitcherAction);
        return tSwitcherAction;
    }

    /**
     * 将Action0包装成Runnable类型。
     */
    private static class SwitcherAction implements Runnable, Calling {

        private final Action0 mAction0;
        private volatile boolean mIsUnCalled;

        public SwitcherAction(Action0 mAction0) {
            this.mAction0 = mAction0;
        }

        @Override
        public void unCall() {
            mIsUnCalled = true;
        }

        @Override
        public boolean isUnCalled() {
            return mIsUnCalled;
        }

        @Override
        public void run() {
            mAction0.call();
        }
    }
}
