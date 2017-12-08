package android.csulb.edu.cecs453finalproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {
    //splash screen time
    private static int SPLASH_TIME =3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent splashIntent = new Intent(Splash.this, MainActivity.class);
                startActivity(splashIntent);
                finish();
            }
        }, SPLASH_TIME);

    }
}
