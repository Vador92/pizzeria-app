package com.example.rupizzeriaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;

public class PizzaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizzas);

        LinearLayout pizzaContainer = findViewById(R.id.pizzaContainer);

        // Data for pizzas
        ArrayList<HashMap<String, String>> pizzas = new ArrayList<>();
        pizzas.add(createPizza("New York Style Build Your Own", "Toppings:", R.drawable.nybuildyourown));
        pizzas.add(createPizza("New York Style Meatzza", "Toppings: Sausage, Pepperoni, Beef, Ham", R.drawable.nymeatzza));
        pizzas.add(createPizza("New York Style BBQ Chicken", "Toppings: BBQ Chicken, Green Pepper, Provolone, Cheddar", R.drawable.nybbqchicken));
        pizzas.add(createPizza("New York Style Deluxe", "Toppings: Sausage, Pepperoni, Green Pepper, Onion, Mushroom", R.drawable.nydeluxe));
        pizzas.add(createPizza("Chicago Style Build Your Own", "Toppings:", R.drawable.chicagobuildyourown));
        pizzas.add(createPizza("Chicago Style Meatzza", "Toppings: Sausage, Pepperoni, Beef, Ham", R.drawable.chicagomeatzza));
        pizzas.add(createPizza("Chicago Style BBQ Chicken", "Toppings: BBQ Chicken, Green Pepper, Provolone, Cheddar", R.drawable.chicagobbqchicken));
        pizzas.add(createPizza("Chicago Style Deluxe", "Toppings: Sausage, Pepperoni, Green Pepper, Onion, Mushroom", R.drawable.chicagodeluxe));

        // Dynamically add pizza items
        for (HashMap<String, String> pizza : pizzas) {
            View pizzaItem = LayoutInflater.from(this).inflate(R.layout.pizza_item, pizzaContainer, false);

            // Set pizza details
            TextView pizzaTitle = pizzaItem.findViewById(R.id.pizzaTitle);
            TextView pizzaToppings = pizzaItem.findViewById(R.id.pizzaToppings);
            pizzaTitle.setText(pizza.get("title"));
            pizzaToppings.setText(pizza.get("toppings"));

            pizzaItem.findViewById(R.id.pizzaImage).setBackgroundResource(Integer.parseInt(pizza.get("image")));

            // Add click listener
            pizzaItem.setOnClickListener(v -> {
                Intent intent = new Intent(PizzaActivity.this, PizzaDetailActivity.class);
                intent.putExtra("title", pizza.get("title"));
                intent.putExtra("toppings", pizza.get("toppings"));
                startActivity(intent);
            });

            pizzaContainer.addView(pizzaItem);
        }
    }

    // Helper method to create pizza data
    private HashMap<String, String> createPizza(String title, String toppings, int imageResId) {
        HashMap<String, String> pizza = new HashMap<>();
        pizza.put("title", title);
        pizza.put("toppings", toppings);
        pizza.put("image", String.valueOf(imageResId));
        return pizza;
    }
}
