package com.example.lab8q1;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText etName, etPrice;
    Button btnAdd, btnSummary;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.etName);
        etPrice = findViewById(R.id.etPrice);
        btnAdd = findViewById(R.id.btnAdd);
        btnSummary = findViewById(R.id.btnSummary);
        db = new DBHelper(this);

        btnAdd.setOnClickListener(v -> {
            String name = etName.getText().toString();
            int price = Integer.parseInt(etPrice.getText().toString());

            boolean inserted = db.insertProduct(name, price);
            if (inserted) {
                Toast.makeText(this, "Product added", Toast.LENGTH_SHORT).show();
                etName.setText(""); etPrice.setText("");
            } else {
                Toast.makeText(this, "Failed to add", Toast.LENGTH_SHORT).show();
            }
        });

        btnSummary.setOnClickListener(v -> {
            Cursor summary = db.getSummary();
            Cursor products = db.getAllProducts();

            if (summary.moveToFirst()) {
                int count = summary.getInt(0);
                int total = summary.getInt(1);
                int max = summary.getInt(2);
                int min = summary.getInt(3);

                StringBuilder sb = new StringBuilder();
                sb.append("Total Products: ").append(count).append("\n")
                        .append("Total Price: ").append(total).append("\n")
                        .append("Max Price: ").append(max).append("\n")
                        .append("Min Price: ").append(min).append("\n\n");

                while (products.moveToNext()) {
                    sb.append("Name: ").append(products.getString(0))
                            .append(", Price: ").append(products.getInt(1)).append("\n");
                }

                new AlertDialog.Builder(this)
                        .setTitle("Product Summary")
                        .setMessage(sb.toString())
                        .setPositiveButton("OK", null)
                        .show();
            }
        });
    }
}
