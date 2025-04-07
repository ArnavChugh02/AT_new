package com.example.app1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Final extends AppCompatActivity {
    private String selectedItem;
    private int quantity;
    private int totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_activity);

        TextView textViewDetails = findViewById(R.id.textViewDetails);
        Button finalButton = findViewById(R.id.buttonFinal);

        selectedItem = getIntent().getStringExtra("selectedItem");
        quantity = getIntent().getIntExtra("quantity", 0);
        totalPrice = getIntent().getIntExtra("totalPrice", 0);

        textViewDetails.setText("Item: " + selectedItem + "\nQuantity: " + quantity + "\nTotal Price: ₹" + totalPrice);

        finalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Final.this, "Final Order: " + selectedItem + " x" + quantity + "\nTotal: ₹" + totalPrice, Toast.LENGTH_LONG).show();
            }
        });
    }
}
