package com.example.lab9q2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etPassenger;
    Spinner spinnerFrom, spinnerTo;
    RadioGroup classGroup;
    Button btnReserve;

    String[] cities = {"Delhi", "Mumbai", "Chennai", "Kolkata", "Bangalore"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPassenger = findViewById(R.id.etPassenger);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        classGroup = findViewById(R.id.classGroup);
        btnReserve = findViewById(R.id.btnReserve);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        btnReserve.setOnClickListener(v -> {
            String name = etPassenger.getText().toString().trim();
            int classId = classGroup.getCheckedRadioButtonId();

            if (name.isEmpty() || classId == -1) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Missing Info")
                        .setMessage("Please enter passenger name and select travel class.")
                        .setPositiveButton("OK", null)
                        .show();
                return;
            }

            String from = spinnerFrom.getSelectedItem().toString();
            String to = spinnerTo.getSelectedItem().toString();
            String travelClass = ((RadioButton) findViewById(classId)).getText().toString();

            String message = "Ticket Reserved!\n\n"
                    + "Passenger: " + name + "\n"
                    + "From: " + from + "\n"
                    + "To: " + to + "\n"
                    + "Class: " + travelClass;

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Reservation Confirmed")
                    .setMessage(message)
                    .setPositiveButton("OK", null)
                    .show();
        });
    }
}
