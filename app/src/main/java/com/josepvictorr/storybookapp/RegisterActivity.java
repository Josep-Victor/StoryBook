package com.josepvictorr.storybookapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.textfield.TextInputEditText;
import com.josepvictorr.storybookapp.util.apihelper.BaseApiService;
import com.josepvictorr.storybookapp.util.apihelper.UtilsApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity{
    TextInputEditText etUsername, etPassword, etConfirmPassword;
    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        mContext = this;
        mApiService = UtilsApi.getApiService();
        initComponents();

        ImageButton backBtn = findViewById(R.id.back_login);
        backBtn.setOnClickListener(view -> {
            Intent backLogin = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(backLogin);
        });
    }

    private void initComponents(){
        etUsername = findViewById(R.id.username_register);
        etPassword = findViewById(R.id.pwd_register);
        etConfirmPassword = findViewById(R.id.confirm_pwd_register);

        AppCompatButton registerBtn = findViewById(R.id.btn_masuk_register);
        registerBtn.setOnClickListener(view -> {
            String password = etPassword.getText().toString();
            String confirmPassword = etConfirmPassword.getText().toString();
            if(!password.equals(confirmPassword)){
                Toast.makeText(getApplicationContext(), "Password tidak sesuai", Toast.LENGTH_SHORT).show();
            } else {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                requestRegister();
            }
        });
    }

    private void requestRegister(){
        mApiService.registerRequest(etUsername.getText().toString(), etPassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            Toast.makeText(mContext, "Register berhasil, silahkan login", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(mContext, LoginActivity.class));
                        } else {
                            Toast.makeText(mContext, "Register gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}