package com.example.ict3214_mobile_application_development_mini_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database ekei, Table ekei nam
    public static final String DATABASE_NAME = "FitnessApp.db";
    public static final String TABLE_NAME = "users";

    // Table eke Columns tika
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "PASSWORD";

    public static final String COL_5 = "HEIGHT";
    public static final String COL_6 = "WEIGHT";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Table eka create karana SQL query eka
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT, PASSWORD TEXT, HEIGHT TEXT, WEIGHT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Aluth user kenek save karana method eka (For Sign Up)
    public boolean insertData(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, email);
        contentValues.put(COL_4, password);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1; // -1 awoth save wela na, wena gank nam save wela
    }

    // Aluth screen eken ena height ekai weight ekai update karana method eka
    public boolean updateUserDetails(String email, String height, String weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_5, height);
        contentValues.put(COL_6, weight);

        // Email eka samanai nam witharak e userge row eka update karanawa
        int result = db.update(TABLE_NAME, contentValues, "EMAIL = ?", new String[]{email});
        return result > 0;
    }

    // Dashboard ekata userge wisthara ganna method eka
    public Cursor getUserDetails(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        // Email eka dunnama NAME, HEIGHT, WEIGHT gannawa
        return db.rawQuery("SELECT NAME, HEIGHT, WEIGHT FROM " + TABLE_NAME + " WHERE EMAIL = ?", new String[]{email});
    }

    //Email ekai Password ekai harida balana method eka (For Login)
    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE EMAIL = ? AND PASSWORD = ?", new String[]{email, password});
        return cursor.getCount() > 0; // count eka 0 ta wada wadi nam user innawa
    }
}