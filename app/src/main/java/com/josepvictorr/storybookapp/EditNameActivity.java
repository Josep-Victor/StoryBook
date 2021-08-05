package com.josepvictorr.storybookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class EditNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);

        getSupportActionBar().hide();

        ImageButton btnEditToSetting = findViewById(R.id.edit_to_setting);
        btnEditToSetting.setOnClickListener(view -> {
            Intent btnEditToSettingIntent = new Intent(EditNameActivity.this, SettingActivity.class);
            startActivity(btnEditToSettingIntent);
        });
    }
}