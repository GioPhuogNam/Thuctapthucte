package com.example.salesmanager.dbhandler;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.example.salesmanager.data.DBManager;

public class RegistrationHandler extends SQLiteOpenHelper {

    SQLiteDatabase db;

    private static final String DATABASE_NAME = "QUANLYDONHANG";
    private static final int DATABASE_VERSION = 1;

    DBManager dbManager;
    private Context context;

    public RegistrationHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        dbManager = new DBManager(context);
        db = dbManager.getWritableDatabase();
    }

    public boolean register(String name, String email, String username, String password, String phone){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Insert into user (name, email, username, password, phone) values (?, ?, ?, ?, ?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, name);
        statement.bindString(2, email);
        statement.bindString(3, username);
        statement.bindString(4, password);
        statement.bindString(5, phone);
        long rowId = statement.executeInsert();
        db.close();
        return (rowId != -1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
