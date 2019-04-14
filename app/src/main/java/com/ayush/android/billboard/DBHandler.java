package com.ayush.android.billboard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "expenseManager";
    public static final String TABLE_EXPENSE = "tbExpenses";
    private static final String KEY_TID = "tid";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_PRICE = "price";
    private static String KEY_TIMESTAMP = null;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EXPENSE_TABLE = " CREATE TABLE " + TABLE_EXPENSE + "("
                + KEY_TID + " INTEGER PRIMARY KEY, " + KEY_CATEGORY + " TEXT,"
                + KEY_PRICE + " INTEGER " + KEY_TIMESTAMP + " CURRENT_TIMESTAMP " + ")";
        db.execSQL(CREATE_EXPENSE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE);
        onCreate(db);
    }

    void addExpense (Expenses expenses) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TID, expenses.getTid());
        values.put(KEY_CATEGORY, expenses.getCategory());
        values.put(KEY_PRICE, expenses.getPrice());

        db.insert(TABLE_EXPENSE, null, values);
        db.close();
    }

    public List<Expenses> getAllExpenses () {
        List<Expenses> expensesList = new ArrayList<Expenses>();
        String selectQuery = "SELECT * FROM " + TABLE_EXPENSE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Expenses expenses = new Expenses();
                expenses.setTid(Integer.parseInt(cursor.getString(0)));
                expenses.setCategory(cursor.getString(1));
                expenses.setPrice(Integer.parseInt(cursor.getString(2)));
                // Adding contact to list
                expensesList.add(expenses);
            } while (cursor.moveToNext());
        }

        db.close();
        return expensesList;
    }
}
