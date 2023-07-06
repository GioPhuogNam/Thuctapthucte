package com.example.salesmanager.activities.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.salesmanager.R;
import com.example.salesmanager.dbhandler.RegistrationHandler;

public class RegistrationActivity extends AppCompatActivity {

    Button btnRegister;
    EditText etFullName;
    EditText etEmail;
    EditText etSdt;
    LinearLayout btnBackLogin;
    EditText etPassword;
    EditText etUsername;
    RegistrationHandler registerHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registerHandler = new RegistrationHandler(this);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etFullName = (EditText) findViewById(R.id.etFullName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etSdt = (EditText) findViewById(R.id.etSdt);
        btnBackLogin = (LinearLayout) findViewById(R.id.btnBackLogin);

        btnBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etFullName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String phone = etSdt.getText().toString().trim();

                if(registerHandler.register(name, email, username, password, phone)){
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),
                            "Đăng ký tài khoản thành công.",
                            Toast.LENGTH_LONG).show();
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),
                            "Đăng ký tài khoản không thành công.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}