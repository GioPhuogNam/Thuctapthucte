package com.example.salesmanager.activities.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.salesmanager.R;

public class BusinessManagementActivity extends AppCompatActivity {
    CardView btnProduct_PM;
    CardView btnBill;
    CardView btnStatistic;
    CardView btnSelling;
    CardView btnProfile;
    CardView btnCategory_PM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_management);

        btnProduct_PM = (CardView) findViewById(R.id.btnProduct_PM);
        btnBill = (CardView) findViewById(R.id.btnBill);
        btnStatistic = (CardView) findViewById(R.id.btnStatistic);
        btnSelling = (CardView) findViewById(R.id.btnSelling);
        btnProfile = (CardView) findViewById(R.id.btnProfile);
        btnCategory_PM = (CardView) findViewById(R.id.btnCategory_PM);

        btnProduct_PM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductListActivity.class);
                startActivity(intent);
            }
        });

        btnCategory_PM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(intent);
            }
        });

        btnBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BillStatisticUserActivity.class);
                startActivity(intent);
            }
        });

        btnStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StatisticActivity.class);
                startActivity(intent);
            }
        });

        btnSelling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SellingActivity.class);
                startActivity(intent);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}