package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private EditText mortgage;
    private EditText tenure;
    private EditText rate;
    private Button calculateButton;
    private Button clearButton;
    private TextView EMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mortgage = findViewById(R.id.mortgage);
        tenure = findViewById(R.id.tenure);
        rate = findViewById(R.id.percentageRate);
        calculateButton = findViewById(R.id.calculateButton);
        EMI = findViewById(R.id.EMI);
        clearButton = findViewById(R.id.clearButton);

        calculateButton.setOnClickListener(v -> {
            calculate();
        });
        clearButton.setOnClickListener(v -> {
            clear();

        });

    }
    private void calculate() {

        // Retrieve the input values as strings
        String mortgageInput = mortgage.getText().toString();
        String tenureInput = tenure.getText().toString();
        String rateInput = rate.getText().toString();

        // Check if any input fields are empty
        if (mortgageInput.isEmpty() || tenureInput.isEmpty() || rateInput.isEmpty()) {
            // Display an error message in the EMI TextView
            EMI.setText("ERROR MISSING INPUT");
            return;
        }

        try {
            double mortgageAmount = Double.parseDouble(mortgageInput);
            double tenureInMonths = Double.parseDouble(tenureInput);
            double annualInterestRate = Double.parseDouble(rateInput);

            // Calculate monthly interest rate
            double monthlyInterestRate = annualInterestRate / 12 / 100;

            // Calculate EMI using the formula
            double result = (mortgageAmount * monthlyInterestRate *
                    Math.pow(1 + monthlyInterestRate, tenureInMonths)) /
                    (Math.pow(1 + monthlyInterestRate, tenureInMonths) - 1);

            // Print result in editText
            EMI.setText("Monthly EMI: $" + String.format("%.2f", result));

        } catch (NumberFormatException e) {
            // Handle the case where parsing the input fails
            EMI.setText("ERROR WRONG INPUT");
        }
    }

    private void clear() {

        // Reset all the views
        mortgage.setText("");
        tenure.setText("");
        rate.setText("");
        EMI.setText("");


    }
}
