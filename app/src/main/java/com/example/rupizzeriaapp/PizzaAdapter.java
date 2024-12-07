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

/**
 * This is the Pizza Adapter class, it manages the data that goes into the UI components based on the:
 * Pizza Type
 * Corresponding Information (Toppings, Crust, etc)
 * @author Varun Doreswamy, Yuet Yue
 */
public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {

    // Instance Variables
    private Context context;
    private List<Pizza> pizzaList;

    /**
     * This is the default constructor that creates the Pizza Adapter object
     * @param context is the context of the application, activity, or UI component information
     * @param pizzaList is the list of pizzas that will be displayed
     */
    public PizzaAdapter(Context context, List<Pizza> pizzaList) {
        this.context = context;
        this.pizzaList = pizzaList;
    }

    /**
     * This method inflates the layout for each pizza item in the RecyclerView
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return the updated view or Page with all the loaded information
     */
    @NonNull
    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pizza_item, parent, false);
        return new PizzaViewHolder(view);
    }

    /**
     * This method binds the Pizza data to the views for the specified position in the RecyclerView
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
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

    /**
     * This is the getter method for the count of items in the list of pizzas
     * @return the length of the pizza list, which corresponds to the amount of pizza types available
     */
    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    /**
     * This is the getter method for the correlated images of each pizza type
     * @param pizza is the type of pizza that an image is correlated to
     * @return the image of a specified pizza type
     */
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

    /**
     * This method is holds references to the views for each pizza item in the RecyclerView
     * This method is also responsible for initializing the views in the pizza item layout
     */
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
