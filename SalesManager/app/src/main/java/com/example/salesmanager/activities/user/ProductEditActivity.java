package com.example.salesmanager.activities.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.salesmanager.R;
import com.example.salesmanager.dbhandler.CategoryHandler;
import com.example.salesmanager.dbhandler.ProductHandler;
import com.example.salesmanager.models.Product;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class ProductEditActivity extends AppCompatActivity {
    Product product;
    EditText txtProductName, txtQuantity, txtPrice;
    ImageView imgHinh;
    ProductHandler productHandler;
    CategoryHandler categoryHandler;
    Spinner spinnerCategory;
    int category;
    ImageButton ibnCamera, ibnFolder;
    Button btnEdit, btnDelete;
    int id;
    int REQUEST_CODE_CAMERA = 454;
    int REQUEST_CODE_FOLDER = 352;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtProductName = (EditText) findViewById(R.id.txtProductName);
        txtQuantity = (EditText) findViewById(R.id.txtQuantity);
        txtPrice = (EditText) findViewById(R.id.txtPrice);
        spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
        ibnCamera = (ImageButton) findViewById(R.id.ibnCamera);
        ibnFolder = (ImageButton) findViewById(R.id.ibnFolder);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        imgHinh = (ImageView) findViewById(R.id.imgHinh);

        productHandler = new ProductHandler(this);
        categoryHandler = new CategoryHandler(this);

        loadSpinner();
        getData();

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = categoryHandler.getCategoryIdByName(spinnerCategory.getSelectedItem().toString().trim());
                productHandler.editProduct(id, category, txtProductName.getText().toString(), Integer.parseInt(txtQuantity.getText().toString()), Integer.parseInt(txtPrice.getText().toString()), convertToArrayByte(imgHinh));
                Intent intent = new Intent(getApplicationContext(), ProductListActivity.class);
                startActivity(intent);
                Toast.makeText(ProductEditActivity.this, "Sửa hàng thành công!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ProductEditActivity.this);
                dialog.setTitle("XÓA SẢN PHẨM NÀY?");
                dialog.setMessage("Bạn thật sự muốn xóa sản phẩm này?");

                dialog.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), ProductListActivity.class);
                        startActivity(intent);
                        productHandler.deleteProduct(id);

                        Toast.makeText(ProductEditActivity.this, "Đã xóa!", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Hủy", null).show();
            }
        });

        ibnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, REQUEST_CODE_CAMERA);
            }
        });

        ibnFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_PICK);
                in.setType("image/*");
                startActivityForResult(in, REQUEST_CODE_FOLDER);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK & data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgHinh.setImageBitmap(bitmap);

        }
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK & data != null) {
            Uri uri = data.getData();
            try {
                InputStream ipstream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(ipstream);
                imgHinh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public byte[] convertToArrayByte(ImageView img) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public void loadSpinner() {

        CategoryHandler categoryHandler = new CategoryHandler(this);
        List<String> category  = categoryHandler.getAllNameCategoryForCreateProduct();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, category);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spinnerCategory.setAdapter(dataAdapter);

        product = (Product) getIntent().getSerializableExtra("Edit");
        int position = dataAdapter.getPosition(String.valueOf(product.getCategoryName()));
        spinnerCategory.setSelection(position);
    }

    public void getData() {
        if (getIntent().getExtras() != null) {
            product = (Product) getIntent().getSerializableExtra("Edit");
            id = product.getId();
            byte[] hinhanh = product.getImage();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
            imgHinh.setImageBitmap(bitmap);
            txtProductName.setText(product.getName().toString());
            txtQuantity.setText(String.valueOf(product.getQuantity()));
            txtPrice.setText(String.valueOf(product.getPrice()));

        }
    }


}