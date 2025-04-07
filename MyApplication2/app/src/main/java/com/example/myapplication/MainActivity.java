package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TableLayout tableLayout = findViewById(R.id.table);
        String[] days = {"M", "T", "W", "T", "F"};
        String[] highs = {"34°C", "35°C", "34°C", "35°C", "33°C"};
        String[] lows = {"28°C", "27°C", "29°C", "26°C", "29°C"};

        TableRow titleRow = new TableRow(this);
        TextView title = new TextView(this);
        title.setText("Weather report");
        title.setTextColor(Color.WHITE);
        title.setTextSize(20);
        title.setPadding(10, 10, 10,10);
        titleRow.addView(title);
        tableLayout.addView(titleRow);

        TableRow daysRow = new TableRow(this);
        for (String day : days) {
            TextView dayView = createTextView(day);
            daysRow.addView(dayView);
        }
        tableLayout.addView(daysRow);


        TableRow highRow = new TableRow(this);
        for (String high : highs) {
            TextView highView = createTextView(high);
            highRow.addView(highView);
        }
        tableLayout.addView(highRow);

        TableRow lowRow = new TableRow(this);
        for (String low : lows) {
            TextView lowView = createTextView(low);
            lowRow.addView(lowView);
        }
        tableLayout.addView(lowRow);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
    private TextView createTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(16);
        textView.setPadding(20, 10, 20, 10);
        return textView;
    }
}