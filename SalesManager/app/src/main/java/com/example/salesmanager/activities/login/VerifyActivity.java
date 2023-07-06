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

public class VerifyActivity extends AppCompatActivity {
    Button btnVerify;
    EditText etCode;
    ForgotPasswordHandler forgotPasswordHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        forgotPasswordHandler = new ForgotPasswordHandler(this);
        btnVerify = (Button) findViewById(R.id.btnVerify);
        etCode = (EditText) findViewById(R.id.etCode);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String code = intent.getStringExtra("code");
        String email = intent.getStringExtra("email");

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtCode = etCode.getText().toString().trim();
                if (txtCode.equals(code)) {
                    Intent i = new Intent(getApplicationContext(), UpdatePasswordActivity.class);
                    i.putExtra("email", email);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Mã xác nhận sai, vui lòng nhập lại",
                            Toast.LENGTH_LONG).show();
                    etCode.setText("");
                }
            }
        });
    }
}