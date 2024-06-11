package com.example.musicapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.musicapp.Model.Baihat;
import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;
import com.example.musicapp.Service.Dataservice;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AccountActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText textPassword;
    private Button btnLogin;
    private TextView txtRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        init();

    }

    private void init() {
        txtEmail = findViewById(R.id.edtendangnhap);
        textPassword = findViewById(R.id.edmatkhau);
        btnLogin = findViewById(R.id.btnDangnhap);
        txtRegister = findViewById(R.id.txtdangki);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString().trim();
                String password = textPassword.getText().toString().trim();

                // Kiểm tra tính hợp lệ của dữ liệu
                if (email.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Gọi phương thức Login
                login(email, password);
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login(String email, String password) {
        Dataservice dataservice = APIService.getService();
        Call<String> callback = dataservice.login(email, password);

        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String result = response.body();
                switch (result) {
                    case "Success":
                        showToast("Đăng nhập thành công");
                        Intent intent = new Intent(AccountActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                        break;
                    case "Password":
                        showToast("Sai mật khẩu!");
                        break;
                    case "Email":
                        showToast("Email không tồn tại");
                        break;
                    case "Validate":
                        showToast("Vui lòng nhập email và mật khẩu");
                        break;
                    default:
                        showToast("Kết quả không xác định");
                        break;
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                showToast("Lỗi kết nối: " + t.getMessage());
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}