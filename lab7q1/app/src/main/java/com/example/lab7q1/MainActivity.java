package com.example.dbprac;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText rollNo, name, marks;
    Button btnAdd, btnUpdate, btnDelete, btnView;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);

        rollNo = findViewById(R.id.editRollNo);
        name = findViewById(R.id.editName);
        marks = findViewById(R.id.editMarks);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnView = findViewById(R.id.btnView);

        btnAdd.setOnClickListener(v -> {
            if(rollNo.getText().toString().isEmpty()){
                showToast("Roll no required");
                return;
            }
            if(!rollNo.getText().toString().trim().matches("\\d+")){
                showToast("Enter number");
                return;
            }
            boolean inserted = db.insertStudent(
                    rollNo.getText().toString(),
                    name.getText().toString(),
                    marks.getText().toString());
            showToast(inserted ? "Student Added" : "Insert Failed");
        });

        btnUpdate.setOnClickListener(v -> {
            boolean updated = db.updateStudent(
                    rollNo.getText().toString(),
                    name.getText().toString(),
                    marks.getText().toString());
            showToast(updated ? "Student Updated" : "Update Failed");
        });

        btnDelete.setOnClickListener(v -> {
            boolean deleted = db.deleteStudent(rollNo.getText().toString());
            showToast(deleted ? "Student Deleted" : "Delete Failed");
        });

        btnView.setOnClickListener(v -> {
            Cursor res = db.getAllStudents();
            if (res.getCount() == 0) {
                showMessage("No Records", "No student found!");
                return;
            }

            StringBuilder buffer = new StringBuilder();
            while (res.moveToNext()) {
                buffer.append("Roll No: ").append(res.getString(0)).append("\n");
                buffer.append("Name: ").append(res.getString(1)).append("\n");
                buffer.append("Marks: ").append(res.getString(2)).append("\n\n");
            }
            showMessage("Student Records", buffer.toString());
        });
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void showMessage(String title, String msg) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("OK", null)
                .show();
    }
}
