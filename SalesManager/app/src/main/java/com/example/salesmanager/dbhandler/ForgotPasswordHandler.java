package com.example.salesmanager.dbhandler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.salesmanager.data.DBManager;
import com.example.salesmanager.email.SendEmailTask;

public class ForgotPasswordHandler extends SQLiteOpenHelper {

    SQLiteDatabase db;
    private static final String DATABASE_NAME = "QUANLYDONHANG";
    private static final int DATABASE_VERSION = 1;
    DBManager dbManager;
    private Context context;

    public ForgotPasswordHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        dbManager = new DBManager(context);
        db = dbManager.getWritableDatabase();
    }

    public boolean checkEmail(String email) {
        // Kiểm tra tài khoản trong bảng user
        String[] columns = { "email" };
        String selection = "email" + " = ?";
        String[] selectionArgs = { email };
        Cursor cursor = db.query("user", columns, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        return false;
    }
    public void sendEmail(String email, String code) {
        new SendEmailTask(email, code).execute();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
