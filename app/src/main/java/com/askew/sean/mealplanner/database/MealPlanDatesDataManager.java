package com.askew.sean.mealplanner.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.askew.sean.mealplanner.database.DailyMealContract.DailyMealEntry;

import java.util.ArrayList;
import java.util.List;

public class MealPlanDatesDataManager {
    MealPlanDatesContractDbHelper mealPlanDatesContractDbHelper;
    List<String> mealPlanDatesList;
    Context mContext;
    SQLiteDatabase readDb;

    public MealPlanDatesDataManager(Context context) {
        this.mContext = context;
        mealPlanDatesContractDbHelper = new MealPlanDatesContractDbHelper(context);

    }

    private long insert(String mealDate) {
        SQLiteDatabase db = mealPlanDatesContractDbHelper.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(MealPlanDatesContract.MealPlanDatesEntry.COLUMN_NAME_DATE, mealDate);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(MealPlanDatesContract.MealPlanDatesEntry.TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    public long store(String mealPlanDate) {

        return insert(mealPlanDate);
    }

    public void store(List<String> mealPlanDatesList){
        for(String date : mealPlanDatesList) {
            store(date);
        }
    }

    private Cursor read(String column, String[] values) {

        String[] projection = null;
        String selection = null;
        if (column != null) {
            // Filter results WHERE "title" = 'My Title'
            selection = new String(column + " = ?");
        }
        String[] selectionArgs = values;

        String sortOrder =
                DailyMealEntry.COLUMN_NAME_DATE + " DESC";

        Cursor cursor = readDb.query(
                MealPlanDatesContract.MealPlanDatesEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder);             // The sort order
        return cursor;
    }

    public String get(String date){

        String[] values = new String[1];
        values[0]=date;
        List<String> mealPlanDates = load(DailyMealEntry.COLUMN_NAME_DATE, values);
        String mealPlanDate = null;
        if (mealPlanDates.size() != 0)
            mealPlanDate = mealPlanDates.get(0);
        return mealPlanDate;
    }

    public List<String> load(String column, String[] values) {
        readDb = mealPlanDatesContractDbHelper.getReadableDatabase();
        List<String> mealPlanDatesPlansList = new ArrayList<>();
        Cursor cursor = read(column, values);
        while (cursor.moveToNext()) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(MealPlanDatesContract.MealPlanDatesEntry._ID));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(MealPlanDatesContract.MealPlanDatesEntry.COLUMN_NAME_DATE));
            mealPlanDatesPlansList.add(date);
        }
        cursor.close();
        readDb.close();
        return mealPlanDatesPlansList;
    }

    public void removeAll(){
           int numRowsDeleted =  remove(null,null);
    }

    public int remove(String column, String[] values) {
        readDb = mealPlanDatesContractDbHelper.getWritableDatabase();
        // Define 'where' part of query.
        String selection = column;
        if (column != null) {
            selection = column + " LIKE ?";
        }
        // Specify arguments in placeholder order.
        String[] selectionArgs = values;

        int deletedRows = readDb.delete(MealPlanDatesContract.MealPlanDatesEntry.TABLE_NAME, selection, selectionArgs);
        readDb.close();
        return deletedRows;
    }

   /* public List<DailyMealPlan> getRandomList(List<Date> date) {
        MealDataManager mealDataManager = new MealDataManager(mContext);

        List<Meal> breakfast = mealDataManager.getRandomMealList(FoodType.BREAKFAST, date);
        List<Meal> lunch = mealDataManager.getRandomMealList(FoodType.LUNCH, date);
        List<Meal> dinner = mealDataManager.getRandomMealList(FoodType.DINNER, date);

        mealPlanDatesPlansList = new ArrayList<>();
        for (int i = 0; i < date.size(); i++) {
            mealPlanDatesPlansList.add(i, new MealPlanDatesPlan(breakfast.get(i).getMealName(),
                    lunch.get(i).getMealName(),
                    dinner.get(i).getMealName(),
                    date.get(i)));
        }

        return mealPlanDatesPlansList;

    }

    public void setInUse(List<MealPlanDatesPlan> dailyMealPlanList) {
        MealDataManager mealDataManager = new MealDataManager(mContext);
        for (DailyMealPlan dailyMealPlan : dailyMealPlanList) {
            dailyMealPlan.setInUse(true);
        }
    }*/
}
