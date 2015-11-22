package com.beanielab.template;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        startNextActivity();
                    }
                },
                1000);
    }

    private void startNextActivity() {
        Intent myIntent = new Intent(MainActivity.this, MainFragmentActivity.class);
        startActivity(myIntent);

    }
}
