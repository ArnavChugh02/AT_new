package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SecondPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.second);

        TextView textView = findViewById(R.id.textView);

        String name = getIntent().getStringExtra("name");
        String option = getIntent().getStringExtra("option");

        textView.setText("Name:" + name + " Selected Option: "+option);

    }

}
