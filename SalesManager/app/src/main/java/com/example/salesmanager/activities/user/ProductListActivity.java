package com.example.salesmanager.activities.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.salesmanager.R;
import com.example.salesmanager.adapters.ProductAdapter;
import com.example.salesmanager.dbhandler.CategoryHandler;
import com.example.salesmanager.dbhandler.ProductHandler;
import com.example.salesmanager.models.Product;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {
    Button btnCreateProduct;
    ArrayList<Product> productArrayList = new ArrayList<Product>();
    ProductAdapter productAdapter;
    Cursor cursor;
    ProductHandler productHandler;
    CategoryHandler categoryHandler;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv = (ListView) findViewById(R.id.lv_Product);
        btnCreateProduct = (Button) findViewById(R.id.btnCreateProduct);
        productHandler = new ProductHandler(this);
        categoryHandler = new CategoryHandler(this);
        display();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ProductListActivity.this, ProductEditActivity.class);
                intent.putExtra("Edit", productAdapter.getItem(position));
                startActivity(intent);
            }
        });

        btnCreateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductCreateActivity.class);
                startActivity(intent);
            }
        });

    }

    public void display() {
        cursor = productHandler.getAllProduct();
        while (cursor.moveToNext()){
            productArrayList.add(new Product(cursor.getInt(0), categoryHandler.getCategoryNameById(cursor.getInt(1)), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getBlob(5)));
        }
        productAdapter = new ProductAdapter(this, R.layout.item_product, productArrayList);
        lv.setAdapter(productAdapter);
    }
}