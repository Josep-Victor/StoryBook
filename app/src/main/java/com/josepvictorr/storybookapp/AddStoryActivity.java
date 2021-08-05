package com.josepvictorr.storybookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class AddStoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story);

        getSupportActionBar().hide();

        ImageButton btnAddStoryToHome = findViewById(R.id.add_story_to_home);
        btnAddStoryToHome.setOnClickListener(view -> {
            Intent btnAddStoryToHomeIntent = new Intent(AddStoryActivity.this, MainActivity.class);
            btnAddStoryToHomeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(btnAddStoryToHomeIntent);
            finish();
        });
    }
}