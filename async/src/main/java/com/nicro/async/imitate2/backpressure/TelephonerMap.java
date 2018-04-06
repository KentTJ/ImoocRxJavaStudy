package com.nicro.async.imitate2.backpressure;

import com.nicro.async.imitate2.Function;

/**
 * Created by rongwenzhao on 2018/4/6.
 * map操作符
 */

public class TelephonerMap<T, R> extends TelephonerWithUpstream<T, R> {
    private Function<T, R> mFunction;

    public TelephonerMap(Telephoner<T> source, Function<T, R> mFunction) {
        super(source);
        this.mFunction = mFunction;
    }

    @Override
    protected void callActual(Receiver receiver) {
        source.callActual(new MapReceiver<>(receiver, mFunction));
    }

    static class MapReceiver<T, R> implements Receiver<T> {
        private final Receiver<R> mReceiver;
        private final Function<T, R> mFunction;

        public MapReceiver(Receiver<R> mReceiver, Function<T, R> mFunction) {
            this.mReceiver = mReceiver;
            this.mFunction = mFunction;
        }

        @Override
        public void onCall(Drop d) {
            mReceiver.onCall(d);
        }

        @Override
        public void onReceiver(T t) {
            R tR = mFunction.call(t);
            mReceiver.onReceiver(tR);
        }

        @Override
        public void onError(Throwable t) {
            mReceiver.onError(t);
        }

        @Override
        public void onCompleted() {
            mReceiver.onCompleted();
        }
    }
}
