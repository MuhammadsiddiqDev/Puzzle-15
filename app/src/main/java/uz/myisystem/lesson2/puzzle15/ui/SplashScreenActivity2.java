package uz.myisystem.lesson2.puzzle15.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import uz.myisystem.lesson2.puzzle15.R;

public class SplashScreenActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity2.this, StartActivity2.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }


}