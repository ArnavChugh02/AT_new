package com.example.lab8q2;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText etId, etName;
    Spinner spDoctor, spTime;
    Button btnBook;
    DBHelper db;
    String[] doctors = {"Dr. Smith", "Dr. John", "Dr. Jane"};
    String[] times = {"10:00 AM", "11:00 AM", "12:00 PM", "2:00 PM"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        spDoctor = findViewById(R.id.spDoctor);
        spTime = findViewById(R.id.spTime);
        btnBook = findViewById(R.id.btnBook);
        db = new DBHelper(this);

        ArrayAdapter<String> docAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, doctors);
        spDoctor.setAdapter(docAdapter);

        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, times);
        spTime.setAdapter(timeAdapter);

        btnBook.setOnClickListener(v -> {
            String id = etId.getText().toString();
            String name = etName.getText().toString();
            String doctor = spDoctor.getSelectedItem().toString();
            String time = spTime.getSelectedItem().toString();

            if (id.isEmpty() || name.isEmpty()) {
                Toast.makeText(this, "Please enter ID and name", Toast.LENGTH_SHORT).show();
                return;
            }

            if (db.isSlotTaken(doctor, time)) {
                Toast.makeText(this, "Slot already booked for " + doctor + " at " + time, Toast.LENGTH_LONG).show();
            } else {
                boolean booked = db.bookAppointment(id, name, doctor, time);
                if (booked)
                    Toast.makeText(this, "Appointment Booked!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Booking Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
