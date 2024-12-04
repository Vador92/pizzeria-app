package com.example.rupizzeriaapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class PizzaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizzas);

        // Example: Add pizzas to current order
        // OrderManager.getInstance().addToOrder("Chicago Style Pizza");
    }
}

