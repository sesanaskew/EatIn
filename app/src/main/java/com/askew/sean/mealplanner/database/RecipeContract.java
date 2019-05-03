package com.askew.sean.mealplanner.database;

import android.provider.BaseColumns;

public final class RecipeContract {

    private RecipeContract() { }

    public static class RecipeEntry implements BaseColumns {
        public static final String TABLE_NAME = "recipe_entry";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_FOOD_TYPE = "food_type";
        public static final String COLUMN_NAME_DATA = "data";
        public static final String COLUMN_NAME_IMAGE = "image";
    }


}
