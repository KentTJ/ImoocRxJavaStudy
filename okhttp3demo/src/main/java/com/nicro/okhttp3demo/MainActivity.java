package com.nicro.okhttp3demo;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private static String cacheDirectory = Environment.getExternalStorageDirectory() + "/okttpcaches"; // 设置缓存文件路径
    private static final String URL_CONTENT = "https://www.baidu.com/";
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .cache(new Cache(new File(cacheDirectory), 24 * 1024 * 1024))
            .retryOnConnectionFailure(true)  //连接失败后进行重连
            .build();

    public void syncRequest() {
        final Request request = new Request.Builder()
                .url(URL_CONTENT)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doOkHttp(View view) {
        final Request request = new Request.Builder()
                .url(URL_CONTENT)
                .build();

        //异步方式请求网络
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //请求失败
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //请求成功,对response进行处理
                Log.d("rwz", "return ok and response = " + response);

            }
        });
    }
}
