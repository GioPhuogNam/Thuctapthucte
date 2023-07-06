package com.example.salesmanager.activities.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.salesmanager.R;
import com.example.salesmanager.dbhandler.ForgotPasswordHandler;

import java.util.Random;

public class ForgetPasswordActivity extends AppCompatActivity {
    Button sendEmail;
    EditText etEmail;
    ForgotPasswordHandler forgotPasswordHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        forgotPasswordHandler = new ForgotPasswordHandler(this);
        sendEmail = (Button) findViewById(R.id.btnSendEmail);
        etEmail = (EditText) findViewById(R.id.etEmail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                if (!forgotPasswordHandler.checkEmail(email)) {
                    Toast.makeText(getApplicationContext(),
                            "Tài khoản không tồn tại.",
                            Toast.LENGTH_LONG).show();
                    etEmail.setText("");
                } else {
                    Random random = new Random();
                    String code = String.valueOf(random.nextInt(900000) + 100000);

                    forgotPasswordHandler.sendEmail(email, code);
                    Intent i = new Intent(getApplicationContext(), VerifyActivity.class);
                    i.putExtra("code", code);
                    i.putExtra("email", email);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),
                            "Đã gửi email xác nhận",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}