package com.askew.sean.mealplanner.mealrecipesview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.askew.sean.mealplanner.R;
import com.askew.sean.mealplanner.dailymealplan.DailyMealPlanSnapHelper;
import com.askew.sean.mealplanner.database.MealDataManager;
import com.askew.sean.mealplanner.database.RecipeDataManager;
import com.askew.sean.mealplanner.menu.NavigationMenu;
import com.askew.sean.mealplanner.model.CalendarViewDate;
import com.askew.sean.mealplanner.model.Meal;
import com.askew.sean.mealplanner.model.Recipe;
import com.askew.sean.mealplanner.recipeviewactivity.RecipeViewMenu;
import com.askew.sean.mealplanner.utils.NavMenu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MealRecipesViewActivity extends AppCompatActivity /*implements RecipeViewMenu.MealViewMenuInt */{
    private NavMenu mNavMenu;
    private RecipeViewMenu mRecipeViewMenu;
    private RecyclerView mRecipeViewRecyclerView;
    private MealRecipesViewRecyclerAdapter mAdapter;
    private RecipeDataManager mRecipeDataManager;
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
        setContentView(R.layout.activity_view_meal_recipes2);
        createMenus();
        initializeRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
      //  updateRecyclerView(CalendarViewDate.getCalendarDateString());
    }

    private void createMenus() {
        //mRecipeViewMenu = new RecipeViewMenu(this, getApplicationContext(), R.layout.activity_view_recipes2);
        BottomNavigationView navigation = findViewById(R.id.navigation_layout_id);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private void initializeRecyclerView() {

        setContentView(R.layout.activity_view_meal_recipes2);

        List<Recipe> recipeList = getRecipes(CalendarViewDate.getMealName());

        mRecipeViewRecyclerView = findViewById(R.id.meal_recipes_view_recycler_id);

        mRecipeViewRecyclerView.setItemAnimator(new DefaultItemAnimator());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecipeViewRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new MealRecipesViewRecyclerAdapter(this, getApplicationContext(), recipeList);

        mRecipeViewRecyclerView.setAdapter(mAdapter);

        DailyMealPlanSnapHelper dailyMealPlanSnapHelper =  new DailyMealPlanSnapHelper();

        dailyMealPlanSnapHelper.attachToRecyclerView(mRecipeViewRecyclerView);

        mRecipeViewRecyclerView.setHasFixedSize(true);

        int today = CalendarViewDate.getTodayDateCalendar().get(Calendar.DAY_OF_MONTH);

    }

    private List<Recipe> getRecipes(String name){
        List<Recipe> recipeList = new ArrayList<>();
        MealDataManager db = new MealDataManager(getApplicationContext());
        Meal meal = db.get(name);
        mRecipeDataManager = new RecipeDataManager(getApplicationContext());
        recipeList = addRecipeToList(recipeList,meal.getAppRecipe());
        recipeList = addRecipeToList(recipeList,meal.getEntreeRecipe());
        recipeList = addRecipeToList(recipeList,meal.getDessertRecipe());
        ArrayList<String> sideRecipeList = meal.getSidesRecipes();
        recipeList = addRecipeToList(recipeList,sideRecipeList.get(0));
        return recipeList;
    }

    private List<Recipe> addRecipeToList(List<Recipe> recipeList, String recipeName){
        Recipe recipe = mRecipeDataManager.get(recipeName);
        if (recipe != null){
            recipeList.add(recipe);
        }
        return recipeList;
    }

    private void updateRecyclerView(String date) {
        List<Recipe> recipeList = getRecipes(date);
        mAdapter.setRecipeList(recipeList);
        mAdapter.notifyDataSetChanged();
    }

}
