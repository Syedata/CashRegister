package com.example.cashregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryRecyclerActivity extends AppCompatActivity implements HistoryRecycleAdapter.ItemListener {
    RecyclerView recyclerHistoryView;
    ArrayList<History> recyclerHistoryList;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_recycler);

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

        recyclerHistoryView = findViewById(R.id.history_recyclerview);
        recyclerHistoryList = getIntent().getExtras().getParcelableArrayList("MyStoreHistoryList");
        HistoryRecycleAdapter adapter = new HistoryRecycleAdapter(this, recyclerHistoryList);
        //telling the adapter that this activity is its listener
        adapter.listener = this;
        recyclerHistoryView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerHistoryView.setLayoutManager(llm);
        recyclerHistoryView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

    }

    @Override
    public void onItemClicked(History history) {
        Intent intent = new Intent(this, HistoryDetailActivity.class);
        intent.putExtra("StoreHistoryItemDetail", history);
        startActivity(intent);
    }
}