package com.nicro.async.imitate1;

import android.os.Handler;
import android.os.Message;

/**
 * Created by rongwenzhao on 2018/4/14.
 * 用于Android的 Worker
 */

public class HandlerWorker extends Switcher.Worker {
    private final Handler mHandler;
    private volatile boolean mIsUncalled;

    public HandlerWorker(Handler mHandler) {
        this.mHandler = mHandler;
    }

    @Override
    public void unCall() {
        mIsUncalled = true;
    }

    @Override
    public boolean isUnCalled() {
        return mIsUncalled;
    }

    @Override
    public Calling switches(Action0 action0) {
        SwitcherAction tSwitcherAction = new SwitcherAction(action0, mHandler);
        Message tMessage = Message.obtain(mHandler, tSwitcherAction);
        tMessage.obj = this;
        mHandler.sendMessage(tMessage);
        return tSwitcherAction;
    }

    private static class SwitcherAction implements Runnable, Calling {
        private final Action0 mAction;
        private final Handler mHandler;
        private volatile boolean mIsUnCalled;

        /**
         * 此处用private，则该类只能在该类中使用。
         *
         * @param mAction
         * @param mHandler
         */
        private SwitcherAction(Action0 mAction, Handler mHandler) {
            this.mAction = mAction;
            this.mHandler = mHandler;
        }

        @Override
        public void run() {
            mAction.call();
        }

        @Override
        public void unCall() {
            mIsUnCalled = true;
            mHandler.removeCallbacks(this);
        }

        @Override
        public boolean isUnCalled() {
            return mIsUnCalled;
        }
    }
}
