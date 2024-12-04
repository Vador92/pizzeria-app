package com.example.rupizzeriaapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CurrentOrderActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);

        // Example: Display the current order
        // List<String> currentOrder = OrderManager.getInstance().getCurrentOrder();
    }
}
