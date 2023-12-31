package com.example.salesmanager.activities.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.salesmanager.R;
import com.example.salesmanager.dbhandler.ProductHandler;
import com.example.salesmanager.models.Product;

public class DetailProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        int product_id = intent.getIntExtra("product_id", 1);
        String category_name = intent.getStringExtra("category_name");
        ProductHandler productHandler = new ProductHandler(this);
        Product product = productHandler.findById(product_id);

        EditText txtProductName = (EditText) findViewById(R.id.txtProductName);
        EditText editxtcategory = (EditText) findViewById(R.id.editxtcategory);
        EditText txtQuantity = (EditText) findViewById(R.id.txtQuantity);
        EditText txtPrice = (EditText) findViewById(R.id.txtPrice);
        ImageView imgHinh = (ImageView) findViewById(R.id.imgHinh);

        txtProductName.setText(product.getName());
        txtPrice.setText(product.getPrice() + "");
        txtQuantity.setText(product.getQuantity() + "");
        editxtcategory.setText(category_name);
        byte[] productImage = product.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(productImage, 0, productImage.length);
        imgHinh.setImageBitmap(bitmap);
    }
}