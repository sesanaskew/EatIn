package com.askew.sean.mealplanner.database;

import android.provider.BaseColumns;

public class IngredientContract {

    public static class IngredientEntry implements BaseColumns {
        public static final String TABLE_NAME = "ingredient_entry";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DATA = "data";
    }
}
