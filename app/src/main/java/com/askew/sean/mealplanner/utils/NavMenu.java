package com.askew.sean.mealplanner.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.askew.sean.mealplanner.R;
import com.askew.sean.mealplanner.dailymealplan.MainActivity;
import com.askew.sean.mealplanner.dailymealplancalendar.DailyMealPlanCalendarActivity;
import com.askew.sean.mealplanner.mealviewactivity.MealViewActivity;

import java.util.Calendar;

public class NavMenu {

    private AppCompatActivity mAppCompatActivity;
    private Context mContext;
    private int mContentView;
    private ImageButton mHomeBtn;
    private ImageButton mFavBtn;
    private ImageButton mCreateMealPlanBtn;
    private ImageButton mViewEditMealsRecipesBtn;
    private ImageButton mViewGroceryListBtn;

    static private Calendar sCalendarViewDate = Calendar.getInstance();

    private NavMenu(){
        super();
    }

    public NavMenu(AppCompatActivity activity, Context context, int contentView) {

        this.mAppCompatActivity = activity;
        this.mContext = context;
        this.mContentView = contentView;

        mAppCompatActivity.setContentView(mContentView);

        createButtons();
    }

    public static void setsCalendarViewDate(Calendar calendar) {

        if (calendar != null) {
            NavMenu.sCalendarViewDate = calendar;
        }
    }

    public static Calendar getsCalendarViewDate(){

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(NavMenu.sCalendarViewDate.getTime());

        return calendar;
    }

    public void goToHome(View view){
        clearButtons();
        mHomeBtn.setSelected(true);
        Intent intent = new Intent(mContext, MainActivity.class);
        mAppCompatActivity.startActivity(intent);
    }

    public void goToFav(View view){
        clearButtons();
        mFavBtn.setSelected(true);
        Intent intent = new Intent(mContext,MainActivity.class); //(mContext,mActivity);
        mAppCompatActivity.startActivity(intent);
    }

    public void goToCreateMealPlan(View view){
        clearButtons();
        mCreateMealPlanBtn.setSelected(true);
        Intent intent = new Intent(mContext, DailyMealPlanCalendarActivity.class); //(mContext, mActivity);
        mAppCompatActivity.startActivity(intent);
    }

    public void goToViewEditMealsRecipes(View view){

        clearButtons();
        mCreateMealPlanBtn.setSelected(true);
        Intent intent = new Intent(mContext, MealViewActivity.class);
        mAppCompatActivity.startActivity(intent);
}

    public void goToViewGroceryList(View view){

        clearButtons();
        mViewGroceryListBtn.setSelected(true);
        Intent intent = new Intent(mContext,MainActivity.class);

        mAppCompatActivity.startActivity(intent);
    }

    public void createButtons() {

        mHomeBtn = mAppCompatActivity.findViewById(R.id.homeBtn) ;
        mFavBtn = mAppCompatActivity.findViewById(R.id.favBtn);
        mCreateMealPlanBtn = mAppCompatActivity.findViewById(R.id.addMealPlanBtn);
        mViewEditMealsRecipesBtn =  mAppCompatActivity.findViewById(R.id.viewEditRecipesBtn);
        mViewGroceryListBtn =  mAppCompatActivity.findViewById(R.id.grocListBtn);
    }

    public void registerHome(View.OnClickListener listener){
        mHomeBtn.setOnClickListener(listener);
    }
    public void registerFav(View.OnClickListener listener){
        mFavBtn.setOnClickListener(listener);
    }
    public void registerCreateMealPlan(View.OnClickListener listener){
        mCreateMealPlanBtn.setOnClickListener(listener);

    }
    public void registerViewEditMealsRecipes(View.OnClickListener listener){
        mViewEditMealsRecipesBtn.setOnClickListener(listener);
    }
    public void registerViewGroceryList(View.OnClickListener listener){
        mViewGroceryListBtn.setOnClickListener(listener);
    }
    private void clearButtons(){
        mHomeBtn.setSelected(false);
        mFavBtn.setSelected(false);
        mCreateMealPlanBtn.setSelected(false);
        mViewEditMealsRecipesBtn.setSelected(false);
        mViewGroceryListBtn.setSelected(false);
    }


    public interface NavMenuInt{
        public void goToHome(View view);
        public void goToFav(View view);
        public void goToCreateMealPlan(View view);
        public void goToViewEditRecipes(View view);
        public void goToViewGroceryList(View view);
        public void registerHome();
        public void registerFav();
        public void registerCreateMealPlan();
        public void registerViewEditMealRecipes();
        public void registerViewGroceryList();
    }
}
