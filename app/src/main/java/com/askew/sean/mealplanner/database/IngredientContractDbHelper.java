package com.askew.sean.mealplanner.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class IngredientContractDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Ingredients.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + IngredientContract.IngredientEntry.TABLE_NAME + "(" +
                    IngredientContract.IngredientEntry._ID + " INTEGER PRIMARY KEY," +
                    IngredientContract.IngredientEntry.COLUMN_NAME_NAME + " TEXT," +
                    IngredientContract.IngredientEntry.COLUMN_NAME_DATA + " TEXT)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + IngredientContract.IngredientEntry.TABLE_NAME;

    public IngredientContractDbHelper(Context context) {
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