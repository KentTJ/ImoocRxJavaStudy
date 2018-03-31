package com.nicro.imoocrxjavastudy.chapter3.lesson5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.nicro.async.imitate1.Caller;
import com.nicro.async.imitate1.Calling;
import com.nicro.async.imitate1.Receiver;
import com.nicro.imoocrxjavastudy.R;

/**
 * Created by rongwenzhao on 2018/3/31.
 * 仿RxJava的async库的使用，类比RxJava效果
 */

public class Lesson3_5Activity extends AppCompatActivity {

    private String TAG = "Lesson3_5Activity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson3_5);
        findViewById(R.id.async).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calling calling = Caller.create(new Caller.OnCall<String>() {
                    @Override
                    public void call(Receiver<String> stringReceiver) {
                        if (!stringReceiver.isUnCalled()) {
                            stringReceiver.onReceiver("test");
                            stringReceiver.onCompleted();
                        }
                    }
                }).call(new Receiver<String>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onReceiver(String s) {
                        Log.d(TAG, "onReceiver " + s);
                    }
                });
            }
        });
    }
}
