package com.askew.sean.mealplanner.menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.askew.sean.mealplanner.config.ConfigSettingsActivity;
import com.askew.sean.mealplanner.dailymealplan.MainActivity;
import com.askew.sean.mealplanner.dailymealplancalendar.DailyMealPlanCalendarActivity;
import com.askew.sean.mealplanner.mealviewactivity.MealViewActivity;

public class NavigationMenu {

    static private Context context;
    static private Activity activity;
    private NavigationMenu navigationMenu;



    public NavigationMenu(Context context, Activity activity){
        NavigationMenu.context = context;
        NavigationMenu.activity = activity;

    }

    static public void goToHome(View view){
        Intent intent = new Intent(NavigationMenu.context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        NavigationMenu.activity.startActivityIfNeeded(intent,0);
    }

    static public void goToCreateMealPlan(View view) {
        Intent intent = new Intent(NavigationMenu.context, DailyMealPlanCalendarActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        NavigationMenu.activity.startActivityIfNeeded(intent,0);    }

    static public void goToViewEditMealsRecipes(View view) {
        Intent intent = new Intent(NavigationMenu.context, MealViewActivity.class);
        NavigationMenu.activity.startActivity(intent);
    }

    static public void goToViewGroceryList(View view) {
        Intent intent = new Intent(NavigationMenu.context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        NavigationMenu.activity.startActivityIfNeeded(intent,0);
    }

    static public void goToConfigSettings(View view){
        Intent intent = new Intent(NavigationMenu.context, ConfigSettingsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        NavigationMenu.activity.startActivityIfNeeded(intent,0);
    }
}
