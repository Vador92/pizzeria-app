package com.example.rupizzeriaapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity {

    private Spinner spinnerOrderNumber;
    private RecyclerView recyclerViewOrderSummary;
    private TextView textViewOrderTotal;
    private Button buttonCancelOrder, buttonExportOrders;

    private ArrayAdapter<String> spinnerAdapter;
    private OrderSummaryAdapter recyclerAdapter;

    private List<Order> placedOrders;
    private Order selectedOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        // Bind UI components
        spinnerOrderNumber = findViewById(R.id.spinnerOrderNumber);
        recyclerViewOrderSummary = findViewById(R.id.recyclerViewOrderSummary);
        textViewOrderTotal = findViewById(R.id.textViewOrderTotal);
        buttonCancelOrder = findViewById(R.id.buttonCancelOrder);
        buttonExportOrders = findViewById(R.id.buttonExportOrders);

        // Initialize the Back Button
        Button backButton = findViewById(R.id.backButton);

        // Set OnClickListener for the Back Button
        backButton.setOnClickListener(v -> finish());

        // Retrieve placed orders
        placedOrders = OrderManager.getInstance().getOrders();

        // Populate Spinner
        initializeOrderSpinner();

        // Set up RecyclerView
        recyclerAdapter = new OrderSummaryAdapter(new ArrayList<>());
        recyclerViewOrderSummary.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewOrderSummary.setAdapter(recyclerAdapter);

        // Handle Spinner item selection
        spinnerOrderNumber.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                if (position == 0) { // "None" selected
                    selectedOrder = null;
                    updateUIForNoOrder();
                } else {
                    selectedOrder = placedOrders.get(position - 1);
                    updateUIForSelectedOrder();
                }
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
            }
        });

        // Handle Cancel Order button
        buttonCancelOrder.setOnClickListener(v -> {
            if (selectedOrder != null) {
                placedOrders.remove(selectedOrder);
                Toast.makeText(this, "Order canceled successfully!", Toast.LENGTH_SHORT).show();
                initializeOrderSpinner(); // Refresh Spinner
            } else {
                Toast.makeText(this, "No order selected to cancel!", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle Export Orders button
        buttonExportOrders.setOnClickListener(v -> {
            if (!placedOrders.isEmpty()) {
                if (exportOrdersToFile()) {
                    Toast.makeText(this, "Orders exported successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed to export orders!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "No orders to export!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initializeOrderSpinner() {
        List<String> orderNumbers = new ArrayList<>();
        if (placedOrders.isEmpty()) {
            spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, orderNumbers);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerOrderNumber.setAdapter(spinnerAdapter);

            updateUIForNoOrder();
            return;
        }

        for (Order order : placedOrders) {
            orderNumbers.add("Order #" + order.getNumber());
        }

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, orderNumbers);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrderNumber.setAdapter(spinnerAdapter);
        spinnerOrderNumber.setSelection(0); // Ensure spinner selects the first order
        selectedOrder = placedOrders.get(0);
        updateUIForSelectedOrder();
    }

    private void updateUIForNoOrder() {
        recyclerAdapter.updateOrderSummary(new ArrayList<>());
        textViewOrderTotal.setText("0.00");
        buttonCancelOrder.setEnabled(false);
    }

    private void updateUIForSelectedOrder() {
        if (selectedOrder != null) {
            recyclerAdapter.updateOrderSummary(selectedOrder.getPizzas());
            textViewOrderTotal.setText(String.format("%.2f", selectedOrder.getTotal()));
            buttonCancelOrder.setEnabled(true);
        }
    }


    private boolean exportOrdersToFile() {
        StringBuilder exportData = new StringBuilder();
        for (Order order : placedOrders) {
            exportData.append("Order #").append(order.getNumber()).append("\n");
            for (Pizza pizza : order.getPizzas()) {
                exportData.append(" - ").append(pizza.toString()).append("\n");
            }
            exportData.append("Total: $").append(String.format("%.2f", order.getTotal())).append("\n\n");
        }

        File exportFile = new File(getFilesDir(), "order_history.txt");

        try (FileOutputStream fos = new FileOutputStream(exportFile)) {
            fos.write(exportData.toString().getBytes());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
