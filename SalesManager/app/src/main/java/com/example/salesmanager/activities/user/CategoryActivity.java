package com.example.salesmanager.activities.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.salesmanager.R;
import com.example.salesmanager.dbhandler.CategoryHandler;
import com.example.salesmanager.models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    EditText txtNameCategory,txtIDCategory;
    ListView lv;
    Button btnCreate, btnEdit, btnDelete;
    ArrayAdapter<String> arrayAdapter;
    List<String> list = new ArrayList<String>();
    Context content;
    CategoryHandler handleCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtNameCategory = (EditText) findViewById(R.id.txtNameCategory);
        txtIDCategory = (EditText) findViewById(R.id.txtId);
        lv = (ListView) findViewById(R.id.lvCategory);
        btnCreate = findViewById(R.id.add);
        btnEdit = findViewById(R.id.edit);
        btnDelete = findViewById(R.id.delete);

        content = this;
        display();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                String[] arr = selectedItem.split(" - ");
                txtIDCategory.setText(arr[0]);
                txtNameCategory.setText(arr[1]);
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputString = txtNameCategory.getText().toString();
                if(!inputString.isEmpty()){
                    Category category = new Category();
                    category.setNameCategory(txtNameCategory.getText().toString());
                    int kq = handleCategory.insertCategory(category);
                    if(kq == -1){
                        Toast.makeText(content,"Danh mục đã tồn tại",Toast.LENGTH_LONG).show();
                    }else {
                        String text = null;
                        txtNameCategory.setText(text);
                        txtIDCategory.setText(text);
                        display();
                        Toast.makeText(content,"Insert Thành Công",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(content,"Điền đầy đủ thông tin trước khi tạo",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(content);
                dialog.setTitle("XÓA DANH MỤC NÀY?");
                dialog.setMessage("Bạn thật sự muốn xóa danh mục này?");

                dialog.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String inputString = txtNameCategory.getText().toString();
                        String idString = txtIDCategory.getText().toString();
                        if(!inputString.isEmpty() && !idString.isEmpty()){
                            String _id = txtIDCategory.getText().toString();
                            int kq = handleCategory.deleteCategory(_id);
                            if(kq == -1){
                                Toast.makeText(content,"Delete Thất Bại",Toast.LENGTH_LONG).show();
                            }else {
                                String text = null;
                                txtNameCategory.setText(text);
                                txtIDCategory.setText(text);
                                display();
                                Toast.makeText(content,"Delete Thành Công",Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(content,"Chọn danh mục muốn xóa",Toast.LENGTH_LONG).show();
                        }
                    }
                }).setNegativeButton("Hủy", null).show();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputString = txtNameCategory.getText().toString();
                String idString = txtIDCategory.getText().toString();
                if(!inputString.isEmpty() && !idString.isEmpty()){
                    Category category = new Category();
                    category.setNameCategory(txtNameCategory.getText().toString());
                    category.setIdCategory(Integer.parseInt(txtIDCategory.getText().toString()));

                    int kq = handleCategory.updateCategory(category);
                    if(kq == -1){
                        Toast.makeText(content,"Update Thất Bại",Toast.LENGTH_LONG).show();
                    }else {
                        String text = null;
                        txtIDCategory.setText(text);
                        txtNameCategory.setText(text);
                        display();
                        Toast.makeText(content,"Update Thành Công",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(content,"Chọn danh mục muốn sửa",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    protected void display(){
        list.clear();
        handleCategory = new CategoryHandler(content);
        list = handleCategory.getAllCategory();
        arrayAdapter = new ArrayAdapter<>(content, android.R.layout.simple_list_item_1,list);
        lv.setAdapter(arrayAdapter);
    }
}