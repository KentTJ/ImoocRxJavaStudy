package com.nicro.imoocrxjavastudy.chapter3.lesson5;

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
import com.nicro.async.imitate2.backpressure.Drop;
import com.nicro.async.imitate2.backpressure.Receiver;
import com.nicro.async.imitate2.backpressure.Telephoner;
import com.nicro.async.imitate2.backpressure.TelephonerEmitter;
import com.nicro.async.imitate2.backpressure.TelephonerOnCall;
import com.nicro.imoocrxjavastudy.R;

/**
 * Created by rongwenzhao on 2018/3/31.
 * 仿RxJava2使用实例
 */

public class Lesson3_6_ImidateRx2Activity extends AppCompatActivity {
    private String TAG = "Lesson3_6_ImidateRx2Activity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson3_6);
        //无背压版
        findViewById(R.id.async).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Caller.create(new CallerOnCall<String>() {
                    @Override
                    public void call(CallerEmitter<String> callerEmitter) {
                        callerEmitter.onReceive("test");
                        callerEmitter.onCompleted();
                    }
                }).call(new Callee<String>() {
                    @Override
                    public void OnCall(Release release) {
                        Log.d(TAG, "OnCall");
                    }

                    @Override
                    public void OnReceive(String s) {
                        Log.d(TAG, "OnReceive " + s);
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
        });
    }

    public void rx2_imitate_with_backpresure(View view) {
        Telephoner.create(new TelephonerOnCall<String>() {
            @Override
            public void call(TelephonerEmitter<String> telephonerEmitter) {
                telephonerEmitter.onReceive("test");
                telephonerEmitter.onCompleted();

            }
        }).call(new Receiver<String>() {
            @Override
            public void onCall(Drop d) {
                d.request(1);
                Log.d(TAG, "onCall");
            }

            @Override
            public void onReceiver(String s) {
                Log.d(TAG, "onReceiver " + s);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }
        });
    }
}
