package com.askew.sean.mealplanner.dailymealplan;

import android.app.Activity;
import android.content.Context;

import com.askew.sean.mealplanner.database.DailyMealDataManager;
import com.askew.sean.mealplanner.database.MealDataManager;
import com.askew.sean.mealplanner.model.DailyMealPlan;
import com.askew.sean.mealplanner.model.Meal;
import com.askew.sean.mealplanner.recycler.MealRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class DailyMealPlanRecyclerAdapter extends MealRecyclerAdapter {
    MealDataManager mMealDataManager;
    DailyMealDataManager mDailyMealDataManager;

    DailyMealPlanRecyclerAdapter(Activity activity, Context context, DailyMealPlan dailyMealPlan) {
        super(activity, context);

        mMealDataManager = new MealDataManager(context);
        mDailyMealDataManager= new DailyMealDataManager(context);
        updateData(dailyMealPlan);
    }

    public void updateData(DailyMealPlan dailyMealPlan){

        List<Meal> dailyMealList = new ArrayList<>();

        if (dailyMealPlan != null) {
            dailyMealList.add(mMealDataManager.get(dailyMealPlan.getBreakfast()));
            dailyMealList.add(mMealDataManager.get(dailyMealPlan.getLunch()));
            dailyMealList.add(mMealDataManager.get(dailyMealPlan.getDinner()));
        }
        super.setMealList(dailyMealList);
        super.notifyDataSetChanged();
    }


    @Override
    public void setClickListener(ClickListener listener) {
        super.setClickListener(listener);
    }
}
