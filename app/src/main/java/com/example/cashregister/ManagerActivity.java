package com.example.cashregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ManagerActivity extends AppCompatActivity {

    Button history;
    Button restock;
    Toolbar toolbar;
    ArrayList<History> managerHistoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

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

        if (getIntent().hasExtra("HistoryListOfItems")) {
            managerHistoryList = getIntent().getExtras().getParcelableArrayList("HistoryListOfItems");
            //managerHistoryList = getIntent().getParcelableExtra("HistoryListOfItems");
        }

        history = findViewById(R.id.history_button);
        restock = findViewById(R.id.restock_button);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent historyIntent = new Intent(ManagerActivity.this, HistoryRecyclerActivity.class);
                historyIntent.putExtra("MyStoreHistoryList", managerHistoryList);
//                Bundle bundle = new Bundle();
//                bundle.putParcelableArrayList("MyStoreHistoryList", managerHistoryList);
//                historyIntent.putExtras(bundle);
                startActivity(historyIntent);
            }
        });
        restock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent restockIntent = new Intent(ManagerActivity.this, RestockActivity.class);
                startActivity(restockIntent);
            }
        });

    }

}