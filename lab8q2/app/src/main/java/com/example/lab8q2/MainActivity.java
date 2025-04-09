package com.example.clinicapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText etId, etName;
    Spinner spDoctor, spTime;
    Button btnBook, btnDate;
    TextView tvDate;
    DBHelper db;
    String selectedDate = "";

    String[] doctors = {"Dr. Smith", "Dr. Alice", "Dr. Bob"};
    String[] times = {"10:00 AM", "11:00 AM", "2:00 PM", "3:00 PM"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        spDoctor = findViewById(R.id.spDoctor);
        spTime = findViewById(R.id.spTime);
        btnBook = findViewById(R.id.btnBook);
        btnDate = findViewById(R.id.btnDate);
        tvDate = findViewById(R.id.tvDate);

        db = new DBHelper(this);

        spDoctor.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, doctors));
        spTime.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, times));

        btnDate.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int y = c.get(Calendar.YEAR), m = c.get(Calendar.MONTH), d = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dp = new DatePickerDialog(this, (view, year, month, day) -> {
                selectedDate = day + "/" + (month + 1) + "/" + year;
                tvDate.setText("Selected Date: " + selectedDate);
            }, y, m, d);
            dp.show();
        });

        btnBook.setOnClickListener(v -> {
            String id = etId.getText().toString().trim();
            String name = etName.getText().toString().trim();
            String doctor = spDoctor.getSelectedItem().toString();
            String time = spTime.getSelectedItem().toString();

            if (id.isEmpty() || name.isEmpty() || selectedDate.isEmpty()) {
                Toast.makeText(this, "Please fill all fields and pick a date", Toast.LENGTH_SHORT).show();
                return;
            }

            if (db.isSlotTaken(doctor, selectedDate, time)) {
                Toast.makeText(this, "Slot already booked for " + doctor + " at " + time + " on " + selectedDate, Toast.LENGTH_LONG).show();
            } else {
                boolean booked = db.bookAppointment(id, name, doctor, selectedDate, time);
                Toast.makeText(this, booked ? "Appointment Booked!" : "Booking Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
