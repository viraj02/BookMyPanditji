package com.example.virajjoshi.bookmypanditji;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent userreg = new Intent(SplashScreen.this,UserRegistration.class);
                startActivity(userreg);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
