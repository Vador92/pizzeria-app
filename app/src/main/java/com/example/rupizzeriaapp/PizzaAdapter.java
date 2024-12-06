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

    private final Context context;
    private final List<Pizza> pizzaList;

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

        // Determine the style based on class name or toString
        String style = pizza.toString().contains("NY") ? "NY Style" : "Chicago Style";

        // Set text for name and toppings
        holder.pizzaName.setText(String.format("%s %s", style, pizza.getClass().getSimpleName()));
        if (pizza.getToppings().isEmpty()) {
            holder.pizzaToppings.setText("Can choose up to 7 toppings");
        } else {
            holder.pizzaToppings.setText(pizza.getToppings().toString());
        }


        // Set the appropriate image
        holder.pizzaImage.setImageResource(getPizzaImage(pizza));

        // Navigate to PizzaDetailActivity on click
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PizzaDetailActivity.class);
            intent.putExtra("pizzaName", String.format("%s %s", style, pizza.getClass().getSimpleName()));
            intent.putExtra("pizzaToppings", pizza.getToppings().toString());
            intent.putExtra("pizzaSize", pizza.getSize().toString());
            intent.putExtra("pizzaPrice", String.valueOf(pizza.price()));
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    private int getPizzaImage(Pizza pizza) {
        // Check for NY Style Pizzas
        if (pizza instanceof Deluxe && pizza.toString().contains("NY")) {
            return R.drawable.nydeluxe; // Replace with NY Deluxe image resource ID
        } else if (pizza instanceof Meatzza && pizza.toString().contains("NY")) {
            return R.drawable.nymeatzza; // Replace with NY Meatzza image resource ID
        } else if (pizza instanceof BBQChicken && pizza.toString().contains("NY")) {
            return R.drawable.nybbqchicken; // Replace with NY BBQ Chicken image resource ID
        } else if (pizza.toString().contains("NY")) {
            return R.drawable.nybuildyourown; // Replace with NY Build Your Own image resource ID
        }

        // Check for Chicago Style Pizzas
        if (pizza instanceof Deluxe && pizza.toString().contains("Chicago")) {
            return R.drawable.chicagodeluxe; // Replace with Chicago Deluxe image resource ID
        } else if (pizza instanceof Meatzza && pizza.toString().contains("Chicago")) {
            return R.drawable.chicagomeatzza; // Replace with Chicago Meatzza image resource ID
        } else if (pizza instanceof BBQChicken && pizza.toString().contains("Chicago")) {
            return R.drawable.chicagobbqchicken; // Replace with Chicago BBQ Chicken image resource ID
        } else {
            return R.drawable.chicagobuildyourown; // Replace with Chicago Build Your Own image resource ID
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
