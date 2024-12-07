package com.example.rupizzeriaapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the Pizza Activity class, which manages the frontend responses on the Pizza Options Page
 * This class changes the data shown in the frontend based on the backend data processing
 * @author Varun Doreswamy, Yuet Yue
 */
public class PizzaActivity extends AppCompatActivity {

    // Instance Variables
    private RecyclerView pizzaRecyclerView;

    /**
     * This method initializes the list of pizza types in a RecyclerView for users to see all pizza types
     * @param savedInstanceState is the previous saved state of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizzas);

        pizzaRecyclerView = findViewById(R.id.pizzaRecyclerView);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(PizzaActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Optional: Finish the current activity
        });

        List<Pizza> pizzas = createPizzaList();
        PizzaAdapter adapter = new PizzaAdapter(this, pizzas);

        pizzaRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pizzaRecyclerView.setAdapter(adapter);
    }

    /**
     * This helper method adds the pizza types to a list that will be later initialized by another method
     * @return the pizzas within the list
     */
    private List<Pizza> createPizzaList() {
        List<Pizza> pizzas = new ArrayList<>();
        PizzaFactory chicagoFactory = new ChicagoPizza();
        PizzaFactory nyFactory = new NYPizza();

        pizzas.add(chicagoFactory.createDeluxe());
        pizzas.add(chicagoFactory.createMeatzza());
        pizzas.add(chicagoFactory.createBBQChicken());
        pizzas.add(chicagoFactory.createBuildYourOwn());
        pizzas.add(nyFactory.createDeluxe());
        pizzas.add(nyFactory.createMeatzza());
        pizzas.add(nyFactory.createBBQChicken());
        pizzas.add(nyFactory.createBuildYourOwn());

        return pizzas;
    }
}
