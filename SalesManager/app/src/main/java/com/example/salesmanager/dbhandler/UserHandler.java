package com.example.salesmanager.dbhandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.salesmanager.activities.MainActivity;
import com.example.salesmanager.data.DBManager;
import com.example.salesmanager.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserHandler extends SQLiteOpenHelper {

    DBManager dbManager;
    SQLiteDatabase db;
    private Context context;
    private static final String DATABASE_NAME = "QUANLYDONHANG";
    private static final int DATABASE_VERSION = 1;

    // Tên bảng
    private static final String TABLE_USER = "user";
    private static final String TABLE_BILLS = "bills";
    // Các trường trong bảng Users
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_USER_NAME = "name";
    private static final String COLUMN_USER_EMAIL = "email";
    private static final String COLUMN_USER_USERNAME = "username";
    private static final String COLUMN_USER_PASSWORD = "password";
    private static final String COLUMN_USER_ADDRESS = "address";
    private static final String COLUMN_USER_PHONE = "phone";
    // Các trường trong bảng Bills
    private static final String COLUMN_BILL_ID = "id";
    private static final String COLUMN_BILL_USER_ID = "user_id";
    private static final String COLUMN_BILL_TOTAL_PRICE = "total_price";
    private static final String COLUMN_BILL_DESCRIPTION = "description";
    private static final String COLUMN_BILL_DATE_CREATED = "date_created";

    public UserHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        dbManager = new DBManager(context);
        db = dbManager.getWritableDatabase();
    }

    public String getUserNameById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT name FROM user WHERE id = ?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.rawQuery(selectQuery, selectionArgs);
        String userName = null;
        if (cursor.moveToFirst()) {
            userName = cursor.getString(0);
        }
        cursor.close();
        return userName;
    }
    public boolean updatePassword(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", password);
        int rowsAffected = db.update("user", values, "email = ?", new String[]{email});
        return rowsAffected > 0;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
