package com.example.rupizzeriaapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class OrderHistoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        // Example: Display the order history
        // List<String> orderHistory = OrderManager.getInstance().getOrderHistory();
    }
}
