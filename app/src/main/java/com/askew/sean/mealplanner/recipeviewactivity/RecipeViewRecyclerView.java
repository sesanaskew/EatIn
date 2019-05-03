package com.askew.sean.mealplanner.recipeviewactivity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.askew.sean.mealplanner.R;
import com.askew.sean.mealplanner.model.FoodType;

public class RecipeViewRecyclerView {
    private RecyclerView mealViewRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private AppCompatActivity mActivity;
    public RecipeViewRecyclerView(AppCompatActivity activity, Context context, FoodType foodType){
        super();
        mActivity = activity;
        createRecyclerAdapter(context,foodType);
    }
    public void createRecyclerAdapter(Context context, FoodType foodType) {
        mealViewRecyclerView = mActivity.findViewById(R.id.recipeViewRecyclerView2);
        layoutManager = new LinearLayoutManager(mActivity);
        mealViewRecyclerView.setLayoutManager(layoutManager);
        adapter = new RecipeViewRecyclerAdapter(context) ;
        mealViewRecyclerView.setAdapter(adapter);
    }
    public void updateFoodType(FoodType foodType){
     RecipeViewRecyclerAdapter recipeViewRecyclerAdapter =  (RecipeViewRecyclerAdapter) mealViewRecyclerView.getAdapter();
     recipeViewRecyclerAdapter.updateData(foodType);
     recipeViewRecyclerAdapter.notifyDataSetChanged();
    }
}
