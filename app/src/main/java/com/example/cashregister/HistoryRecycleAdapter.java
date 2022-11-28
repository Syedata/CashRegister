package com.example.cashregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
//recycler adapter needs a view holder so that it can create that view holder and bind data to it
//existing view holder are going to be recycled to take the new data


public class HistoryRecycleAdapter extends RecyclerView.Adapter<HistoryRecycleAdapter.HistoryViewHolder>{

    //every row in recycler adapter is view holder instead of view so we don't have default listener for it
    //creating a listener in adapter to notify when an action happened

    public interface ItemListener{
        void onItemClicked(History historyItem);
    }

    Context context;
    List<History> soldItemHistory;
    ItemListener listener;

    public HistoryRecycleAdapter(Context context, List<History> soldItemHistory) {

        this.context = context;
        this.soldItemHistory = soldItemHistory;
    }

    @NonNull
    @Override
    //returns the view  object that is needed to create a view holder
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //creating a view after inflating the layout
        View v = LayoutInflater.from(context).inflate(R.layout.history_recycleadapter_row,parent,false);
        //creating a view holder object of the existing view
        return new HistoryViewHolder(v);
    }

    @Override
    //binding data on view holder
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.nameText.setText(soldItemHistory.get(position).getName());
        holder.quantityText.setText(soldItemHistory.get(position).getQuantityPurchased()+"");
        holder.priceText.setText(soldItemHistory.get(position).getTotalPrice() + "");
    }

    @Override
    public int getItemCount() {
        return soldItemHistory.size();
    }

    // inner class to create a view holder
    public class HistoryViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView nameText;
        TextView quantityText;
        TextView priceText;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.history_prod_name);
            quantityText = itemView.findViewById(R.id.history_prod_quantity);
            priceText = itemView.findViewById(R.id.history_prod_price);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            listener.onItemClicked(soldItemHistory.get(getAdapterPosition()));

        }
    }


}

