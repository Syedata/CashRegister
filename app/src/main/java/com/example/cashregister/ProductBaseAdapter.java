package com.example.cashregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ProductBaseAdapter extends BaseAdapter {

    List<Product> list;
    Context context;

    public ProductBaseAdapter(List<Product> prodList, Context c) {
        this.list = prodList;
        this.context = c;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    //every row in base adapter is single view
    public View getView(int i, View view, ViewGroup viewGroup) {
        //inflate the layout in the listview
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.list_baseadapter_row, viewGroup, false);

        TextView prodName = v.findViewById(R.id.prod_name);
        prodName.setText(list.get(i).getName());


        TextView prodPrice = v.findViewById(R.id.prod_price);
        prodPrice.setText(String.valueOf(list.get(i).getPrice()));


        TextView prodQuantity = v.findViewById(R.id.prod_quantity);
        prodQuantity.setText(String.valueOf(list.get(i).getQuantity()));

        return v;
    }
}

