package com.askew.sean.mealplanner.database;

import android.provider.BaseColumns;

public final class DailyMealContract {

    private DailyMealContract() { }

    public static class DailyMealEntry implements BaseColumns {
        public static final String TABLE_NAME = "daily_meal_entry";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_BREAKFAST = "breakfast";
        public static final String COLUMN_NAME_LUNCH = "lunch";
        public static final String COLUMN_NAME_DINNER = "dinner";
        public static final String COLUMN_NAME_CURRENT_MEAL_PLAN = "current";

    }




}
