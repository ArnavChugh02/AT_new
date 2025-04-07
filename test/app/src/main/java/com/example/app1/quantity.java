package com.example.app1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class quantity extends AppCompatActivity {

    private String selectedItem;
    private int itemPrice;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quantity);

        TextView textView = findViewById(R.id.textViewItem);
        RadioGroup radioGroup = findViewById(R.id.radioGroupQuantity);
        Button submitButton = findViewById(R.id.btnSubmitQuantity);
        progressBar = findViewById(R.id.progressBarQuantity);
        progressBar.setVisibility(View.GONE);

        selectedItem = getIntent().getStringExtra("selectedItem");
        itemPrice = getIntent().getIntExtra("itemPrice", 0);

        textView.setText("Select quantity for: " + selectedItem);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(quantity.this, "Please select a quantity!", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);

                    new Handler().postDelayed(() -> {
                        RadioButton selectedRadioButton = findViewById(selectedId);
                        int quantity = Integer.parseInt(selectedRadioButton.getText().toString());
                        int totalPrice = itemPrice * quantity;

                        Intent intent = new Intent(quantity.this, Final.class);
                        intent.putExtra("selectedItem", selectedItem);
                        intent.putExtra("quantity", quantity);
                        intent.putExtra("totalPrice", totalPrice);
                        startActivity(intent);

                        progressBar.setVisibility(View.GONE); // Hide spinner after navigation
                    }, 1000); // Simulating a small delay
                }
            }
        });
    }
}
