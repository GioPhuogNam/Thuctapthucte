package com.example.salesmanager.activities.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.salesmanager.R;
import com.example.salesmanager.adapters.SellingAdapter;
import com.example.salesmanager.dbhandler.CategoryHandler;
import com.example.salesmanager.models.Category;

import java.util.ArrayList;
import java.util.List;

public class SellingActivity extends AppCompatActivity {
    ListView lv;
    Button btnGioHang, btnHoaDon;
    List<Category> categoryList;
    SellingAdapter sellingAdapter;
    CategoryHandler categoryHandler;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selling);

        lv = (ListView) findViewById(R.id.lv_selling_category);
        btnGioHang = (Button) findViewById(R.id.btnGioHang);
        btnHoaDon = (Button) findViewById(R.id.btnHoaDon);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        categoryList = new ArrayList<>();
        display();

        btnGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShoppingCartActivity.class);
                startActivity(intent);
            }
        });

        btnHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BillStatisticUserActivity.class);
                startActivity(intent);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), ProductListSellingActivity.class);
                Category category = (Category) sellingAdapter.getItem(position);
                intent.putExtra("category_id", category.getIdCategory());
                intent.putExtra("category_name", category.getNameCategory());
                startActivity(intent);
            }
        });

    }
    public void display() {
        categoryHandler = new CategoryHandler(this);
        categoryList = categoryHandler.getAllCategoriesWithIdCategory();
        sellingAdapter = new SellingAdapter(this, categoryList, R.layout.item_category);
        lv.setAdapter(sellingAdapter);
    }
}