package com.askew.sean.mealplanner.mealviewactivity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.askew.sean.mealplanner.R;
import com.askew.sean.mealplanner.model.FoodType;

public class MealViewRecyclerView extends RecyclerView {
    private RecyclerView mealViewRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private AppCompatActivity mActivity;
    public MealViewRecyclerView(AppCompatActivity activity, Context context, FoodType foodType){
        super(context);
        mActivity = activity;
       // DateConverter dateConverter = new DateConverter(date);
        createRecyclerAdapter(context,foodType);
    }
    public void createRecyclerAdapter(Context context, FoodType foodType) {
        mealViewRecyclerView = mActivity.findViewById(R.id.mealViewRecyclerView2);
        layoutManager = new LinearLayoutManager(mActivity);
        mealViewRecyclerView.setLayoutManager(layoutManager);
        adapter = new MealViewRecyclerAdapter(context, foodType) ;
        mealViewRecyclerView .setAdapter(adapter);
    }
    public void updateFoodType(FoodType foodType){
     MealViewRecyclerAdapter mealViewRecyclerAdapter = (MealViewRecyclerAdapter) mealViewRecyclerView.getAdapter();
     mealViewRecyclerAdapter.updateData(foodType);
     mealViewRecyclerAdapter.notifyDataSetChanged();
    }
}
