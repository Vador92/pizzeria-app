package com.example.rupizzeriaapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PizzaDetailActivity extends AppCompatActivity {

    private Pizza selectedPizza;
    private TextView pizzaName, pizzaPrice, pizzaCrust;
    private ImageView pizzaImage;
    private Spinner sizeSpinner;
    private LinearLayout toppingsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_detail);

        // Bind UI components
        pizzaName = findViewById(R.id.pizzaName);
        pizzaPrice = findViewById(R.id.pizzaPrice);
        pizzaCrust = findViewById(R.id.pizzaCrust);
        pizzaImage = findViewById(R.id.pizzaImage);
        sizeSpinner = findViewById(R.id.sizeSpinner);
        toppingsContainer = findViewById(R.id.toppingsContainer);
        Button addToCartButton = findViewById(R.id.addToCartButton);

        // Initialize the Back Button
        Button backButton = findViewById(R.id.backButton);

        // Set OnClickListener for the Back Button
        backButton.setOnClickListener(v -> finish());

        // Get the selected pizza from Intent
        selectedPizza = (Pizza) getIntent().getSerializableExtra("selectedPizza");
        if (selectedPizza == null) {
            finish();
            return;
        }

        // Set pizza details
        String style = selectedPizza.toString().contains("Chicago") ? "Chicago Style" : "NY Style";
        String type = selectedPizza.toString().split(" ")[0]; // Extract the first word (e.g., Deluxe)
        pizzaName.setText(String.format("%s %s", style, type));
        pizzaCrust.setText(String.format("Crust: %s", selectedPizza.getCrust()));
        pizzaImage.setImageResource(getPizzaImage(type, style));
        updatePizzaPrice();

        // Setup size spinner
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, new String[]{"SMALL", "MEDIUM", "LARGE"});
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(sizeAdapter);

        // Handle size changes
        sizeSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                String size = parent.getItemAtPosition(position).toString();
                switch (size) {
                    case "SMALL":
                        selectedPizza.setSize(Size.SMALL);
                        break;
                    case "MEDIUM":
                        selectedPizza.setSize(Size.MEDIUM);
                        break;
                    case "LARGE":
                        selectedPizza.setSize(Size.LARGE);
                        break;
                }
                updatePizzaPrice();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
            }
        });

        // Initialize toppings
        initializeToppings();

        // Handle "Add to Cart" button
        addToCartButton.setOnClickListener(v -> {
            // Add the pizza to the current order
            OrderManager.getInstance().getCurrentOrder().addPizza(selectedPizza);

            // Show confirmation
            new AlertDialog.Builder(this)
                    .setTitle("Added to Cart")
                    .setMessage("Pizza added to your current order.")
                    .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                    .show();
        });
    }

    private void initializeToppings() {
        toppingsContainer.removeAllViews();
        for (Topping topping : Topping.values()) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(topping.toString());
            checkBox.setEnabled(selectedPizza instanceof BuildYourOwn);
            checkBox.setChecked(selectedPizza.getToppings().contains(topping));

            // Handle toppings selection for BuildYourOwn
            if (selectedPizza instanceof BuildYourOwn) {
                checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    if (isChecked) {
                        int oldSize = selectedPizza.getToppings().size();
                        selectedPizza.addTopping(topping);

                        // If topping wasn't added, show error dialog
                        if (selectedPizza.getToppings().size() == oldSize) {
                            checkBox.setChecked(false); // Undo the check
                            new AlertDialog.Builder(PizzaDetailActivity.this)
                                    .setTitle("Limit Reached")
                                    .setMessage("You can select up to 7 toppings.")
                                    .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                                    .show();
                        }
                    } else {
                        selectedPizza.removeTopping(topping);
                    }
                    updatePizzaPrice();
                });
            }

            // Add the checkbox to the container
            toppingsContainer.addView(checkBox);
        }
    }

    private void updatePizzaPrice() {
        pizzaPrice.setText(String.format("$%.2f", selectedPizza.price()));
    }

    private int getPizzaImage(String type, String style) {
        if (style.equals("Chicago Style")) {
            switch (type) {
                case "Deluxe":
                    return R.drawable.chicagodeluxe;
                case "Meatzza":
                    return R.drawable.chicagomeatzza;
                case "BBQChicken":
                    return R.drawable.chicagobbqchicken;
                default:
                    return R.drawable.chicagobuildyourown;
            }
        } else { // Assume NY Style
            switch (type) {
                case "Deluxe":
                    return R.drawable.nydeluxe;
                case "Meatzza":
                    return R.drawable.nymeatzza;
                case "BBQChicken":
                    return R.drawable.nybbqchicken;
                default:
                    return R.drawable.nybuildyourown;
            }
        }
    }
}
