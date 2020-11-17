package com.example.moneytracker50;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnIncome, btnExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIncome = findViewById(R.id.btnIncome);
        btnIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SaveActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        btnExpense = findViewById(R.id.btnExpense);
        btnExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SaveActivity.class);
                v.getContext().startActivity(intent);
            }
        });

    }
}