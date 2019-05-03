package com.askew.sean.mealplanner.config;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.askew.sean.mealplanner.R;
import com.askew.sean.mealplanner.database.DailyMealDataManager;
import com.askew.sean.mealplanner.database.MealDataManager;
import com.askew.sean.mealplanner.database.RecipeDataManager;
import com.askew.sean.mealplanner.menu.NavigationMenu;
import com.askew.sean.mealplanner.model.DailyMealPlan;
import com.askew.sean.mealplanner.model.Meal;
import com.askew.sean.mealplanner.model.Recipe;
import com.askew.sean.mealplanner.tools.AppDebug;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ConfigSettingsActivity extends AppCompatActivity {

    private boolean userDebugModeSwitch;
    private boolean userCleanDbSwitch;
    private DebugDbRecyclerView debugDbRecyclerView;
    private RadioGroup radioGroup;
    private AppDebug appDebug;
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
                default:
                    Log.i(this.getClass().toString(),"@string/unknown_nav");
                    return false;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_settings);
        appDebug = AppDebug.getInstance(this, getApplicationContext());
        initBottomNavigation();
        initializeRecyclerView();
        initDebugDatabase();
        displayDbDataSelection();
    }

    @Override
    protected void onResume(){
        super.onResume();


    }
    private void initBottomNavigation(){

        BottomNavigationView navigation = findViewById(R.id.navigation_layout_id);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

       NavigationMenu navigationMenu = new NavigationMenu(getApplicationContext(), this);
    }
    private void initDebugDatabase(){

        boolean systemDebugMode = appDebug.getDebugMode();

        boolean systemCleanDb = appDebug.getCleanDb();


        Switch debugModeSwitch = findViewById(R.id.debug_mode_switch_id);

        debugModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    userDebugModeSwitch = true;
                } else {
                    userDebugModeSwitch = false;
                }

            }
        });

        Switch cleanDbSwitch = findViewById(R.id.clean_db_switch_id);

        cleanDbSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    userCleanDbSwitch = true;
                } else {
                    userCleanDbSwitch = false;
                }
            }
        });

        debugModeSwitch.setChecked(systemDebugMode);
        cleanDbSwitch.setChecked(systemCleanDb);

        Button okButton = findViewById(R.id.commit_settings_id);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appDebug.setDebugMode(userDebugModeSwitch);
                appDebug.setCleanDb(userCleanDbSwitch);
                appDebug.updateConfigDb();
                NavigationMenu.goToHome(view);
            }
        });

        Button cancelButton = findViewById(R.id.cancel_settings_id);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationMenu.goToHome(view);
            }
        });

    }
    private void initializeRecyclerView() {
        debugDbRecyclerView = new DebugDbRecyclerView(this,
                getApplicationContext());
    }
    private List<String[]> getDbData(DbType dbType) {
        List<String[]> dataList = new ArrayList<String[]>();
        switch (dbType) {
            case MEAL_PLAN:
                DailyMealDataManager dailyMealDataManager = new DailyMealDataManager(getApplicationContext());
                List<DailyMealPlan> dailyMealPLanList = dailyMealDataManager.load(null, null);
                for (DailyMealPlan dailyMealPlan : dailyMealPLanList) {
                    String[] dailyMealPlanData = new String[2];

                    dailyMealPlanData[0] = Integer.toString((int) dailyMealPlan.getKey());
                    dailyMealPlanData[1] = dailyMealPlan.getDate();
                    dataList.add(dailyMealPlanData);
                }
                return dataList;
            case MEALS:
                MealDataManager mealDataManager = new MealDataManager(getApplicationContext());
                List<Meal> mealList = mealDataManager.load(null, null);
                for (Meal meal : mealList) {
                    String[] mealData = new String[2];
                    mealData[0] = Integer.toString((int) meal.getKey());
                    mealData[1] = meal.getMealName();
                    dataList.add(mealData);
                }
                return dataList;
            case RECIPES:
                RecipeDataManager recipeDataManager = new RecipeDataManager(getApplicationContext());
                List<Recipe> recipeList = recipeDataManager.load(null, null);
                for (Recipe recipe : recipeList) {
                    String[] recipeData = new String[2];
                    Gson gson = new Gson();

                    String jsonString = gson.toJson(recipe);
                    recipeData[0] = Integer.toString((int) recipe.getKey());
                    recipeData[1] = jsonString;
                    dataList.add(recipeData);
                }
                return dataList;
        }
        return dataList;
    }
    public void displayDbDataSelection() {
        radioGroup = findViewById(R.id.db_radio_group_id);
        Button displayButton = findViewById(R.id.db_display_data_id);
        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selected = radioGroup.getCheckedRadioButtonId();
                //Button radioButton = findViewById(selected);
                switch (selected) {
                    case R.id.mealPlanRadioButtonId:
                        debugDbRecyclerView.updateDbEntry(getDbData(DbType.MEAL_PLAN));
                        return;
                    case R.id.mealsRadioButtonId:
                        debugDbRecyclerView.updateDbEntry(getDbData(DbType.MEALS));
                        return;
                    case R.id.recipeRadioButtonId:
                        debugDbRecyclerView.updateDbEntry(getDbData(DbType.RECIPES));
                        return;
                    default:
                        Log.i(ConfigSettingsActivity.class.toString(),"Undefined Radio Button");
                        return;
                }
            }
        });
    }
}
