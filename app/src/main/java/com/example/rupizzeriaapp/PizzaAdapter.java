package com.example.rupizzeriaapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {

    private Context context;
    private List<Pizza> pizzaList;

    public PizzaAdapter(Context context, List<Pizza> pizzaList) {
        this.context = context;
        this.pizzaList = pizzaList;
    }

    @NonNull
    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pizza_item, parent, false);
        return new PizzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);

        // Bind pizza data to the UI
        String style = pizza.toString().contains("Chicago") ? "Chicago Style" : "NY Style";
        holder.pizzaName.setText(String.format("%s %s", style, pizza.getClass().getSimpleName()));
        holder.pizzaToppings.setText(pizza.getToppings().isEmpty() ?
                "Can choose up to 7 toppings" : pizza.getToppings().toString());
        holder.pizzaImage.setImageResource(getPizzaImage(pizza));

        // Set onClickListener for each pizza item
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PizzaDetailActivity.class);
            intent.putExtra("selectedPizza", pizza); // Pass the Pizza object
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    private int getPizzaImage(Pizza pizza) {
        if (pizza.toString().contains("Chicago")) {
            if (pizza instanceof Deluxe) {
                return R.drawable.chicagodeluxe;
            } else if (pizza instanceof Meatzza) {
                return R.drawable.chicagomeatzza;
            } else if (pizza instanceof BBQChicken) {
                return R.drawable.chicagobbqchicken;
            } else {
                return R.drawable.chicagobuildyourown;
            }
        } else {
            if (pizza instanceof Deluxe) {
                return R.drawable.nydeluxe;
            } else if (pizza instanceof Meatzza) {
                return R.drawable.nymeatzza;
            } else if (pizza instanceof BBQChicken) {
                return R.drawable.nybbqchicken;
            } else {
                return R.drawable.nybuildyourown;
            }
        }
    }

    public static class PizzaViewHolder extends RecyclerView.ViewHolder {
        TextView pizzaName, pizzaToppings;
        ImageView pizzaImage;

        public PizzaViewHolder(@NonNull View itemView) {
            super(itemView);
            pizzaName = itemView.findViewById(R.id.pizzaName);
            pizzaToppings = itemView.findViewById(R.id.pizzaToppings);
            pizzaImage = itemView.findViewById(R.id.pizzaImage);
        }
    }
}
