package com.askew.sean.mealplanner.tools;

import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;

import com.askew.sean.mealplanner.database.DailyMealDataManager;
import com.askew.sean.mealplanner.database.MealDataManager;
import com.askew.sean.mealplanner.database.RecipeDataManager;
import com.askew.sean.mealplanner.model.FoodType;
import com.askew.sean.mealplanner.model.Meal;
import com.askew.sean.mealplanner.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Database {

    private MealDataManager mMealDataManager;
    private DailyMealDataManager mDailyMealDataManager;
    private RecipeDataManager mRecipeDataManager;
    private static final int NUMBER_OF_MEALS = 20;
    private Boolean debugMode = false;

    public Database(AppCompatActivity appCompatActivity, Context context) {

        AppCompatActivity mAppCompatActivity = appCompatActivity;
        this.mMealDataManager = new MealDataManager(mAppCompatActivity.getApplicationContext());
        this.mDailyMealDataManager = new DailyMealDataManager(mAppCompatActivity.getApplicationContext());
        this.mRecipeDataManager = new RecipeDataManager(mAppCompatActivity.getApplicationContext());
    }

    public void cleanDb(){
        if (debugMode ) {
            deleteDbEntries();
            initializeDB();
        }
    }

    public void initializeDB() {
        addMealsToDb();

    }

    public void updateDb(){
        cleanDb();
    }

    public void  addRecipeToDb(String theRecipeName,FoodType foodType){

        /*Bitmap image = BitmapFactory.decodeResource(mAppCompatActivity.getResources(),
                R.drawable.GrilledCheese);
        // convert bitmap to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte imageInByte[] = stream.toByteArray();*/


        List<String> ingredients = asList("4 slices white bread",
                "3 tablespoons butter, divided",
                "2 slices Cheddar cheese");

        String instructions = "Preheat skillet over medium heat. Generously butter one side of a slice of bread. Place bread butter-side-down onto skillet bottom and add 1 slice of cheese. Butter a second slice of bread on one side and place butter-side-up on top of sandwich. Grill until lightly browned and flip over; continue grilling until cheese is melted. Repeat with remaining 2 slices of bread, butter and slice of cheese.";
        String cookTime = "15m";
        String prepTime = "5m";
        String serves = "1";
        Image pic = null;



        StringBuilder sb = new StringBuilder();
        for (String ingredient : ingredients) {
            sb.append(ingredient).append(System.lineSeparator());
        }
        byte[] imageInByte = null;
        String recipeIngredient = sb.toString();
        Recipe newRecipe = new Recipe(theRecipeName,
                foodType,
                recipeIngredient,
                serves,
                prepTime,
                cookTime,
                instructions,
                imageInByte);
        mRecipeDataManager.store(newRecipe);


    }

    private void addMealsToDb() {
        FoodType foodTypeArray[] = FoodType.values();
        for (FoodType foodType : foodTypeArray) {
            for (int i = 0; i < NUMBER_OF_MEALS; i++) {
                ArrayList<String> sideArrayList = new ArrayList<String>();
                String sideString = foodType.toString()+"_sides_" +  String.valueOf(i);
                sideArrayList.add(sideString);
                addRecipeToDb(sideString,foodType);
                String appString = foodType.toString()+"_app_" + String.valueOf(i);
                addRecipeToDb(appString,foodType);
                String entreeString = foodType.toString()+"_entree_" + String.valueOf(i);
                addRecipeToDb(entreeString,foodType);
                String dessertString = foodType.toString()+"_dessert_" + String.valueOf(i);
                addRecipeToDb(dessertString,foodType);
                Meal newMeal = new Meal(foodType.toString() + "_name_" + String.valueOf(i),
                        foodType,
                        appString,
                        entreeString,
                        sideArrayList,
                        dessertString
                        );
                mMealDataManager.store(newMeal);
            }
        }
    }

    private void deleteDbEntries(){
        removeDailyMealsFromDb();
        removeMealsFromDb();
        removeRecipesFromDb();
    }

    private void removeDailyMealsFromDb(){

        mDailyMealDataManager.removeAll();
    }

    private void removeMealsFromDb(){

        mMealDataManager.removeAll();
    }

    private void removeRecipesFromDb(){
        mRecipeDataManager.removeAll();
    }

    public Boolean getDebugMode() {
        return debugMode;
    }

    public void setDebugMode(Boolean debugMode) {
        this.debugMode = debugMode;
    }

}
