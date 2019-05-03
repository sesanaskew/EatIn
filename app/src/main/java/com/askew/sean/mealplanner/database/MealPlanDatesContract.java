package com.askew.sean.mealplanner.database;

import android.provider.BaseColumns;

public final class MealPlanDatesContract {

    private MealPlanDatesContract() { }

    public static class MealPlanDatesEntry implements BaseColumns {
        public static final String TABLE_NAME = "daily_meal_entry";
        public static final String COLUMN_NAME_DATE = "date_start";
    }




}
