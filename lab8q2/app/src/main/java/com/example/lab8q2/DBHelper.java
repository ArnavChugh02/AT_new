package com.example.lab8q2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "ClinicDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE appointments(id TEXT, name TEXT, doctor TEXT, time TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS appointments");
        onCreate(db);
    }

    public boolean isSlotTaken(String doctor, String time) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM appointments WHERE doctor=? AND time=?", new String[]{doctor, time});
        return c.getCount() > 0;
    }

    public boolean bookAppointment(String id, String name, String doctor, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("name", name);
        cv.put("doctor", doctor);
        cv.put("time", time);
        long result = db.insert("appointments", null, cv);
        return result != -1;
    }
}
