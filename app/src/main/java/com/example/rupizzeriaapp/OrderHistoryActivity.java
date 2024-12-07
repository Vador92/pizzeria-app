package com.example.rupizzeriaapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity {

    private Spinner spinnerOrderNumber;
    private ListView listViewOrderSummary; // Replaced RecyclerView with ListView
    private TextView textViewOrderTotal;
    private Button buttonCancelOrder, buttonExportOrders;

    private ArrayAdapter<Integer> spinnerAdapter;
    private ArrayAdapter<String> listAdapter; // Adapter for ListView

    private List<Order> placedOrders;
    private Order selectedOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        // Retrieve placed orders
        placedOrders = OrderManager.getInstance().getOrders();

        // Bind UI components
        spinnerOrderNumber = findViewById(R.id.spinnerOrderNumber);
        listViewOrderSummary = findViewById(R.id.listViewOrderSummary); // ListView
        textViewOrderTotal = findViewById(R.id.textViewOrderTotal);
        buttonCancelOrder = findViewById(R.id.buttonCancelOrder);
        buttonExportOrders = findViewById(R.id.buttonExportOrders);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        // Initialize ListView and Spinner
        setUpListView();
        setUpSpinner();

        // Set OnClickListeners for buttons
        buttonCancelOrder.setOnClickListener(v -> cancelOrder());
        buttonExportOrders.setOnClickListener(v -> exportOrders());
    }

    private void setUpListView() {
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        listViewOrderSummary.setAdapter(listAdapter);
    }

    private void setUpSpinner() {
        // Ensure placedOrders is not null
        if (placedOrders == null) {
            placedOrders = new ArrayList<>();
        }

        // Prepare the list of order numbers
        List<Integer> orderNumbers = new ArrayList<>();
        for (Order order : placedOrders) {
            if (order != null) {
                orderNumbers.add(order.getNumber());
            }
        }

        // Check if there are no orders
        if (orderNumbers.isEmpty()) {
            Toast.makeText(this, "No orders available", Toast.LENGTH_SHORT).show();
            spinnerOrderNumber.setEnabled(false);
            spinnerOrderNumber.setAdapter(null); // Clear adapter
            return;
        }

        // Enable spinner if orders are available
        spinnerOrderNumber.setEnabled(true);

        // Create and set the spinner adapter
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, orderNumbers);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrderNumber.setAdapter(spinnerAdapter);

        spinnerOrderNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position >= 0 && position < orderNumbers.size()) {
                    int selectedOrderNumber = orderNumbers.get(position);
                    selectOrder(selectedOrderNumber);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No action needed
            }
        });

        // Select the first order by default if available
        if (!orderNumbers.isEmpty()) {
            selectOrder(orderNumbers.get(0));
        }
    }

    private void selectOrder(int orderNumber) {
        for (Order order : placedOrders) {
            if (order.getNumber() == orderNumber) {
                selectedOrder = order;
                break;
            }
        }
        updateOrderDetails();
    }

    private void updateOrderDetails() {
        if (selectedOrder != null && selectedOrder.getPizzas() != null) {
            List<String> pizzaDescriptions = new ArrayList<>();
            for (Pizza pizza : selectedOrder.getPizzas()) {
                pizzaDescriptions.add(String.valueOf(pizza));
            }
            listAdapter.clear();
            listAdapter.addAll(pizzaDescriptions);
            textViewOrderTotal.setText(String.format("$%.2f", selectedOrder.getTotal()));
        } else {
            listAdapter.clear();
            textViewOrderTotal.setText("");
        }
    }

    private void cancelOrder() {
        if (selectedOrder != null) {
            placedOrders.remove(selectedOrder);
            Toast.makeText(this, "Order canceled successfully!", Toast.LENGTH_SHORT).show();
            setUpSpinner(); // Refresh spinner after order removal
        } else {
            Toast.makeText(this, "No order selected!", Toast.LENGTH_SHORT).show();
        }
    }

    private void exportOrders() {
        if (placedOrders.isEmpty()) {
            Toast.makeText(this, "No orders to export!", Toast.LENGTH_SHORT).show();
            return;
        }

        File exportFile = new File(getExternalFilesDir(null), "orders.txt");

        try (FileOutputStream fos = new FileOutputStream(exportFile)) {
            for (Order order : placedOrders) {
                fos.write(String.format("Order Number: #%d%n", order.getNumber()).getBytes());
                fos.write("Pizzas:\n".getBytes());
                for (Pizza pizza : order.getPizzas()) {
                    fos.write(String.format("%s%n", pizza).getBytes());
                }
                fos.write(String.format("Total Price: $%.2f%n", order.getTotal()).getBytes());
                fos.write("-----------------------------------\n".getBytes());
            }
            Toast.makeText(this, "Orders exported to: " + exportFile.getPath(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(this, "Failed to export orders.", Toast.LENGTH_SHORT).show();
        }
    }
}
