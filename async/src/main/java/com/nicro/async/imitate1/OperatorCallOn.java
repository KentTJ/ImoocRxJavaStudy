package com.nicro.async.imitate1;

/**
 * Created by rongwenzhao on 2018/4/14.
 * 用于CallOn的OnCall(类似于RxJava1中的SubscriberOn方法的线程变换)
 */

public class OperatorCallOn<T> implements Caller.OnCall<T> {
    private final Switcher mSwitcher;
    private final Caller<T> mCaller;

    public OperatorCallOn(Switcher mSwitcher, Caller<T> mCaller) {
        this.mSwitcher = mSwitcher;
        this.mCaller = mCaller;
    }

    @Override
    public void call(final Receiver<T> tReceiver) {
        /**
         * 此处，只是演示RxJava1中最核心的相关实现。
         * 在RxJava1的对应代码中，会有相关保护代码，保证运行的安全健壮性。
         * 比如，此处只是简单的tReceiver替换调用，而RxJava1中，还有其他操作，包括unSubscriber等。
         */
        Switcher.Worker tWorker = mSwitcher.createWorker();
        tWorker.switches(new Action0() {
            @Override
            public void call() {
                Receiver<T> tReceiver1 = new Receiver<T>() {
                    @Override
                    public void onCompleted() {
                        tReceiver.onCompleted();
                    }

                    @Override
                    public void onError(Throwable t) {
                        tReceiver.onError(t);
                    }

                    @Override
                    public void onReceiver(T t) {
                        tReceiver.onReceiver(t);
                    }
                };
                mCaller.call(tReceiver1);
            }
        });
    }
}
