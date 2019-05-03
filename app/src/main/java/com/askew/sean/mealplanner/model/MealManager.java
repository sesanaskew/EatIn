package com.askew.sean.mealplanner.model;

import android.content.Context;

import com.askew.sean.mealplanner.database.MealDataManager;

import java.util.ArrayList;
import java.util.List;

public class MealManager {
    private MealDataManager mMealDataManager;

     private void mealManager(){}
     public    void  mealManager(FoodType foodType, Context context){
         mMealDataManager = new MealDataManager(context);
         List<Meal> mealList = new ArrayList<>();
    }
}
