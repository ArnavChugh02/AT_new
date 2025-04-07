package com.example.lab9q1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge;
    RadioGroup genderGroup, q1Group;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        genderGroup = findViewById(R.id.genderGroup);
        q1Group = findViewById(R.id.q1Group);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String age = etAge.getText().toString().trim();
            int genderId = genderGroup.getCheckedRadioButtonId();
            int q1Id = q1Group.getCheckedRadioButtonId();

            if (name.isEmpty() || age.isEmpty() || genderId == -1 || q1Id == -1) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Incomplete Form")
                        .setMessage("Please complete all fields in the survey.")
                        .setPositiveButton("OK", null)
                        .show();
                return;
            }

            RadioButton genderBtn = findViewById(genderId);
            RadioButton q1Btn = findViewById(q1Id);

            String gender = genderBtn.getText().toString();
            String answer = q1Btn.getText().toString();

            // Creating AlertDialog to show results
            String message = "Thank you " + name + "!\n\n"
                    + "Age: " + age + "\n"
                    + "Gender: " + gender + "\n"
                    + "Q1 Answer: " + answer;

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Survey Submitted")
                    .setMessage(message)
                    .setPositiveButton("OK", null)
                    .show();
        });
    }
}
