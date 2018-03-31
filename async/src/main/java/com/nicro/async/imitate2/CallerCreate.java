package com.nicro.async.imitate2;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by rongwenzhao on 2018/3/31.
 * 实际的Caller
 */

public final class CallerCreate<T> extends Caller<T> {

    CallerOnCall<T> mCallerOnCall;

    public CallerCreate(CallerOnCall<T> mCallerOnCall) {
        this.mCallerOnCall = mCallerOnCall;
    }

    @Override
    protected void callActual(Callee<T> callee) {
        CreateEmitter<T> tCreateEmitter = new CreateEmitter<>(callee);
        callee.OnCall(tCreateEmitter);
        mCallerOnCall.call(tCreateEmitter);
    }

    public static final class CreateEmitter<T> extends AtomicReference<Release> implements CallerEmitter<T>, Release {

        private Callee<T> mCallee;

        public CreateEmitter(Callee<T> callee) {
            this.mCallee = callee;
        }

        @Override
        public void onReceive(T t) {
            if (!isReleased()) {
                mCallee.OnReceive(t);
            }
        }

        @Override
        public void onCompleted() {
            if (!isReleased()) {
                mCallee.OnCompleted();
            }
        }

        @Override
        public void onError(Throwable t) {
            if (!isReleased()) {
                mCallee.OnError(t);
            }
        }

        @Override
        public boolean isReleased() {
            return ReleaseHelper.isRelease(get());
        }

        @Override
        public void release() {
            ReleaseHelper.release(this);
        }
    }
}
