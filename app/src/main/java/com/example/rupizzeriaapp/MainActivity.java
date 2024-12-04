package com.example.rupizzeriaapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupListeners();
    }

    private void setupListeners() {
        // Navigate to PizzaActivity
        ImageButton pizzaButton = findViewById(R.id.pizzaButton);
        pizzaButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PizzaActivity.class);
            startActivity(intent);
        });

        // Navigate to CurrentOrderActivity
        ImageButton currentOrderButton = findViewById(R.id.currentOrderButton);
        currentOrderButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CurrentOrderActivity.class);
            startActivity(intent);
        });

        // Navigate to OrderHistoryActivity
        ImageButton orderHistoryButton = findViewById(R.id.orderHistoryButton);
        orderHistoryButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, OrderHistoryActivity.class);
            startActivity(intent);
        });
    }
}