package com.nicro.imoocrxjavastudy.chapter5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.nicro.async.imitate1.Caller;
import com.nicro.async.imitate1.NewThreadSwitcher;
import com.nicro.async.imitate1.Receiver;
import com.nicro.imoocrxjavastudy.R;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by rongwenzhao on 2018/4/7.
 * 线程变换章节demo
 */

public class ThreadTransformDemoActivity extends AppCompatActivity {
    private String TAG = "ThreadTransformDemoActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson5_thread_transform);
    }

    /**
     * RxJava1线程变换使用实例
     *
     * @param view
     */
    public void threadTransform_demo_rx1(View view) {
        Observable.
                create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        if (!subscriber.isUnsubscribed()) {
                            Log.d(TAG, "currentThread : " + Thread.currentThread().getName());
                            subscriber.onNext("test");
                            subscriber.onCompleted();
                        }
                    }
                }).
                subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext " + s + "currentThread : " + Thread.currentThread().getName());
                    }
                });
    }

    /**
     * 模仿RxJava1的subscriberOn和OnserverOn的线程变换的demo
     *
     * @param view
     */
    public void imitate_subscriberOn_OnserverOn_rx1(View view) {

        Caller
                .create(new Caller.OnCall<String>() {
                    @Override
                    public void call(Receiver<String> stringReceiver) {
                        if (!stringReceiver.isUnCalled()) {
                            Log.d(TAG, "call currentThread : " + Thread.currentThread().getName());
                            stringReceiver.onReceiver("test");
                            stringReceiver.onCompleted();
                        }
                    }
                })
                .callOn(new NewThreadSwitcher())
                .call(new Receiver<String>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted currentThread : " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onReceiver(String s) {
                        Log.d(TAG, "onReceiver recever: " + s
                                + "currentThread : " + Thread.currentThread().getName());
                    }
                });
    }
}
