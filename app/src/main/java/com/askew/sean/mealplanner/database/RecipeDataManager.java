package com.askew.sean.mealplanner.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.askew.sean.mealplanner.database.RecipeContract.RecipeEntry;
import com.askew.sean.mealplanner.model.Recipe;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class RecipeDataManager {
    RecipeContractDbHelper recipeContractDbHelper;
    List<Recipe> recipesList;
    Context mContext;

    public RecipeDataManager(Context context) {
        this.mContext = context;
        recipeContractDbHelper = new RecipeContractDbHelper(context);

    }

    private long insert(String name,
                        String foodType,
                        String data) {
        SQLiteDatabase db = recipeContractDbHelper.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(RecipeEntry.COLUMN_NAME_NAME, name);
        values.put(RecipeEntry.COLUMN_NAME_FOOD_TYPE,foodType);
        values.put(RecipeEntry.COLUMN_NAME_DATA,data);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(RecipeEntry.TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    public long store(Recipe recipe) {

        String recipeName = recipe.getName();
        String foodType = recipe.getFoodType().toString();

        Gson gson = new Gson();
        String recipeJsonString = gson.toJson(recipe);

        return insert(recipeName,foodType, recipeJsonString);
    }

    private Cursor read(String column, String[] values) {
        SQLiteDatabase db = recipeContractDbHelper.getReadableDatabase();
        String[] projection = null;

        String selection = null;
        if (column != null) {
            // Filter results WHERE "title" = 'My Title'
            selection = new String(column + " = ?");
        }
        String[] selectionArgs = values;

        String sortOrder =
                RecipeEntry.COLUMN_NAME_NAME + " DESC";

        Cursor cursor = db.query(
                RecipeEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder);             // The sort order
        return cursor;
    }

    public Recipe get(String name){

        String[] values = new String[1];
        values[0]=name;
        List<Recipe> recipes = load(RecipeEntry.COLUMN_NAME_NAME, values);
        Recipe recipe = null;
        if (recipes.size() != 0)
            recipe = recipes.get(0);
        return recipe;


    }

    public List<Recipe> load(String column, String[] values) {
        recipesList = new ArrayList<>();
        Cursor cursor = read(column, values);
        while (cursor.moveToNext()) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(RecipeEntry._ID));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(RecipeEntry.COLUMN_NAME_NAME));
            String foodType = cursor.getString(cursor.getColumnIndexOrThrow(RecipeEntry.COLUMN_NAME_FOOD_TYPE));
            String recipeJsonString = cursor.getString(cursor.getColumnIndexOrThrow(RecipeEntry.COLUMN_NAME_DATA));
            Gson gson = new Gson();

            Recipe newRecipe = gson.fromJson(recipeJsonString, Recipe.class);

            recipesList.add(newRecipe);
        }
        cursor.close();
        return recipesList;
    }

    public void removeAll() {

           int numRowsDeleted =  remove(null,null);
    }

    public int remove(String column, String[] values) {
        SQLiteDatabase db = recipeContractDbHelper.getWritableDatabase();
        // Define 'where' part of query.
        String selection = column;
        if (column != null) {
            selection = column + " LIKE ?";
        }
        // Specify arguments in placeholder order.
        String[] selectionArgs = values;

        int deletedRows = db.delete(RecipeEntry.TABLE_NAME, selection, selectionArgs);

        return deletedRows;
    }

}
