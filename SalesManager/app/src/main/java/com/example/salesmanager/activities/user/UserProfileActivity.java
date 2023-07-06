package com.example.salesmanager.activities.user;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.salesmanager.R;
import com.example.salesmanager.activities.MainActivity;
import com.example.salesmanager.dbhandler.LoginHandler;
import com.example.salesmanager.dbhandler.UserHandler;
import com.example.salesmanager.models.Product;
import com.example.salesmanager.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserProfileActivity extends AppCompatActivity {

    TextView username, email, fullname;
    Button btn_logout;
    UserHandler UserHandler;
    Cursor cursor;
    LoginHandler loginHandler;
    String userName;
    List<User> userList = new ArrayList<User>();
    SQLiteDatabase database;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        fullname = findViewById(R.id.fullname);
        btn_logout = findViewById(R.id.btn_logout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        UserHandler = new UserHandler(this);
        loginHandler = new LoginHandler(this);



        database = openOrCreateDatabase("QUANLYDONHANG", MODE_PRIVATE, null);
        String[] selectionArgs = {String.valueOf(MainActivity.user_id)};
        // Retrieve user data from the database
        Cursor cursor = database.rawQuery("SELECT * FROM user WHERE id = ?",selectionArgs );
        if (cursor.moveToFirst()) {
            int usernameIndex = cursor.getColumnIndex("username");
            int emailIndex = cursor.getColumnIndex("email");
            int fullnameIndex = cursor.getColumnIndex("name");
            String emailRt = cursor.getString(emailIndex);
            String fullnameRt = cursor.getString(fullnameIndex);
            String usernameRt = cursor.getString(usernameIndex);
            username.setText(usernameRt);
            email.setText(emailRt);
            fullname.setText(fullnameRt);
        }
        cursor.close();
        database.close();



        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder dialog = new AlertDialog.Builder(UserProfileActivity.this);
                dialog.setTitle("ĐĂNG XUẤT");
                dialog.setMessage("Bạn thật sự muốn đăng xuất khỏi ứng dụng ?");
                dialog.setPositiveButton("Đăng xuất", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("Hủy", null).show();
            }
        });
    }
}