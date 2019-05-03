package com.askew.sean.mealplanner.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MealContractDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Meals.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MealContract.MealEntry.TABLE_NAME + " (" +
                    MealContract.MealEntry._ID + " INTEGER PRIMARY KEY," +
                    MealContract.MealEntry.COLUMN_NAME_MEAL_NAME + " TEXT," +
                    MealContract.MealEntry.COLUMN_NAME_MEAL_TYPE + " TEXT," +
                    MealContract.MealEntry.COLUMN_NAME_APPETIZER + " TEXT," +
                    MealContract.MealEntry.COLUMN_NAME_ENTREE    + " TEXT," +
                    MealContract.MealEntry.COLUMN_NAME_SIDES     + " TEXT," +
                    MealContract.MealEntry.COLUMN_NAME_DESSERT   + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MealContract.MealEntry.TABLE_NAME;
    public MealContractDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
