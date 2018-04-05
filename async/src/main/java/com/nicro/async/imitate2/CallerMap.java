package com.nicro.async.imitate2;

/**
 * Created by rongwenzhao on 2018/4/6.
 * map操作符
 */

public class CallerMap<T, R> extends CallerWithUpstream<T, R> {
    private Function<T, R> mFunction;

    public CallerMap(Caller<T> source, Function<T, R> mFunction) {
        super(source);
        this.mFunction = mFunction;
    }

    @Override
    protected void callActual(Callee<R> callee) {
        source.call(new MapCallee<T, R>(callee, mFunction));
    }

    static class MapCallee<T, R> implements Callee<T> {
        private final Callee<R> mCallee;
        private final Function<T, R> mFunction;

        public MapCallee(Callee<R> mCallee, Function<T, R> mFunction) {
            this.mCallee = mCallee;
            this.mFunction = mFunction;
        }

        @Override
        public void OnCall(Release release) {
            mCallee.OnCall(release);
        }

        @Override
        public void OnReceive(T t) {
            R tR = this.mFunction.call(t);
            mCallee.OnReceive(tR);
        }

        @Override
        public void OnCompleted() {
            mCallee.OnCompleted();
        }

        @Override
        public void OnError(Throwable t) {
            mCallee.OnError(t);
        }
    }
}
