package com.nicro.async.imitate2.backpressure;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by rongwenzhao on 2018/4/3.
 * 实际的Telephoner(实际打电话的人)
 */

public class TelephonerCreate<T> extends Telephoner<T> {

    TelephonerOnCall<T> mTelephonerOnCall;

    public TelephonerCreate(TelephonerOnCall<T> telephonerOnCall) {
        this.mTelephonerOnCall = telephonerOnCall;
    }

    @Override
    protected void callActual(Receiver<T> receiver) {
        DropEmitter<T> tDropEmitter = new DropEmitter<>(receiver);
        receiver.onCall(tDropEmitter);
        mTelephonerOnCall.call(tDropEmitter);
    }

    /**
     * 丢弃策略的Emitter.
     * 响应式拉取的实现方式：需要多少就request方法中就传递多少，
     * onReceive方法中发射一个之后，DropEmitter的AtomicLong中的存储值就会减掉一个来进行消费的标志。
     * (onReceive方法可以发送多个，直到get() == 0 ,即达到了你请求的个数).
     *
     * @param <T>
     */
    private static class DropEmitter<T>
            extends AtomicLong
            implements TelephonerEmitter<T>, Drop {

        private Receiver<T> mReceiver;
        private volatile boolean mIsDropped;

        private DropEmitter(Receiver<T> receiver) {
            this.mReceiver = receiver;
        }

        @Override
        public void onReceive(T t) {
            if (get() != 0) {
                mReceiver.onReceiver(t);
                BackpressureHelper.produced(this, 1);
            }
        }

        @Override
        public void onCompleted() {
            mReceiver.onCompleted();
        }

        @Override
        public void onError(Throwable t) {
            mReceiver.onError(t);
        }

        /**
         * 实现响应式拉取的方法。需要多少就request多少，onReceive中发射一个呢，就会减掉一个。
         *
         * @param n
         */
        @Override
        public void request(long n) {
            BackpressureHelper.add(this, n);
        }

        @Override
        public void drop() {
            mIsDropped = true;
        }
    }
}
