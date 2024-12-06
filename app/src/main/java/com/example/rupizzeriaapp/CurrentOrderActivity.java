package com.example.rupizzeriaapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CurrentOrderActivity extends AppCompatActivity {

    private ListView currentCartList;
    private EditText cartSubTotal, cartTax, cartTotal;
    private TextView orderNumber;
    private Button removePizza, placeOrder, clearOrder;
    private ArrayAdapter<Pizza> adapter;
    private int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);

        // Initialize UI components
        currentCartList = findViewById(R.id.currentCartList);
        cartSubTotal = findViewById(R.id.cartSubTotal);
        cartTax = findViewById(R.id.cartTax);
        cartTotal = findViewById(R.id.cartTotal);
        orderNumber = findViewById(R.id.orderNumber); // TextView for the order number
        removePizza = findViewById(R.id.removePizza);
        placeOrder = findViewById(R.id.placeOrder);
        clearOrder = findViewById(R.id.clearOrder);

        // Display the current order
        updateOrderDisplay();

        // Initialize the Back Button
        Button backButton = findViewById(R.id.backButton);

        // Set OnClickListener for the Back Button
        backButton.setOnClickListener(v -> finish());

        // Handle ListView item selection
        currentCartList.setOnItemClickListener((parent, view, position, id) -> {
            selectedIndex = position;
            Toast.makeText(this, "Selected: " +
                    OrderManager.getInstance().getCurrentOrder().getPizzas().get(position).toString(), Toast.LENGTH_SHORT).show();
        });

        // Remove the selected pizza
        removePizza.setOnClickListener(v -> {
            if (selectedIndex != -1) {
                OrderManager.getInstance().getCurrentOrder().removePizza(
                        OrderManager.getInstance().getCurrentOrder().getPizzas().get(selectedIndex)
                );
                selectedIndex = -1; // Reset selection
                updateOrderDisplay();
            } else {
                Toast.makeText(this, "Please select a pizza to remove!", Toast.LENGTH_SHORT).show();
            }
        });

        // Place the current order
        placeOrder.setOnClickListener(v -> {
            if (!OrderManager.getInstance().getCurrentOrder().getPizzas().isEmpty()) {
                OrderManager.getInstance().placeCurrentOrder();
                Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_SHORT).show();
                updateOrderDisplay();
            } else {
                Toast.makeText(this, "No pizzas in the order to place!", Toast.LENGTH_SHORT).show();
            }
        });

        // Clear the entire order
        clearOrder.setOnClickListener(v -> {
            OrderManager.getInstance().cancelCurrentOrder();
            Toast.makeText(this, "Order cleared!", Toast.LENGTH_SHORT).show();
            updateOrderDisplay();
        });
    }

    // Update the ListView and price details
    private void updateOrderDisplay() {
        Order currentOrder = OrderManager.getInstance().getCurrentOrder();

        // Update order number
        orderNumber.setText(String.valueOf(currentOrder.getNumber()));

        // Update ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, currentOrder.getPizzas());
        currentCartList.setAdapter(adapter);

        // Update subtotal, tax, and total
        cartSubTotal.setText(String.format("%.2f", currentOrder.getSubTotal()));
        cartTax.setText(String.format("%.2f", currentOrder.getTax()));
        cartTotal.setText(String.format("%.2f", currentOrder.getTotal()));
    }
}
