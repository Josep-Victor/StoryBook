package com.josepvictorr.storybookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.josepvictorr.storybookapp.adapter.StoryAdapter;
import com.josepvictorr.storybookapp.item.SemuaStoryItem;
import com.josepvictorr.storybookapp.model.ResponseStory;
import com.josepvictorr.storybookapp.util.apihelper.BaseApiService;
import com.josepvictorr.storybookapp.util.apihelper.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tvUsernameHome;
    private String usernameHome;
    RecyclerView rvStory;
    ProgressDialog loading;
    Context mContext;
    StoryAdapter storyAdapter;
    BaseApiService mApiService;
    List<SemuaStoryItem> semuaStoryItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        tvUsernameHome = findViewById(R.id.username_home);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            usernameHome = extras.getString("usernameHome");
            tvUsernameHome.setText(usernameHome);
        }
        
        ImageButton settingBtn = findViewById(R.id.setting);
        settingBtn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, SettingActivity.class));
        });

        ImageButton notifBtn = findViewById(R.id.notif);
        notifBtn.setOnClickListener(view -> {
            Intent notifIntent = new Intent(MainActivity.this, NotificationActivity.class);
            startActivity(notifIntent);
        });

        Button addStory = findViewById(R.id.btn_new_story);
        addStory.setOnClickListener(view -> {
            Intent addStoryIntent = new Intent(MainActivity.this, AddStoryActivity.class);
            startActivity(addStoryIntent);
        });

        rvStory = findViewById(R.id.rvStory);
        mContext = this;
        mApiService = UtilsApi.getApiService();

        storyAdapter = new StoryAdapter(this, semuaStoryItemList);
        RecyclerView.LayoutManager mLayoutManger = new LinearLayoutManager(this);
        rvStory.setLayoutManager(mLayoutManger);
        rvStory.setItemAnimator(new DefaultItemAnimator());

        getResultListStory();
    }

    private void getResultListStory() {
        loading = ProgressDialog.show(this, null, "Harap tunggu...", true, false);
        mApiService.getSemuaStory().enqueue(new Callback<ResponseStory>() {
            @Override
            public void onResponse(Call<ResponseStory> call, Response<ResponseStory> response) {
                loading.dismiss();
                final List<SemuaStoryItem> semuaStoryItems = response.body().getSemuastory();

                rvStory.setAdapter(new StoryAdapter(mContext, semuaStoryItems));
                storyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseStory> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(mContext, "Terjadi Kesalaha", Toast.LENGTH_SHORT).show();
            }
        });
    }
}