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
import com.example.salesmanager.dbhandler.UserHandler;

public class UpdatePasswordActivity extends AppCompatActivity {
    Button btnVerify;
    EditText etNewPassword;
    EditText etNewPasswordConfirm;
    UserHandler userHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        userHandler = new UserHandler(this);
        btnVerify = (Button) findViewById(R.id.btnVerify);
        etNewPassword = (EditText) findViewById(R.id.etNewPassword);
        etNewPasswordConfirm = (EditText) findViewById(R.id.etNewPasswordConfirm);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = etNewPassword.getText().toString().trim();
                String passwordConfirm = etNewPasswordConfirm.getText().toString().trim();

                if (!password.equals(passwordConfirm)) {
                    Toast.makeText(getApplicationContext(),
                            "Mật khẩu không trùng khớp",
                            Toast.LENGTH_LONG).show();
                } else if (userHandler.updatePassword(email,password)) {
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),
                            "Cập nhật mật khẩu thành công",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}