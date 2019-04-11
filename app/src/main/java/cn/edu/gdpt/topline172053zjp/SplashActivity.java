package cn.edu.gdpt.topline172053zjp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Timer timer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
              Intent intent=new Intent(getApplicationContext(),MainActivity.class);
              startActivity(intent);
              finish();
            }
        };
        timer.schedule(task,3000);
    }


}
