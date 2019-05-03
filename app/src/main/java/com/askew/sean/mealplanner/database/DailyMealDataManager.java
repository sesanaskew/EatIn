package com.askew.sean.mealplanner.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.askew.sean.mealplanner.database.DailyMealContract.DailyMealEntry;
import com.askew.sean.mealplanner.model.DailyMealPlan;
import com.askew.sean.mealplanner.model.FoodType;
import com.askew.sean.mealplanner.model.Meal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DailyMealDataManager {
    DailyMealContractDbHelper dailyMealContractDbHelper;
    List<DailyMealPlan> dailyMealPlansList;
    Context mContext;
    SQLiteDatabase readDb;

    public DailyMealDataManager(Context context) {
        this.mContext = context;
        dailyMealContractDbHelper = new DailyMealContractDbHelper(context);

    }

    private long insert(String mealDate,
                        String breakfast,
                        String lunch,
                        String dinner) {
        SQLiteDatabase db = dailyMealContractDbHelper.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(DailyMealEntry.COLUMN_NAME_DATE, mealDate);
        values.put(DailyMealEntry.COLUMN_NAME_BREAKFAST, breakfast);
        values.put(DailyMealEntry.COLUMN_NAME_LUNCH, lunch);
        values.put(DailyMealEntry.COLUMN_NAME_DINNER, dinner);


        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DailyMealEntry.TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    public long store(DailyMealPlan dailyMealPlan) {

        return insert(dailyMealPlan.getUseDateString(),
                dailyMealPlan.getBreakfast(),
                dailyMealPlan.getLunch(),
                dailyMealPlan.getDinner());
    }

    public void store(List<DailyMealPlan> dailyMealPlanList){
        for(DailyMealPlan dailyMealPlan : dailyMealPlanList) {
            String[] values = new String[1];
            values[0] = dailyMealPlan.getUseDateString();
            List<DailyMealPlan> dailyMealEntries = load(DailyMealEntry.COLUMN_NAME_DATE, values);
            if (dailyMealEntries.size() != 0) {
                dailyMealPlan.setKey(dailyMealEntries.get(0).getKey());
            }
            store(dailyMealPlan);
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
                DailyMealEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder);             // The sort order
        return cursor;
    }

    public DailyMealPlan get(String date){

        String[] values = new String[1];
        values[0]=date;
        List<DailyMealPlan> dailyMealPlans = load(DailyMealEntry.COLUMN_NAME_DATE, values);
        DailyMealPlan dailyMealPlan = null;
        if (dailyMealPlans.size() != 0)
            dailyMealPlan = dailyMealPlans.get(0);
        return dailyMealPlan;
    }

    public List<DailyMealPlan> load(String column, String[] values) {
        readDb = dailyMealContractDbHelper.getReadableDatabase();
        dailyMealPlansList = new ArrayList<>();
        Cursor cursor = read(column, values);
        while (cursor.moveToNext()) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(DailyMealEntry._ID));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(DailyMealEntry.COLUMN_NAME_DATE));
            String breakfast = cursor.getString(cursor.getColumnIndexOrThrow(DailyMealEntry.COLUMN_NAME_BREAKFAST));
            String lunch = cursor.getString(cursor.getColumnIndexOrThrow(DailyMealEntry.COLUMN_NAME_LUNCH));
            String dinner = cursor.getString(cursor.getColumnIndexOrThrow(DailyMealEntry.COLUMN_NAME_DINNER));
            DailyMealPlan newMealPlan = new DailyMealPlan(itemId, breakfast, lunch, dinner, date);
            dailyMealPlansList.add(newMealPlan);
        }
        cursor.close();
        readDb.close();
        return dailyMealPlansList;
    }

    public void removeAll() {
           //List<DailyMealPlan> dailyMealPlanList = load( null,null);
           int numRowsDeleted =  remove(null,null);
    }

    public int remove(String column, String[] values) {
        readDb = dailyMealContractDbHelper.getWritableDatabase();
        // Define 'where' part of query.
        String selection = column;
        if (column != null) {
            selection = column + " LIKE ?";
        }
        // Specify arguments in placeholder order.
        String[] selectionArgs = values;

        int deletedRows = readDb.delete(DailyMealEntry.TABLE_NAME, selection, selectionArgs);
        readDb.close();
        return deletedRows;
    }

    public List<DailyMealPlan> getRandomList(List<Date> date) {
        MealDataManager mealDataManager = new MealDataManager(mContext);

        List<Meal> breakfast = mealDataManager.getRandomMealList(FoodType.BREAKFAST, date);
        List<Meal> lunch = mealDataManager.getRandomMealList(FoodType.LUNCH, date);
        List<Meal> dinner = mealDataManager.getRandomMealList(FoodType.DINNER, date);

        dailyMealPlansList = new ArrayList<>();
        for (int i = 0; i < date.size(); i++) {
            dailyMealPlansList.add(i, new DailyMealPlan(
                    breakfast.get(i).getMealName(),
                    lunch.get(i).getMealName(),
                    dinner.get(i).getMealName(),
                    date.get(i)));
        }

        return dailyMealPlansList;

    }

    public void setInUse(List<DailyMealPlan> dailyMealPlanList) {
        MealDataManager mealDataManager = new MealDataManager(mContext);
        for (DailyMealPlan dailyMealPlan : dailyMealPlanList) {
            dailyMealPlan.setInUse(true);
        }
    }
}
