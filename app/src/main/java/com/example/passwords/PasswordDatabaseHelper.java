package com.example.passwords;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PasswordDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "password.db";
    public static final String TABLE = "password";
    public static final String _ID = "_id";
    public static final String COLUMN_WEBSITE = "website";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    private static final int VERSION = 2;

    public PasswordDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE +
                "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_WEBSITE + " TEXT" + ", "
                + COLUMN_USERNAME + " TEXT" + ", "
                + COLUMN_PASSWORD + " TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }

    public boolean deleteAllEntries(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE);
        return true;
    }
}
