package com.nicro.imoocrxjavastudy.chapter3.lesson1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.nicro.imoocrxjavastudy.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by rongwenzhao on 2018/3/27.
 */

public class Lesson3_1Activity extends AppCompatActivity {
    private String TAG = "Lesson3_1Activity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson3_1);
       /* findViewById(R.id.btnRxJava1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Subscription subscription = Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(" test");
                            subscriber.onCompleted();
                        }

                    }
                }).subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext" + s);
                    }
                });

            }
        });*/

        findViewById(R.id.btnRxJava2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //完全不处理背压问题的 Observable
                Observable.create(new ObservableOnSubscribe<String>() {
                    //Emitter 发射器
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        if (!e.isDisposed()) {
                            e.onNext("test");
                            e.onComplete();
                        }
                    }
                }).subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(String value) {
                        Log.d(TAG, "onNext " + value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onCompleted");
                    }
                });

                //新拆出来的Flowable，解决背压问题
                Flowable.create(new FlowableOnSubscribe<String>() {
                    @Override
                    public void subscribe(FlowableEmitter<String> e) throws Exception {
                        if (!e.isCancelled()) {
                            e.onNext("Flowable test");
                            e.onComplete();
                        }
                    }
                }, BackpressureStrategy.DROP).subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                        Log.d(TAG, "Flowable onSubscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "Flowable onNext " + s);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "Flowable onCompleted");
                    }
                });

            }//end onClick

        });//end rxjava2 button set onClickListener
    }
}
