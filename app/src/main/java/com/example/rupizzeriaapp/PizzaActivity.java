package com.example.rupizzeriaapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class PizzaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizzas);

        // Initialize New York Style buttons
        ImageButton nyBuildYourOwn = findViewById(R.id.NYStyleBuildYourOwn);
        ImageButton nyMeatzza = findViewById(R.id.NYStyleMeatzza);
        ImageButton nyBBQChicken = findViewById(R.id.NYStyleBBQChicken);
        ImageButton nyDeluxe = findViewById(R.id.NYStyleDeluxe);

        // Initialize Chicago Style buttons
        ImageButton chicagoBuildYourOwn = findViewById(R.id.ChicagoStyleBuildYourOwn);
        ImageButton chicagoMeatzza = findViewById(R.id.ChicagoStyleMeatzza);
        ImageButton chicagoBBQChicken = findViewById(R.id.ChicagoStyleBBQChicken);
        ImageButton chicagoDeluxe = findViewById(R.id.ChicagoStyleDeluxe);

        // Set up click listeners for New York Style Pizzas
        nyBuildYourOwn.setOnClickListener(v -> openPizzaDetail("Build Your Own", "NY"));
        nyMeatzza.setOnClickListener(v -> openPizzaDetail("Meatzza", "NY"));
        nyBBQChicken.setOnClickListener(v -> openPizzaDetail("BBQ Chicken", "NY"));
        nyDeluxe.setOnClickListener(v -> openPizzaDetail("Deluxe", "NY"));

        // Set up click listeners for Chicago Style Pizzas
        chicagoBuildYourOwn.setOnClickListener(v -> openPizzaDetail("Build Your Own", "Chicago"));
        chicagoMeatzza.setOnClickListener(v -> openPizzaDetail("Meatzza", "Chicago"));
        chicagoBBQChicken.setOnClickListener(v -> openPizzaDetail("BBQ Chicken", "Chicago"));
        chicagoDeluxe.setOnClickListener(v -> openPizzaDetail("Deluxe", "Chicago"));
    }

    private void openPizzaDetail(String pizzaType, String style) {
        Intent intent = new Intent(this, PizzaDetailActivity.class);
        intent.putExtra("PIZZA_TYPE", pizzaType);
        intent.putExtra("STYLE", style);
        startActivity(intent);
    }
}
