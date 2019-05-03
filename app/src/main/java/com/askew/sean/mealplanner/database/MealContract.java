package com.askew.sean.mealplanner.database;

import android.provider.BaseColumns;

public final class MealContract {

    private MealContract() { }

    public static class MealEntry implements BaseColumns {
        public static final String TABLE_NAME = "meal_entry";
        public static final String COLUMN_NAME_MEAL_NAME = "meal_name";
        public static final String COLUMN_NAME_MEAL_TYPE = "meal_type";
        public static final String COLUMN_NAME_APPETIZER = "appetizer";
        public static final String COLUMN_NAME_ENTREE = "entree";
        public static final String COLUMN_NAME_SIDES = "sides";
        public static final String COLUMN_NAME_DESSERT = "dessert";
    }


}
