package com.askew.sean.mealplanner.recipeviewactivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.askew.sean.mealplanner.R;
import com.askew.sean.mealplanner.mealviewactivity.MealViewActivity;

public class RecipeViewMenu {
    private AppCompatActivity activity;
    private Context mContext;
    private ImageButton mViewMealButton;
    private ImageButton mViewRecipeButton;
    private ImageButton mBreakfastListButton;
    private ImageButton mLunchListButton;
    private ImageButton mDinnerListButton;
    private int mContentView;

    public RecipeViewMenu(AppCompatActivity activity, Context context, int contentView) {

        this.activity = activity;
        this.mContext = context;
        this.mContentView = contentView;

        activity.setContentView(mContentView);
        initMenu();
    }

    private void initMenu(){
        mViewMealButton = activity.findViewById(R.id.viewMealsBtn);
        mViewMealButton.setSelected(false);

        mViewRecipeButton = activity.findViewById(R.id.viewRecipeBtn);
        mViewRecipeButton.setSelected(false);

        mBreakfastListButton = activity.findViewById(R.id.breakfastMealsBtn);
        mBreakfastListButton.setSelected(false);

        mLunchListButton = activity.findViewById(R.id.lunchMealsBtn);
        mLunchListButton.setSelected(false);

        mDinnerListButton = activity.findViewById(R.id.dinnerMealsBtn);
        mDinnerListButton.setSelected(false);

    }

    public void goToviewMeals(View view){
        mViewMealButton.setSelected(true);
        mViewRecipeButton.setSelected(false);
        Intent intent = new Intent(mContext, MealViewActivity.class); //(mContext, mActivity);
        activity.startActivity(intent);

    }

    public void goToViewRecipes(View view){
        mViewMealButton.setSelected(false);
        mViewRecipeButton.setSelected(true);
        Intent intent = new Intent(mContext, RecipeViewActivity.class); //(mContext, mActivity);
        activity.startActivity(intent);
    }

    public void goToBreakfastList(View view){
        clearButtons();
        mBreakfastListButton.setSelected(true);
    }


    public void goToLunchList(View view){
        clearButtons();
        mLunchListButton.setSelected(true);
    }

    public void goToDinnerList(View view){
        clearButtons();
        mDinnerListButton.setSelected(true);
    }

    private void clearButtons(){
        mBreakfastListButton.setSelected(false);
        mLunchListButton.setSelected(false);
        mDinnerListButton.setSelected(false);

        mViewMealButton.setSelected(false);
        mViewRecipeButton.setSelected(false);
    }

    public void registerViewMeals(View.OnClickListener listener){
        mViewMealButton.setOnClickListener(listener);
    }

    public void registerViewRecipes(View.OnClickListener listener){
        mViewRecipeButton.setOnClickListener(listener);
    }

    public void registerBreakfastList(View.OnClickListener listener){
        mBreakfastListButton.setOnClickListener(listener);
    }

    public void registerLunchList(View.OnClickListener listener){
        mLunchListButton.setOnClickListener(listener);
    }

    public void registerDinnerList(View.OnClickListener listener){
        mDinnerListButton.setOnClickListener(listener);
    }
    public interface MealViewMenuInt{
            void goToViewMeals(View view);
            void registerViewMeals();
            void gotoViewRecipes(View view);
            void registerViewRecipes();
            void goToBreakfastList(View view);
            void registerBreakfastList();
            void goToLunchList(View view);
            void registerLunchList();
            void goToDinnerList(View view);
            void registerDinnerList();

    }
}
