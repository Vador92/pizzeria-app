package com.example.rupizzeriaapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class CurrentOrderActivity extends AppCompatActivity {

    private ListView currentCartList;
    private EditText cartSubTotal, cartTax, cartTotal;
    private Button removePizza, placeOrder, clearOrder;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);

        // Example: Display the current order
        // List<String> currentOrder = OrderManager.getInstance().getCurrentOrder();
    }
}
