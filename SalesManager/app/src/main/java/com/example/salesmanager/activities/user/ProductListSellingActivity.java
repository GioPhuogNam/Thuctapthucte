package com.example.salesmanager.activities.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.salesmanager.R;
import com.example.salesmanager.adapters.ProductSellingAdapter;
import com.example.salesmanager.dbhandler.ProductHandler;
import com.example.salesmanager.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductListSellingActivity extends AppCompatActivity {
    TextView txtCategoryName;
    int categoryId;
    ListView lv;
    List<Product> productList;
    ProductHandler productHandler;

    ProductSellingAdapter productSellingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list_selling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        display();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), DetailProductActivity.class);
                Product product = (Product) productSellingAdapter.getItem(position);
                intent.putExtra("product_id", product.getId());
                intent.putExtra("category_name", txtCategoryName.getText());
                startActivity(intent);
            }
        });

    }

    private void display() {
        txtCategoryName = (TextView) findViewById(R.id.txtCategoryname);
        lv = (ListView) findViewById(R.id.lv_user_product);
        productList = new ArrayList<>();

        Intent intent = getIntent(); // Lấy Intent hiện tại
        int category_id = intent.getIntExtra("category_id", 1);
        txtCategoryName.setText(intent.getStringExtra("category_name"));

        productHandler = new ProductHandler(this);
        productList = productHandler.getAllProductByCategoryId(category_id);

        productSellingAdapter = new ProductSellingAdapter(this, R.layout.selling_item_product, productList);
        lv.setAdapter(productSellingAdapter);
    }
}