package com.josepvictorr.storybookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        getSupportActionBar().hide();

        ImageButton btnNotifToHome = findViewById(R.id.notif_to_home);
        btnNotifToHome.setOnClickListener(view -> {
            Intent btnNotifToHomeIntent = new Intent (NotificationActivity.this, MainActivity.class);
            btnNotifToHomeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(btnNotifToHomeIntent);
            finish();
        });
    }
}