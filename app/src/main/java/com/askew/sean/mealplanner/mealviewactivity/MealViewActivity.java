package com.askew.sean.mealplanner.mealviewactivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.askew.sean.mealplanner.R;
import com.askew.sean.mealplanner.menu.NavigationMenu;
import com.askew.sean.mealplanner.model.FoodType;
import com.askew.sean.mealplanner.utils.NavMenu;

public class MealViewActivity extends AppCompatActivity implements MealViewMenu.MealViewMenuInt {
    private NavMenu mNavMenu;
    private MealViewMenu mMealViewMenu;
    private MealViewRecyclerView mMealViewRecyclerView;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.nav_home_btn:
                    NavigationMenu.goToHome(getCurrentFocus());
                    return true;
                case R.id.nav_view_edit_meals_recipes:
                    NavigationMenu.goToViewEditMealsRecipes(getCurrentFocus());
                    return true;
                case R.id.nav_create_mealplan:
                    NavigationMenu.goToCreateMealPlan(getCurrentFocus());
                    return true;
                case R.id.nav_grocery_list:
                    NavigationMenu.goToViewGroceryList(getCurrentFocus());
                    return true;
                case R.id.nav_settings:
                    NavigationMenu.goToConfigSettings(getCurrentFocus());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_meals2);
        createMenus();
        initializeRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateRecyclerView(FoodType.BREAKFAST);
    }

    private void createMenus() {
        mMealViewMenu = new MealViewMenu(this, getApplicationContext(), R.layout.activity_view_meals2);
        BottomNavigationView navigation = findViewById(R.id.navigation_layout_id);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        registerMenu();
    }

    private void registerMenu(){
        registerMealViewCalendarMenu();
    }

    public void registerMealViewCalendarMenu(){
        registerViewMeals();
        registerViewRecipes();
        registerBreakfastList();
        registerLunchList();
        registerDinnerList();
    }

    private void initializeRecyclerView() {
        mMealViewRecyclerView = new MealViewRecyclerView(this,
                                    getApplicationContext(),
                FoodType.BREAKFAST);
        MealViewSnapHelper mealViewSnapHelper =  new MealViewSnapHelper();
        mealViewSnapHelper.attachToRecyclerView((RecyclerView) mMealViewRecyclerView);
    }

    private void updateRecyclerView(FoodType foodType) {
        mMealViewRecyclerView.updateFoodType(foodType);
    }

    public void goToViewMeals(View view){
        mMealViewMenu.goToviewMeals(view);
    }

    public void  registerViewMeals(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToViewMeals(view);
            }
        };

        mMealViewMenu.registerViewMeals(listener);
    }

    public void  gotoViewRecipes(View view){
        mMealViewMenu.goToViewRecipes(view);
    }

    public void registerViewRecipes(){View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            gotoViewRecipes(view);
        }
    };

        mMealViewMenu.registerViewRecipes(listener);
}

    public void goToBreakfastList(View view){
        updateRecyclerView(FoodType.BREAKFAST);
        mMealViewMenu.goToBreakfastList(view);

    }

    public void registerBreakfastList(){View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            goToBreakfastList(view);
            }
        };
     mMealViewMenu.registerBreakfastList(listener);
    }

    public void goToLunchList(View view){
        updateRecyclerView(FoodType.LUNCH);
        mMealViewMenu.goToLunchList(view);

    }

    public void registerLunchList(){View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // if (! (this instanceof MainActivity)){
            goToLunchList(view);
            // }}
        }
    };
        mMealViewMenu.registerLunchList(listener);
    }

    public void goToDinnerList(View view){
        updateRecyclerView(FoodType.DINNER);
        mMealViewMenu.goToDinnerList(view);
    }

    public void registerDinnerList(){View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // if (! (this instanceof MainActivity)){
            goToDinnerList(view);
            // }}
        }
    };
        mMealViewMenu.registerDinnerList(listener);
    }


}
