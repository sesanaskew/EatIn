package com.askew.sean.mealplanner.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.askew.sean.mealplanner.database.DebugToolContract.DebugToolEntry;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DebugToolDataManager {
    DebugToolDbHelper debugToolDbHelper;
    
    Context mContext;

    public DebugToolDataManager(Context context) {
        this.mContext = context;
        debugToolDbHelper = new DebugToolDbHelper(context);

    }

    public long insert(
            boolean debugMode,
            boolean cleanDb,
            boolean updateDb) {

        SQLiteDatabase db = debugToolDbHelper.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(DebugToolEntry.COLUMN_NAME_CLEAN_DB, String.valueOf(cleanDb));
        values.put(DebugToolEntry.COLUMN_NAME_UPDATE_DB, String.valueOf(updateDb));
        values.put(DebugToolEntry.COLUMN_NAME_DEBUG_MODE, String.valueOf(debugMode));
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DebugToolEntry.TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }
/*
    public long store(Recipe recipe) {

        String recipeName = recipe.getName();
        String foodType = recipe.getFoodType().toString();

        Gson gson = new Gson();
        String recipeJsonString = gson.toJson(recipe);

        return insert(recipeName,foodType, recipeJsonString);
    }
*/
    private Cursor read(String column, String[] values) {
        SQLiteDatabase db = debugToolDbHelper.getReadableDatabase();
        String[] projection = null;

        String selection = null;
        if (column != null) {
            // Filter results WHERE "title" = 'My Title'
            selection = new String(column + " = ?");
        }
        String[] selectionArgs = values;


        Cursor cursor = db.query(
                DebugToolEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null);             // The sort order
        return cursor;
    }

    public List<Boolean> load(String column, String[] values) {
        List<List<Boolean>> debugInfo = new ArrayList<List<Boolean>>();
        Cursor cursor = read(column, values);
        while (cursor.moveToNext()) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(DebugToolEntry._ID));
            String debugMode = cursor.getString(cursor.getColumnIndexOrThrow(DebugToolEntry.COLUMN_NAME_DEBUG_MODE));
            String cleanDb = cursor.getString(cursor.getColumnIndexOrThrow(DebugToolEntry.COLUMN_NAME_CLEAN_DB));
            String updateDb = cursor.getString(cursor.getColumnIndexOrThrow(DebugToolEntry.COLUMN_NAME_UPDATE_DB));
            Gson gson = new Gson();
            Boolean[] debugArray = {
                    Boolean.parseBoolean(debugMode),
                    Boolean.parseBoolean(cleanDb),
                    Boolean.parseBoolean(updateDb)};

           List<Boolean> tList = new ArrayList<Boolean>(Arrays.asList(debugArray));
           debugInfo.add(tList);
        }
        cursor.close();

        List<Boolean> debugInfoReturnList;
        if ( debugInfo.size() == 0 )
            debugInfoReturnList = new ArrayList<>();
        else
            debugInfoReturnList = debugInfo.get(0);
        return debugInfoReturnList;
    }

    public void removeAll() {

           int numRowsDeleted =  remove(null,null);
    }

    public int remove(String column, String[] values) {
        SQLiteDatabase db = debugToolDbHelper.getWritableDatabase();
        // Define 'where' part of query.
        String selection = column;
        if (column != null) {
            selection = column + " LIKE ?";
        }
        // Specify arguments in placeholder order.
        String[] selectionArgs = values;

        int deletedRows = db.delete(DebugToolEntry.TABLE_NAME, selection, selectionArgs);

        return deletedRows;
    }

}
