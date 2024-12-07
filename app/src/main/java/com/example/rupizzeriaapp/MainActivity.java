package com.example.rupizzeriaapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * This is the Main Activity class, which manages the frontend responses on the Main Menu Page
 * This class changes the data shown in the frontend based on the backend data processing
 * @author Varun Doreswamy, Yuet Yue
 */
public class MainActivity extends AppCompatActivity {

    /**
     * This method initializes the Main Menu activity by setting up the UI and all related components
     * @param savedInstanceState is the previous saved state of the activity
     */
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

    /**
     * This method sets up the Event Listeners that detect any user interaction with UI button components
     */
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