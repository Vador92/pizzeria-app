package com.example.rupizzeriaapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class PizzaActivity extends AppCompatActivity {

    private RecyclerView pizzaRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizzas);

        pizzaRecyclerView = findViewById(R.id.pizzaRecyclerView);

        List<Pizza> pizzas = createPizzaList();
        PizzaAdapter adapter = new PizzaAdapter(this, pizzas);

        pizzaRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pizzaRecyclerView.setAdapter(adapter);
    }

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
