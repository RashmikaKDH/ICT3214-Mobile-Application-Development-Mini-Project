package com.example.ict3214_mobile_application_development_mini_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class activity_splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Splash to main
                Intent intent = new Intent(activity_splash.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        }, 2000);
    }
}