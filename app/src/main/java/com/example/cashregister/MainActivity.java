package com.example.cashregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton, sevenButton, eightButton, nineButton, zeroButton, clearButton, buyButton, managerButton;
    TextView productType, totalPrice, totalQuantity;
    ListView productList;
    Product itemToBuy;
    String quantityText = "";
    ProductBaseAdapter adapter;
    ArrayList<History> historyList = new ArrayList<>();
    Toolbar toolbar;
    boolean validPurchase = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //findViewById returns a view by its id
        oneButton = findViewById(R.id.one_button);
        twoButton = findViewById(R.id.two_button);
        threeButton = findViewById(R.id.three_button);
        fourButton = findViewById(R.id.four_button);
        fiveButton = findViewById(R.id.five_button);
        sixButton = findViewById(R.id.six_button);
        sevenButton = findViewById(R.id.seven_button);
        eightButton = findViewById(R.id.eight_button);
        nineButton = findViewById(R.id.nine_button);
        zeroButton = findViewById(R.id.zero_button);
        clearButton = findViewById(R.id.clear_button);
        buyButton = findViewById(R.id.buy_button);
        managerButton = findViewById(R.id.manager_button);
        productType = findViewById(R.id.product_type);
        totalPrice = findViewById(R.id.total_price);
        totalQuantity = findViewById(R.id.quantity_text);
        productList = findViewById(R.id.list_products);

        oneButton.setOnClickListener(this);
        twoButton.setOnClickListener(this);
        threeButton.setOnClickListener(this);
        fourButton.setOnClickListener(this);
        fiveButton.setOnClickListener(this);
        sixButton.setOnClickListener(this);
        sevenButton.setOnClickListener(this);
        eightButton.setOnClickListener(this);
        nineButton.setOnClickListener(this);
        zeroButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
        buyButton.setOnClickListener(this);
        managerButton.setOnClickListener(this);


        adapter = new ProductBaseAdapter(((MyApp) getApplication()).getAppProductList(), MainActivity.this);
        productList.setAdapter(adapter);

        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the name of item and setting it in productType
                itemToBuy = ((MyApp) getApplication()).getAppProductList().get(i);
                productType.setText(itemToBuy.getName());
            }
        });

    }
//onResume() is called when navigating back to the activity
//creating the adapter again to populate the ListView
    @Override
    public void onResume(){
        super.onResume();
        productType.setText("");
        totalQuantity.setText("");
        totalPrice.setText("");

        //passing product list and context to adapter
        adapter = new ProductBaseAdapter(((MyApp) getApplication()).getAppProductList(), MainActivity.this);
        productList.setAdapter(adapter);
        //adding click listener on every row which is a view in base adapter
        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //storing the selected item in itemToBuy
                itemToBuy = ((MyApp) getApplication()).getAppProductList().get(i);
                // productType text will display the product name of the selected item
                productType.setText(itemToBuy.getName());
            }
        });

    }
//click listeners for buttons
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //if the user click  C button
            case R.id.clear_button:

                quantityText = "";
                productType.setText("");
                totalQuantity.setText("");
                totalPrice.setText("");
                break;

            case R.id.buy_button:

                if (validatePurchase()) {
                    int itemQuantity = Integer.parseInt(totalQuantity.getText().toString());
                    //if the itemQuantity that user enter is less than the quantity available in stock then only user can buy a product
                    if (itemQuantity <= itemToBuy.getQuantity()) {
                        double totalAmount = itemQuantity * itemToBuy.getPrice();
                        String totalAmountString = String.format("%.2f", totalAmount);
                        totalPrice.setText(totalAmountString);
                        //adding the purchased item in the history list
                        History historyItem = new History(itemToBuy.getName(), (new Date().toString()), itemQuantity, totalAmount);
                        historyList.add(historyItem);
                        //updating the stock that is left after the item is sold
                        int leftQuantity = itemToBuy.getQuantity() - itemQuantity;
                        itemToBuy.setQuantity(leftQuantity);
                        adapter.notifyDataSetChanged();
                        showTheAlert(itemToBuy);


                    } else {
                        Toast.makeText(MainActivity.this, "Not enough quantity in the stock", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.manager_button:

                Intent intent = new Intent(this, ManagerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("HistoryListOfItems", historyList);
                intent.putExtras(bundle);
                startActivity(intent);
                break;

            default:
                //if the user click 0-9 buttons
                String value = ((Button) view).getText().toString();
                quantityText = quantityText + value;
                totalQuantity.setText(quantityText);
        }
    }

    boolean validatePurchase() {
        if (!totalQuantity.getText().toString().isEmpty() && !productType.getText().toString().isEmpty())
            validPurchase = true;
        return validPurchase;
    }

    void showTheAlert(Product productPurchased) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thank you for your purchase");
        builder.setMessage("Your purchase is " + totalQuantity.getText().toString() + " " + productPurchased.getName() + " for $" + totalPrice.getText().toString());
        builder.setPositiveButton("OK", null);
        builder.create().show();
    }
}