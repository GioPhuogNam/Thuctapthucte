package com.example.salesmanager.activities.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salesmanager.R;
import com.example.salesmanager.activities.MainActivity;
import com.example.salesmanager.activities.user.BusinessManagementActivity;
import com.example.salesmanager.dbhandler.LoginHandler;

public class LoginActivity extends AppCompatActivity {
    Button btnRegister;
    Button btnLogin;
    EditText etPassword;
    EditText etUsername;
    TextView forgotPassword;
    LoginHandler loginHandler;
    CheckBox remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginHandler = new LoginHandler(this);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        forgotPassword = (TextView) findViewById(R.id.forgot_password);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if (loginHandler.checkLogin(username, password) == "user") {
                    MainActivity.user_id = loginHandler.getUserId(username);
                    Intent i = new Intent(getApplicationContext(), BusinessManagementActivity.class);
//                    Intent i = new Intent(getApplicationContext(), UserProfileActivity.class);
                    i.putExtra("key_username", username);

                    startActivity(i);
                    Toast.makeText(getApplicationContext(),
                            "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Tài khoản hoặc mật khẩu không đúng, hãy kiểm tra lại.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}