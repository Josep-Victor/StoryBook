package com.josepvictorr.storybookapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.textfield.TextInputEditText;
import com.josepvictorr.storybookapp.util.apihelper.BaseApiService;
import com.josepvictorr.storybookapp.util.apihelper.UtilsApi;
import com.josepvictorr.storybookapp.util.sharedpref.SharedPrefManager;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity{
    Context mContext;
    BaseApiService mApiService;
    TextInputEditText usernameLogin, passwordLogin;
    ProgressDialog loading;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        sharedPrefManager = new SharedPrefManager(this);
        if (sharedPrefManager.getSP_LoginCheck()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
        TextView daftarSekarang = findViewById(R.id.daftar_sekarang);
        daftarSekarang.setOnClickListener(v-> {
            Intent moveRegisterIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(moveRegisterIntent);
        });
        mContext = this;
        mApiService = UtilsApi.getApiService();
        initComponents();
    }

    private void initComponents(){
        usernameLogin = findViewById(R.id.username_login);
        passwordLogin = findViewById(R.id.pwd_login);

        AppCompatButton LoginBtn = findViewById(R.id.btn_masuk_login);
        LoginBtn.setOnClickListener(v->{
            loading = ProgressDialog.show(mContext, null, "Harap tunggu...", true, false);
            requestLogin();
        });
    }

    private void requestLogin(){
        mApiService.loginRequest(usernameLogin.getText().toString(), passwordLogin.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            Toast.makeText(mContext, "Login berhasil", Toast.LENGTH_SHORT).show();
                            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_LoginCheck, true);
                            startActivity(new Intent(LoginActivity.this, MainActivity.class)
                                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK)
                                            .putExtra("usernameHome", usernameLogin.getText().toString()));
                            finish();
                        } else {
                            Toast.makeText(mContext, "Login gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onBackPressed()
    {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}