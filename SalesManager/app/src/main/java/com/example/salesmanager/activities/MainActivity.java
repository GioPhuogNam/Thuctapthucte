package com.example.salesmanager.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salesmanager.R;
import com.example.salesmanager.activities.login.LoginActivity;

public class MainActivity extends AppCompatActivity {
    public static int user_id = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch(Exception e) {
                } finally {
                    Intent i = new Intent(getApplicationContext(),
                            LoginActivity.class);
                    startActivity(i);
                }
            }
        };
        thread.start();
    }

    protected void onPause() {
        super.onPause();
        finish();
    }

}