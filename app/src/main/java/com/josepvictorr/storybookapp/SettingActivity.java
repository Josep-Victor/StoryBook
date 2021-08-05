package com.josepvictorr.storybookapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.josepvictorr.storybookapp.util.sharedpref.SharedPrefManager;

public class SettingActivity extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        getSupportActionBar().hide();

        sharedPrefManager = new SharedPrefManager(this);
        ImageButton btnSettingToHome = findViewById(R.id.setting_to_home);
        btnSettingToHome.setOnClickListener(view -> {
            Intent btnSettingToHomeIntent = new Intent(SettingActivity.this, MainActivity.class);
            btnSettingToHomeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(btnSettingToHomeIntent);
            finish();
        });

        BottomNavigationView btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Logout berhasil", Toast.LENGTH_SHORT).show();
            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_LoginCheck, false);
            startActivity(new Intent(SettingActivity.this, LoginActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        });

        TextView btnEditName = findViewById(R.id.btn_edit_name);
        btnEditName.setOnClickListener(view -> {
            Intent btnEditNameIntent = new Intent(SettingActivity.this, EditNameActivity.class);
            startActivity(btnEditNameIntent);
        });
    }

    private void logout(){
        startActivity(new Intent(SettingActivity.this, LoginActivity.class));
    }
}