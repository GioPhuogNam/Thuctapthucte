package com.example.salesmanager.dbhandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.salesmanager.data.DBManager;
import com.example.salesmanager.models.ProductStatistic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StatisticHandler extends SQLiteOpenHelper {
    DBManager dbManager;
    SQLiteDatabase db;
    private Context context;
    // Tên cơ sở dữ liệu
    private static final String DATABASE_NAME = "QUANLYDONHANG";
    // Phiên bản cơ sở dữ liệu
    private static final int DATABASE_VERSION = 1;
    // Tên bảng
    private static final String TABLE_BILLS = "bills";
    private static final String TABLE_BILL_DETAIL = "bill_detail";
    // Các trường trong bảng Bills
    private static final String COLUMN_BILL_ID = "id";
    private static final String COLUMN_BILL_USER_ID = "user_id";
    private static final String COLUMN_BILL_TOTAL_PRICE = "total_price";
    private static final String COLUMN_BILL_DESCRIPTION = "description";
    private static final String COLUMN_BILL_DATE_CREATED = "date_created";
    // Các trường trong bảng Bill Detail
    private static final String COLUMN_BILL_DETAIL_ID = "id";
    private static final String COLUMN_BILL_DETAIL_BILL_ID = "bill_id";
    private static final String COLUMN_BILL_DETAIL_PRODUCT_NAME = "product_name";
    private static final String COLUMN_BILL_DETAIL_QUANTITY = "quantity";
    private static final String COLUMN_BILL_DETAIL_PRICE = "price";


    public StatisticHandler( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        dbManager = new DBManager(context);
        db = dbManager.getWritableDatabase();
    }
    public int getTotalBill(String start_date, String end_date) {
        int total = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT SUM(" + COLUMN_BILL_TOTAL_PRICE + ") FROM " + TABLE_BILLS + " WHERE " + COLUMN_BILL_DATE_CREATED + " BETWEEN ? AND ? ";
        Cursor cursor = db.rawQuery(query, new String[]{start_date, end_date});
        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return total;
    }

    public List<ProductStatistic> getProductStatistic() {
        List<ProductStatistic> productSales = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_BILL_DETAIL_PRODUCT_NAME + ", SUM(" + COLUMN_BILL_DETAIL_QUANTITY + "), SUM(" + COLUMN_BILL_DETAIL_PRICE + " * " + COLUMN_BILL_DETAIL_QUANTITY + ") " +
                "FROM " + TABLE_BILL_DETAIL +
                " GROUP BY " + COLUMN_BILL_DETAIL_PRODUCT_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String productName = cursor.getString(0);
                int quantity = cursor.getInt(1);
                double totalSales = cursor.getDouble(2);

                ProductStatistic productStatistic = new ProductStatistic(productName, quantity, totalSales);
                productSales.add(productStatistic);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return productSales;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
