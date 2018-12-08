package com.csee5590.helloworldapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import com.facebook.appevents.AppEventsLogger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       FacebookSdk.sdkInitialize(getApplicationContext());
       AppEventsLogger.activateApp(this);

    }

    public void goToLogin(View v) {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
