package my.com.mvptestapp.icarasia.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;

import my.com.mvptestapp.icarasia.R;
import my.com.mvptestapp.icarasia.loginSignUp.LoginSignUpActivity;

public class SplashScreenActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, SplashScreenActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        delay();
    }

    protected void delay() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
                LoginSignUpActivity.start(SplashScreenActivity.this);
            }
        }, 5000);
    }
}
