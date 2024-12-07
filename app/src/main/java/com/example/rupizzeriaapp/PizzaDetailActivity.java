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

/**
 * This is the Pizza Detail Activity class, it handles the detailed information page of the selected pizza
 * @author Varun Doreswamy, Yuet Yue
 */
public class PizzaDetailActivity extends AppCompatActivity {

    // Instance Variables
    private Pizza selectedPizza;
    private TextView pizzaName, pizzaPrice, pizzaCrust;
    private ImageView pizzaImage;
    private Spinner sizeSpinner;
    private LinearLayout toppingsContainer;

    /**
     * This method initializes the Pizza Detail Activity by:
     * Setting up UI components
     * Retrieving the selected Pizza from previous activity
     * Giving users the ability to customize their selected Pizza
     * @param savedInstanceState is the previous saved state of activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_detail);

        // Initialize UI components
        initializeUIComponents();

        // Set up the back button listener
        setupBackButton();

        // Retrieve and validate the selected pizza from Intent
        if (!retrieveSelectedPizza()) {
            finish();
            return;
        }

        // Display pizza details
        displayPizzaDetails();

        // Set up the size spinner
        setupSizeSpinner();

        // Initialize toppings selection
        initializeToppings();

        // Set up the "Add to Cart" button
        setupAddToCartButton();
    }

    /**
     * This method initializes UI components by linking them to their corresponding views
     */
    private void initializeUIComponents() {
        pizzaName = findViewById(R.id.pizzaName);
        pizzaPrice = findViewById(R.id.pizzaPrice);
        pizzaCrust = findViewById(R.id.pizzaCrust);
        pizzaImage = findViewById(R.id.pizzaImage);
        sizeSpinner = findViewById(R.id.sizeSpinner);
        toppingsContainer = findViewById(R.id.toppingsContainer);
    }

    /**
     * This method sets up the back button to finish the activity when clicked
     */
    private void setupBackButton() {
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());
    }

    /**
     * This method retrieves the selected pizza passed via the Intent and validates it
     *
     * @return true if the pizza is successfully retrieved, false otherwise.
     */
    private boolean retrieveSelectedPizza() {
        selectedPizza = (Pizza) getIntent().getSerializableExtra("selectedPizza");
        return selectedPizza != null;
    }

    /**
     * This method displays the details of the selected pizza, such as its name, crust, image, and price
     */
    private void displayPizzaDetails() {
        String style = selectedPizza.toString().contains("Chicago") ? "Chicago Style" : "NY Style";
        String type = selectedPizza.toString().split(" ")[0]; // Extract the first word (e.g., Deluxe)
        pizzaName.setText(String.format("%s %s", style, type));
        pizzaCrust.setText(String.format("Crust: %s", selectedPizza.getCrust()));
        pizzaImage.setImageResource(getPizzaImage(type, style));
        updatePizzaPrice();
    }

    /**
     * This method sets up the size spinner to allow size selection and update the pizza price accordingly
     */
    private void setupSizeSpinner() {
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, new String[]{"SMALL", "MEDIUM", "LARGE"});
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(sizeAdapter);

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
    }

    /**
     * This method sets up the "Add to Cart" button to add the selected pizza to the current order
     */
    private void setupAddToCartButton() {
        Button addToCartButton = findViewById(R.id.addToCartButton);
        addToCartButton.setOnClickListener(v -> {
            OrderManager.getInstance().getCurrentOrder().addPizza(selectedPizza);

            new AlertDialog.Builder(this)
                    .setTitle("Added to Cart")
                    .setMessage("Pizza added to your current order.")
                    .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                    .show();
        });
    }

    /**
     * This method initializes the toppings UI with checkboxes for each topping
     */
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

    /**
     * This method updates the pizza price based on the current size and selected toppings
     */
    private void updatePizzaPrice() {
        pizzaPrice.setText(String.format("$%.2f", selectedPizza.price()));
    }

    /**
     * This is the getter method for the pizza image based on the pizza type
     * @param type is the type of the pizza
     * @param style is the style of the pizza (NY or Chicago)
     * @return the image based on the type and style of the pizza
     */
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
