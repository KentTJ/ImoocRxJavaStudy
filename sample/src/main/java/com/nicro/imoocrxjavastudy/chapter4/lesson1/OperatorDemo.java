package com.nicro.imoocrxjavastudy.chapter4.lesson1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.nicro.async.imitate2.Callee;
import com.nicro.async.imitate2.Caller;
import com.nicro.async.imitate2.CallerEmitter;
import com.nicro.async.imitate2.CallerOnCall;
import com.nicro.async.imitate2.Release;
import com.nicro.imoocrxjavastudy.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * Created by rongwenzhao on 2018/4/4.
 * RxJava1与RxJava2操作符Demo
 */

public class OperatorDemo extends AppCompatActivity {
    private String TAG = "OperatorDemo";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator_demo_lesson4_1);

    }

    public void demo_rxJava1(View view) {
        /*Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("1");
                    subscriber.onNext("2");
                    subscriber.onCompleted();
                }
            }
        }).map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return Integer.parseInt(s);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer s) {
                Log.d(TAG, "onNext " + s + " instanceOf " + s.getClass());
            }
        });*/

    }

    /**
     * 模拟实现RxJava1的操作符变换
     *
     * @param view
     */
    public void imitate_RxJava1_operator(View view) {
        /*Caller.
                create(new Caller.OnCall<String>() {
                    @Override
                    public void call(Receiver<String> stringReceiver) {
                        if (!stringReceiver.isUnCalled()) {
                            stringReceiver.onReceiver("1");
                            stringReceiver.onReceiver("2");
                            stringReceiver.onCompleted();
                        }
                    }
                }).
                map(new com.nicro.async.imitate1.Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return Integer.parseInt(s);
                    }
                }).
                call(new Receiver<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onReceiver(Integer integer) {
                        Log.d(TAG, "onReceiver " + integer);
                    }
                });*/

    }

    /**
     * 使用RxJava2 操作符变换demo 无背压版
     *
     * @param view
     */
    public void demo_rxJava2_no_backpressure(View view) {
        Observable.
                create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        if (!e.isDisposed()) {
                            e.onNext("1");
                            e.onNext("2");
                            e.onComplete();
                        }
                    }
                }).
                map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) throws Exception {
                        return Integer.parseInt(s);
                    }
                }).
                subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "onNext " + value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });

    }

    /**
     * 模拟RxJava2 无背压版 map操作符变换
     *
     * @param view
     */
    public void Imitate_rxJava2_no_backpressure(View view) {
        Caller.
                create(new CallerOnCall<String>() {
                    @Override
                    public void call(CallerEmitter<String> callerEmitter) {
                        callerEmitter.onReceive("1");
                        callerEmitter.onReceive("2");
                        callerEmitter.onCompleted();

                    }
                }).
                map(new com.nicro.async.imitate2.Function<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return Integer.parseInt(s);
                    }
                }).
                call(new Callee<Integer>() {
                    @Override
                    public void OnCall(Release release) {
                        Log.d(TAG, "OnCall");
                    }

                    @Override
                    public void OnReceive(Integer integer) {
                        Log.d(TAG, "OnReceive " + integer);
                    }

                    @Override
                    public void OnCompleted() {
                        Log.d(TAG, "OnCompleted");
                    }

                    @Override
                    public void OnError(Throwable t) {

                    }
                });

    }
}
