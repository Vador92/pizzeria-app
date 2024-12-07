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

/**
 * This is the Current Order Activity class, which manages the frontend responses on the Current Order Page
 * This class changes the data shown in the frontend based on the backend data processing
 * @author Varun Doreswamy, Yuet Yue
 */
public class CurrentOrderActivity extends AppCompatActivity {

    // Instance Variables
    private ListView currentCartList;
    private EditText cartSubTotal, cartTax, cartTotal;
    private TextView orderNumber;
    private Button removePizza, placeOrder, clearOrder;
    private ArrayAdapter<Pizza> adapter;
    private int selectedIndex = -1;

    /**
     * This method initializes the Current Order activity by setting up the:
     * UI
     * Event listeners
     * Displaying the current order details
     * @param savedInstanceState is the previous saved state of activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);

        // Initialize the UI and set up event listeners
        initializeUIComponents();
        setupBackButton();
        setupListView();
        setupButtonActions();

        // Display the current order
        updateOrderDisplay();
    }

    /**
     * This method initializes UI components by linking them to their respective views
     */
    private void initializeUIComponents() {
        currentCartList = findViewById(R.id.currentCartList);
        cartSubTotal = findViewById(R.id.cartSubTotal);
        cartTax = findViewById(R.id.cartTax);
        cartTotal = findViewById(R.id.cartTotal);
        orderNumber = findViewById(R.id.orderNumber); // TextView for the order number
        removePizza = findViewById(R.id.removePizza);
        placeOrder = findViewById(R.id.placeOrder);
        clearOrder = findViewById(R.id.clearOrder);
    }

    /**
     * This method sets up the back button to close the activity when clicked
     */
    private void setupBackButton() {
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());
    }

    /**
     * This method configures the ListView to handle item selection and show feedback
     */
    private void setupListView() {
        currentCartList.setOnItemClickListener((parent, view, position, id) -> {
            selectedIndex = position;
            Toast.makeText(this, "Selected: " +
                    OrderManager.getInstance().getCurrentOrder().getPizzas().get(position).toString(),
                    Toast.LENGTH_SHORT).show();
        });
    }

    /**
     * This method sets up the actions for the remove, place, and clear order buttons
     */
    private void setupButtonActions() {
        // Remove the selected pizza
        removePizza.setOnClickListener(v -> handleRemovePizza());

        // Place the current order
        placeOrder.setOnClickListener(v -> handlePlaceOrder());

        // Clear the entire order
        clearOrder.setOnClickListener(v -> handleClearOrder());
    }

    /**
     * This method handles removing the selected pizza from the order
     */
    private void handleRemovePizza() {
        if (selectedIndex != -1) {
            OrderManager.getInstance().getCurrentOrder().removePizza(
                    OrderManager.getInstance().getCurrentOrder().getPizzas().get(selectedIndex)
            );
            selectedIndex = -1; // Reset selection
            updateOrderDisplay();
        } else {
            Toast.makeText(this, "Please select a pizza to remove!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method handles placing the current order
     */
    private void handlePlaceOrder() {
        if (!OrderManager.getInstance().getCurrentOrder().getPizzas().isEmpty()) {
            OrderManager.getInstance().placeCurrentOrder();
            Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_SHORT).show();
            updateOrderDisplay();
        } else {
            Toast.makeText(this, "No pizzas in the order to place!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method handles clearing the current order
     */
    private void handleClearOrder() {
        OrderManager.getInstance().cancelCurrentOrder();
        Toast.makeText(this, "Order cleared!", Toast.LENGTH_SHORT).show();
        updateOrderDisplay();
    }

    /**
     * This method updates the ListView and price details
     */
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
