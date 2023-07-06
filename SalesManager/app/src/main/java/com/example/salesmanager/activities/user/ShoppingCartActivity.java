package com.example.salesmanager.activities.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.salesmanager.R;
import com.example.salesmanager.activities.MainActivity;
import com.example.salesmanager.adapters.CartAdapter;
import com.example.salesmanager.dbhandler.BillDetailHandler;
import com.example.salesmanager.dbhandler.BillHandler;
import com.example.salesmanager.dbhandler.ProductHandler;
import com.example.salesmanager.models.Bill;
import com.example.salesmanager.models.BillDetail;
import com.example.salesmanager.models.Cart;
import com.example.salesmanager.models.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {
    ListView lv;
    ProductHandler productHandler;
    BillHandler billHandler;
    BillDetailHandler billDetailHandler;
    List<Cart> cartList;
    Button buttonthanhtoan;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv = (ListView) findViewById(R.id.listviewgiohang);
        buttonthanhtoan = (Button) findViewById(R.id.buttonthanhtoan);

        cartList = new ArrayList<>();

        if (CartActivity.listItem != null) {
            ProductHandler productHandler = new ProductHandler(this);
            for (int i = 0; i < CartActivity.listItem.size(); i++) {
                Product product = productHandler.findById(CartActivity.listItem.get(i).getProduct_id());
                Cart cart = new Cart();
                cart.setId(product.getId());
                cart.setHinhAnh(product.getImage());
                cart.setTenSP(product.getName());
                cart.setGiaTien(product.getPrice());
                cart.setSoLuong(CartActivity.listItem.get(i).getQuantity());
                cartList.add(cart);
            }
        }

        CartAdapter cartAdapter = new CartAdapter(this, R.layout.selling_item_cart_product, cartList);
        lv.setAdapter(cartAdapter);

        buttonthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CartActivity.listItem == null || CartActivity.listItem.size() == 0) {
                    Toast.makeText(ShoppingCartActivity.this, "Giỏ hàng trống", Toast.LENGTH_SHORT).show();
                } else {
                    List<Product> productList = new ArrayList<>();
                    List<Integer> quantityList = new ArrayList<>();
                    productHandler = new ProductHandler(ShoppingCartActivity.this);
                    billHandler = new BillHandler(ShoppingCartActivity.this);
                    billDetailHandler = new BillDetailHandler(ShoppingCartActivity.this);
                    int total_price = 0;
                    for (int i = 0; i < CartActivity.listItem.size(); i++) {
                        Product product = productHandler.findById(CartActivity.listItem.get(i).getProduct_id());
                        productList.add(product);
                        int quantity = CartActivity.listItem.get(i).getQuantity();
                        total_price += product.getPrice() * quantity;
                        quantityList.add(quantity);
                    }
                    Bill bill = new Bill();
                    bill.setUser_id(MainActivity.user_id);
                    bill.setTotal_price(total_price * 1);
                    bill.setDescription("Thanh toán cả giỏ hàng");
                    Date now = new Date();
                    bill.setDate_created(now.toString());

                    if (billHandler.insertBill(bill) == 1) {
                        int bill_id = billHandler.getBillIdNew();
                        // Nếu thêm thành công thì tiến hành thêm chi tiết sản phẩm
                        Toast.makeText(ShoppingCartActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < productList.size(); i++) {
                            BillDetail billDetail = new BillDetail();
                            billDetail.setBill_id(bill_id);
                            billDetail.setProduct_name(productList.get(i).getName());
                            int quantity = quantityList.get(i);
                            billDetail.setQuantity(quantity);
                            billDetail.setPrice(productList.get(i).getPrice());
                            billDetailHandler.insertBillDetail(billDetail);
                            productHandler.editQuantity(productList.get(i).getId(), quantity);
                        }
                        CartActivity.listItem.clear();
                        cartList.clear();
                        cartAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(ShoppingCartActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), BillStatisticUserActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}