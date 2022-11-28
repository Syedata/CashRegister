package com.example.cashregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RestockActivity extends AppCompatActivity {

    Button ok, cancel;
    EditText newQuantityText;
    ListView newProductListView;
    Toolbar toolbar;
    Product itemToUpdate;
    ProductBaseAdapter restockAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);

        toolbar = findViewById(R.id.custom_toolbar);
//        TextView mTitle = (TextView) toolbar.findViewById(R.id.custom_title);
        toolbar.setNavigationIcon(com.google.android.material.R.drawable.abc_ic_ab_back_material);
        setSupportActionBar(toolbar);
//        mTitle.setText("Cash Register");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        newQuantityText = findViewById(R.id.new_quantity_text);
        ok = findViewById(R.id.ok_button);
        cancel = findViewById(R.id.cancel_button);

        newProductListView = findViewById(R.id.new_product_list);
        restockAdapter = new ProductBaseAdapter(((MyApp) getApplication()).getAppProductList(), RestockActivity.this);
        newProductListView.setAdapter(restockAdapter);


        newProductListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                itemToUpdate = ((MyApp) getApplication()).getAppProductList().get(i);

            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (newQuantityText.getText().toString().isEmpty()) {
                    Toast.makeText(RestockActivity.this, "Please enter the quantity", Toast.LENGTH_SHORT).show();
                } else if (itemToUpdate == null) {
                    Toast.makeText(RestockActivity.this, "Please select the item to restock", Toast.LENGTH_SHORT).show();
                } else {
                    int QuantityToUpdate = Integer.parseInt(newQuantityText.getText().toString());
                    int newQuantity = itemToUpdate.getQuantity() + QuantityToUpdate;
                    itemToUpdate.setQuantity(newQuantity);
                    restockAdapter.notifyDataSetChanged();

                }
                itemToUpdate = null;
                newQuantityText.setText("");
            }

        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}