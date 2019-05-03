package com.askew.sean.mealplanner.database;

import android.provider.BaseColumns;

public final class DebugToolContract {

    private DebugToolContract() { }

    public static class DebugToolEntry implements BaseColumns {
        public static final String TABLE_NAME = "recipe_entry";
        public static final String COLUMN_NAME_CLEAN_DB = "clean_db";
        public static final String COLUMN_NAME_UPDATE_DB = "update_db";
        public static final String COLUMN_NAME_DEBUG_MODE = "debug_mode";
    }
}
