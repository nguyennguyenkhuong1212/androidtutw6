package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void insert(String country, String currency) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.COUNTRY, country);
        contentValue.put(DatabaseHelper.CURRENCY, currency);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public int update(long id, String newCountry, String newCurrency) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COUNTRY,newCountry);
        contentValues.put(DatabaseHelper.CURRENCY,newCurrency);
        return database.update(DatabaseHelper.TABLE_NAME,
                contentValues,
                DatabaseHelper.ID + " = " + id,
                null);
    }

    public void delete(long id) {
        database.delete(DatabaseHelper.TABLE_NAME,
                DatabaseHelper.ID + "=" + id,
                null);
    }
    public Cursor fetch() {
        String[] columns = new String[]{
                DatabaseHelper.ID,
                DatabaseHelper.COUNTRY,
                DatabaseHelper.CURRENCY};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}
