package com.askew.sean.mealplanner.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MealPlanDatesContractDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "MealPlan.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MealPlanDatesContract.MealPlanDatesEntry.TABLE_NAME + " (" +
                    DailyMealContract.DailyMealEntry._ID + " INTEGER PRIMARY KEY," +
                    DailyMealContract.DailyMealEntry.COLUMN_NAME_DATE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MealPlanDatesContract.MealPlanDatesEntry.TABLE_NAME;
    public MealPlanDatesContractDbHelper(Context context) {
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
