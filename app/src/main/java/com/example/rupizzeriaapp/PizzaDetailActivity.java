package com.example.rupizzeriaapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PizzaDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_detail);

        ImageView pizzaImage = findViewById(R.id.pizzaImage);
        TextView pizzaTitle = findViewById(R.id.pizzaTitle);
        TextView pizzaToppings = findViewById(R.id.pizzaToppings);

        // Get data from intent
        String title = getIntent().getStringExtra("pizzaTitle");
        String toppings = getIntent().getStringExtra("pizzaToppings");
        int imageResId = getIntent().getIntExtra("pizzaImage", -1);

        pizzaImage.setImageResource(imageResId);
        pizzaTitle.setText(title);
        pizzaToppings.setText(toppings);
    }
}
