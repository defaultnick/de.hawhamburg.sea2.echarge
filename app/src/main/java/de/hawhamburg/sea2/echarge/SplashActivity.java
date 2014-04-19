package de.hawhamburg.sea2.echarge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

    // Splash screen timer
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        new Handler().postDelayed(new Runnable() {
            public void run() {

                Intent intent = new Intent();
                intent.setClass(SplashActivity.this, LoginActivity.class);

                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
            }
        }, 3000);

        setContentView(R.layout.activity_splash);


    }

}
