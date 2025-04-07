package com.example.lab7q1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "StudentDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE student(rollno VARCHAR PRIMARY KEY, name VARCHAR, marks VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS student");
        onCreate(db);
    }

    public boolean insertStudent(String rollno, String name, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("rollno", rollno);
        cv.put("name", name);
        cv.put("marks", marks);
        long result = db.insert("student", null, cv);
        return result != -1;
    }

    public boolean updateStudent(String rollno, String name, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("marks", marks);
        int result = db.update("student", cv, "rollno=?", new String[]{rollno});
        return result > 0;
    }

    public boolean deleteStudent(String rollno) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("student", "rollno=?", new String[]{rollno});
        return result > 0;
    }

    public Cursor getAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM student", null);
    }
}
