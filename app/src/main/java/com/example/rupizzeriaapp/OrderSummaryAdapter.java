package com.example.rupizzeriaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderSummaryAdapter extends RecyclerView.Adapter<OrderSummaryAdapter.ViewHolder> {

    private List<Pizza> pizzaList;

    // Constructor
    public OrderSummaryAdapter(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }

    // Method to update the data in the adapter
    public void updateOrderSummary(List<Pizza> newPizzaList) {
        this.pizzaList = newPizzaList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_summary, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);
        holder.textViewPizzaName.setText(pizza.getClass().getSimpleName());
        holder.textViewPizzaPrice.setText(String.format("$%.2f", pizza.price()));
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    // ViewHolder class to bind data to the views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPizzaName, textViewPizzaPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPizzaName = itemView.findViewById(R.id.textViewPizzaName);
            textViewPizzaPrice = itemView.findViewById(R.id.textViewPizzaPrice);
        }
    }
}
