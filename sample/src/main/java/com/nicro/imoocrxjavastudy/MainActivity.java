package com.nicro.imoocrxjavastudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nicro.imoocrxjavastudy.chapter3.lesson1.Lesson3_1Activity;
import com.nicro.imoocrxjavastudy.chapter3.lesson5.Lesson3_5Activity;
import com.nicro.imoocrxjavastudy.chapter3.lesson5.Lesson3_6_ImidateRx2Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void demo_rx1_rx2(View view) {
        startActivity(new Intent(this, Lesson3_1Activity.class));
    }

    public void rx1_imitate(View view) {
        startActivity(new Intent(this, Lesson3_5Activity.class));
    }

    public void rx2_imitate(View view) {
        startActivity(new Intent(this, Lesson3_6_ImidateRx2Activity.class));
    }
}
