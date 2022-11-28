package com.example.cashregister;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;
//main class extending from Application
//managing the products that will be shown in the app
public class MyApp extends Application {
    private Product firstItem;
    private Product secondItem;
    private Product thirdItem;
    private List<Product> appProductList = new ArrayList<>();

    public MyApp() {
        super();
        firstItem = new Product("Pants", 10, 20.44);
        secondItem = new Product("Shoes", 100, 10.44);
        thirdItem = new Product("Hats", 30, 5.9);
        appProductList.add(firstItem);
        appProductList.add(secondItem);
        appProductList.add(thirdItem);
    }

    public List<Product> getAppProductList() {
        return appProductList;
    }

}



