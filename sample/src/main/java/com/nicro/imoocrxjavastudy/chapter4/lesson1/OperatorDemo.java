package com.nicro.imoocrxjavastudy.chapter4.lesson1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.nicro.async.imitate1.Caller;
import com.nicro.async.imitate1.Receiver;
import com.nicro.imoocrxjavastudy.R;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func1;

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
        Observable.create(new Observable.OnSubscribe<String>() {
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
        });

    }

    /**
     * 模拟实现RxJava1的操作符变换
     *
     * @param view
     */
    public void imitate_RxJava1_operator(View view) {
        Caller.
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
                });

    }
}
