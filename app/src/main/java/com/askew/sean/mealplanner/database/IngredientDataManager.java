package com.askew.sean.mealplanner.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.askew.sean.mealplanner.database.IngredientContract.IngredientEntry;
import com.askew.sean.mealplanner.model.Ingredient;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class IngredientDataManager {
    IngredientContractDbHelper ingredientContractDbHelper;
    List<Ingredient> ingredientsList;
    Context mContext;

    public IngredientDataManager(Context context) {
        this.mContext = context;
        ingredientContractDbHelper = new IngredientContractDbHelper(context);

    }

    private long insert(String name,
                        String data) {
        SQLiteDatabase db = ingredientContractDbHelper.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(IngredientEntry.COLUMN_NAME_NAME, name);
        values.put(IngredientEntry.COLUMN_NAME_DATA,data);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(IngredientEntry.TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    public long store(Ingredient ingredient) {

        String ingredientName = ingredient.getMealName();


        Gson gson = new Gson();
        String ingredientJsonString = gson.toJson(ingredient);

        return insert(ingredientName, ingredientJsonString);
    }

    private Cursor read(String column, String[] values) {
        SQLiteDatabase db = ingredientContractDbHelper.getReadableDatabase();
        String[] projection = null;

        String selection = null;
        if (column != null) {
            // Filter results WHERE "title" = 'My Title'
            selection = new String(column + " = ?");
        }
        String[] selectionArgs = values;

        String sortOrder =
                IngredientEntry.COLUMN_NAME_NAME + " DESC";

        Cursor cursor = db.query(
                IngredientEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder);             // The sort order
        return cursor;
    }

    public Ingredient get(String name){

        String[] values = new String[1];
        values[0]=name;
        List<Ingredient> ingredients = load(IngredientEntry.COLUMN_NAME_NAME, values);
        Ingredient ingredient = null;
        if (ingredients.size() != 0)
            ingredient = ingredients.get(0);
        return ingredient;


    }

    public List<Ingredient> load(String column, String[] values) {
        ingredientsList = new ArrayList<>();
        Cursor cursor = read(column, values);
        while (cursor.moveToNext()) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(IngredientEntry._ID));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(IngredientEntry.COLUMN_NAME_NAME));
            String ingredientJsonString = cursor.getString(cursor.getColumnIndexOrThrow(IngredientEntry.COLUMN_NAME_DATA));
            Gson gson = new Gson();

            Ingredient newIngredient = gson.fromJson(ingredientJsonString, Ingredient.class);

            ingredientsList.add(newIngredient);
        }
        cursor.close();
        return ingredientsList;
    }

    public void removeAll() {

           int numRowsDeleted =  remove(null,null);
    }

    public int remove(String column, String[] values) {
        SQLiteDatabase db = ingredientContractDbHelper.getWritableDatabase();
        // Define 'where' part of query.
        String selection = column;
        if (column != null) {
            selection = column + " LIKE ?";
        }
        // Specify arguments in placeholder order.
        String[] selectionArgs = values;

        int deletedRows = db.delete(IngredientEntry.TABLE_NAME, selection, selectionArgs);

        return deletedRows;
    }

}
