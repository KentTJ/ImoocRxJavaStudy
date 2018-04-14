package com.nicro.async.imitate1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by rongwenzhao on 2018/4/14.
 * 用于callbackOn(类比RxJava1中ObserverOn)。
 * 注：
 * 1、持有Switcher；
 * 2、call方法中返回用于callbackOn的Receiver。
 */

public class OperatorCallbackOn<T> implements Caller.Operator<T, T> {
    private final Switcher mSwitcher;

    public OperatorCallbackOn(Switcher switcher) {
        this.mSwitcher = switcher;
    }

    @Override
    public Receiver<T> call(Receiver<T> receiver) {
        return new CallbackOnReceiver<>(receiver, mSwitcher);
    }

    /**
     * 1、持有原Caller和Switcher；
     * 2、在onReceiver等方法中做调度；
     * 3、调度后原Receiver再调用onReceiver。
     *
     * @param <T>
     */
    private static final class CallbackOnReceiver<T> extends Receiver<T> implements Action0 {

        private final Receiver<T> mReceiver;

        private final Switcher.Worker mWorker;

        private final Queue<T> mQueue = new LinkedList<>();

        public CallbackOnReceiver(Receiver<T> receiver, Switcher switcher) {
            this.mReceiver = receiver;
            this.mWorker = switcher.createWorker();
        }

        @Override
        public void call() {
            T t = mQueue.poll();
            mReceiver.onReceiver(t);
        }

        @Override
        public void onCompleted() {
            //do not implement
        }

        @Override
        public void onError(Throwable t) {
            //do not implement
        }

        @Override
        public void onReceiver(T t) {
            mQueue.offer(t);
            switches();
        }

        private void switches() {
            mWorker.switches(this);
        }
    }
}
