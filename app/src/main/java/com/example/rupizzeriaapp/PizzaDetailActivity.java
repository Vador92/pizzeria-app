package com.example.rupizzeriaapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PizzaDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_detail);

        TextView pizzaName = findViewById(R.id.pizzaName);
        TextView pizzaDetails = findViewById(R.id.pizzaDetails);

        pizzaName.setText(getIntent().getStringExtra("pizzaName"));
        pizzaDetails.setText(getIntent().getStringExtra("pizzaStyle"));
    }
}
