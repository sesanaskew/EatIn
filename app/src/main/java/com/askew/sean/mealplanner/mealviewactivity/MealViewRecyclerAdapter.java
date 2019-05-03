package com.askew.sean.mealplanner.mealviewactivity;

import android.content.Context;

import com.askew.sean.mealplanner.database.MealContract;
import com.askew.sean.mealplanner.database.MealDataManager;
import com.askew.sean.mealplanner.model.FoodType;
import com.askew.sean.mealplanner.model.Meal;
import com.askew.sean.mealplanner.recycler.MealRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MealViewRecyclerAdapter extends MealRecyclerAdapter {

    MealDataManager mMealDataManager;

    MealViewRecyclerAdapter(Context context, FoodType foodType) {
        super();
        mMealDataManager = new MealDataManager(context);
        updateData(foodType);
    }

    public void updateData(FoodType foodType){
        String column= MealContract.MealEntry.COLUMN_NAME_MEAL_TYPE;
        List<String> values = new ArrayList<>();
        values.add(foodType.toString());
        List<Meal> dailyMealList = mMealDataManager.load(column,(ArrayList<String>) values);
        super.setMealList((ArrayList<Meal>) dailyMealList);
    }
}
