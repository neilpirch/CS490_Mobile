package com.neilpirch.firebasephotos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HelpOnWayActivity extends AppCompatActivity {

    public Button logOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_on_way);

        logOutButton = findViewById(R.id.logOutButton);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logout();

            }
        });
    }

    @Override
    public void onBackPressed() {
        logout();
    }

    private void logout(){
        FirebaseAuth.getInstance().signOut();

        startActivity(new Intent(HelpOnWayActivity.this, com.neilpirch.firebasephotos.WelcomeActivity.class));
    }
}
