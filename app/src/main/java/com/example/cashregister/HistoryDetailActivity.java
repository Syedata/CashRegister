package com.example.cashregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HistoryDetailActivity extends AppCompatActivity {
    TextView historyDetailText;
    History historyDetailItem;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);
        toolbar = findViewById(R.id.custom_toolbar);
        toolbar.setNavigationIcon(com.google.android.material.R.drawable.abc_ic_ab_back_material);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        historyDetailText = findViewById(R.id.history_detail_text);
        historyDetailItem = getIntent().getParcelableExtra("StoreHistoryItemDetail");
        historyDetailText.setText("Product: " + historyDetailItem.getName() + "\n" + "Quantity Purchased: " + historyDetailItem.getQuantityPurchased() + "\n" + "Total Price: " + historyDetailItem.getTotalPrice() + "\n" + "Purchased Date: " + historyDetailItem.getDate());
    }
}