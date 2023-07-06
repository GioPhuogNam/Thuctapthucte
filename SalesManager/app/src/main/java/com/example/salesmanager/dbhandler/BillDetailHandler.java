package com.example.salesmanager.dbhandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.salesmanager.data.DBManager;
import com.example.salesmanager.models.BillDetail;

public class BillDetailHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QUANLYDONHANG";
    private static final int DATABASE_VERSION = 1;
    private Context context;
    DBManager dbManager;

    public BillDetailHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        dbManager = new DBManager(context);
    }
    public int insertBillDetail(BillDetail billDetail) {
        ContentValues values = new ContentValues();
        values.put("bill_id", billDetail.getBill_id());
        values.put("product_name", billDetail.getProduct_name());
        values.put("quantity", billDetail.getQuantity());
        values.put("price", billDetail.getPrice());

        long result = dbManager.getWritableDatabase().insert("bill_detail", null, values);
        if (result <= 0) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
