package com.askew.sean.mealplanner.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.askew.sean.mealplanner.database.MealContract.MealEntry;
import com.askew.sean.mealplanner.model.FoodType;
import com.askew.sean.mealplanner.model.Meal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MealDataManager {
    MealContractDbHelper mealContractDbHelper;

    public MealDataManager(Context context) {
        mealContractDbHelper = new MealContractDbHelper(context);

    }
    public MealDataManager(){

    }
    private long insert(String mealName,
                        String mealType,
                        String appRecipe,
                        String entreeRecipe,
                        String sidesRecipes,
                        String dessertRecipe) {
        SQLiteDatabase db = mealContractDbHelper.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(MealEntry.COLUMN_NAME_MEAL_NAME, mealName);
        values.put(MealEntry.COLUMN_NAME_MEAL_TYPE, mealType);
        values.put(MealEntry.COLUMN_NAME_APPETIZER, appRecipe);
        values.put(MealEntry.COLUMN_NAME_ENTREE, entreeRecipe);
        values.put(MealEntry.COLUMN_NAME_SIDES, sidesRecipes);
        values.put(MealEntry.COLUMN_NAME_APPETIZER, dessertRecipe);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(MealEntry.TABLE_NAME, null, values);

        return newRowId;
    }

    public long store(Meal meal) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> names = meal.getSidesRecipes();
        for (String name : names) {
            sb.append(name);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        String sides = sb.toString();
        return insert(meal.getMealName(),
                meal.getMealType().toString(),
                meal.getAppRecipe(),
                meal.getEntreeRecipe(),
                sides,
                meal.getAppRecipe()
        );
    }

    private Cursor read(String column, ArrayList<String> values) {
        SQLiteDatabase  db = mealContractDbHelper.getReadableDatabase();
        String[] projection = null;

        String selection = null;
        if (column != null) {
            // Filter results WHERE "title" = 'My Title'
            selection = new String(column + " = ?");
        }
        String[] selectionArgs = null;
        if (values != null) {
            selectionArgs = new String[values.size()];
            selectionArgs = values.toArray(selectionArgs);
        }
        String sortOrder =
                MealEntry.COLUMN_NAME_MEAL_NAME + " DESC";

        Cursor cursor = db.query(
                MealEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder);             // The sort order

        return cursor;

    }

    public ArrayList<Meal> load(String column, ArrayList<String> values) {
        ArrayList<Meal> mealList = new ArrayList<>();
        Cursor cursor = read(column, values);
        while (cursor.moveToNext()) {
            Meal newMeal = readMeal(cursor);
            if (newMeal.isAvailable()) {
                mealList.add(newMeal);
            }
        }
        cursor.close();
        return mealList;
    }

    private Meal readMeal(Cursor cursor ){

            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(MealEntry._ID));
            String mealName = cursor.getString(cursor.getColumnIndexOrThrow(MealEntry.COLUMN_NAME_MEAL_NAME));
            String mealType = cursor.getString(cursor.getColumnIndexOrThrow(MealEntry.COLUMN_NAME_MEAL_TYPE));
            String app = cursor.getString(cursor.getColumnIndexOrThrow(MealEntry.COLUMN_NAME_APPETIZER));
            String entree = cursor.getString(cursor.getColumnIndexOrThrow(MealEntry.COLUMN_NAME_ENTREE));
            String sides = cursor.getString(cursor.getColumnIndexOrThrow(MealEntry.COLUMN_NAME_SIDES));
            String dessert = cursor.getString(cursor.getColumnIndexOrThrow(MealEntry.COLUMN_NAME_DESSERT));
            String[] sideArray = sides.split(",");
            ArrayList<String> sidesList = new ArrayList<>(Arrays.asList(sideArray));
            FoodType type = FoodType.valueOf(mealType);

            return new Meal(itemId, mealName, type, app, entree, sidesList, dessert);
        }

    public Meal get(String mealName){
        List<String> values = new ArrayList<String>();
        values.add(mealName);  //new String[1];

        List<Meal> mealList = load(MealEntry.COLUMN_NAME_MEAL_NAME, (ArrayList<String>) values);
        Meal newMeal;
        if (mealList.isEmpty()){

            newMeal = getEmptyMeal();
        }
        else{
            newMeal = mealList.get(0);
        }
        return newMeal;
    }

    private Meal getEmptyMeal(){
        List<String> sidesStringList =  new ArrayList<String>();
        sidesStringList.add(new String(""));
        return new Meal(
                new String("                            "),
                FoodType.EMPTY,
                new String(""),
                new String(""),
                (ArrayList<String>)sidesStringList,
                new String(""));
    }

    public void removeAll (){
        String[] values = new String[1];
        FoodType[] foodType = FoodType.values();

        for (FoodType food : foodType) {
            values[0] = food.toString();
            remove(MealEntry.COLUMN_NAME_MEAL_TYPE, values);
        }
    }

    public void remove(String column,String[] values){
       SQLiteDatabase db = mealContractDbHelper.getWritableDatabase();
        // Define 'where' part of query.
        String selection = column + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = values;
        int deletedRows = db.delete(MealEntry.TABLE_NAME, selection, selectionArgs);
        db.close();
    }

    public List<Meal> getRandomMealList(FoodType foodType, List<Date> dates){

        ArrayList<String> values = new ArrayList<>();
        values.add(foodType.toString());

        List<Meal> mealList = load(MealEntry.COLUMN_NAME_MEAL_TYPE,values);

        List<Meal> randomMealList = new ArrayList<>();
        for(Date date : dates) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, mealList.size());
            Meal meal = mealList.remove(randomNum);
            meal.setUseDate(date);
            randomMealList.add(meal);
            meal.setInUse(true);
            store(meal);
        }
        return randomMealList;
    }
}
