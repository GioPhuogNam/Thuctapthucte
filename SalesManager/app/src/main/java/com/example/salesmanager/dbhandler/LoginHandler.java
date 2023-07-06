package com.example.salesmanager.dbhandler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.salesmanager.data.DBManager;

public class LoginHandler extends SQLiteOpenHelper {

    SQLiteDatabase db;
    private static final String DATABASE_NAME = "QUANLYDONHANG";
    private static final int DATABASE_VERSION = 1;
    DBManager dbManager;
    private Context context;

    public LoginHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        dbManager = new DBManager(context);
        db = dbManager.getWritableDatabase();
    }

    public String checkLogin(String username, String password){
        SQLiteDatabase db =this.getReadableDatabase();

        // Kiểm tra tài khoản trong bảng USER
        String[] columns = { "username" };
        String selection = "username" + " = ?" + " AND " + "password" + " = ?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query("user", columns, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            cursor.close();
            return "user";
        }

        cursor.close();
        return "false";
    }

    public int getUserId(String username) {
        String query = "SELECT id FROM user WHERE username = ?";
        Cursor c = db.rawQuery(query, new String[] {username});
        c.moveToFirst();
        int user_id = -1;
        if (!c.isAfterLast()) {
            user_id = c.getInt(0);
        }
        c.close();
        return user_id;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
