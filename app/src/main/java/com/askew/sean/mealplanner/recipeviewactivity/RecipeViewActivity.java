package com.askew.sean.mealplanner.recipeviewactivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.askew.sean.mealplanner.R;
import com.askew.sean.mealplanner.menu.NavigationMenu;
import com.askew.sean.mealplanner.model.FoodType;
import com.askew.sean.mealplanner.utils.NavMenu;

public class RecipeViewActivity extends AppCompatActivity /*implements RecipeViewMenu.MealViewMenuInt */{
    private NavMenu mNavMenu;
    private RecipeViewMenu mRecipeViewMenu;
    private RecipeViewRecyclerView mRecipeViewRecyclerView;
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
        setContentView(R.layout.activity_view_recipes2);
        createMenus();
        initializeRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateRecyclerView(FoodType.BREAKFAST);
    }

    private void createMenus() {

        mRecipeViewMenu = new RecipeViewMenu(this, getApplicationContext(), R.layout.activity_view_recipes2);
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
    }

    private void initializeRecyclerView() {

        mRecipeViewRecyclerView = new RecipeViewRecyclerView(this,
                                    getApplicationContext(),
                FoodType.BREAKFAST);

    }

    private void updateRecyclerView(FoodType foodType) {
        mRecipeViewRecyclerView.updateFoodType(foodType);
    }

    public void goToViewMeals(View view){
        mRecipeViewMenu.goToviewMeals(view);
    }

    public void  registerViewMeals(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToViewMeals(view);
            }
        };

        mRecipeViewMenu.registerViewMeals(listener);
    }

    public void  gotoViewRecipes(View view){
        mRecipeViewMenu.goToViewRecipes(view);
    }

    public void registerViewRecipes(){View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            gotoViewRecipes(view);
        }
    };

        mRecipeViewMenu.registerViewRecipes(listener);
}

}
