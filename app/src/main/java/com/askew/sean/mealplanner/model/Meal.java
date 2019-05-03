package com.askew.sean.mealplanner.model;

import java.util.ArrayList;

public class Meal extends UsableItem {

    private String appRecipe;
    private String entreeRecipe;
    private ArrayList<String> sidesRecipes;
    private String dessertRecipe;
    private FoodType mealType;

    public Meal(long key, String mealName,
                FoodType mealType,
                String appRecipe,
                String entreeRecipe,
                ArrayList<String> sidesRecipes,
                String dessertRecipe) {
        super(key, mealName);
        this.mealType = mealType;
        this.appRecipe = appRecipe;
        this.entreeRecipe = entreeRecipe;
        this.sidesRecipes = sidesRecipes;
        this.dessertRecipe = dessertRecipe;
    }

    public Meal(String mealName, FoodType mealType,
                String appRecipe,
                String entreeRecipe,
                ArrayList<String> sidesRecipes,
                String dessertRecipe) {
        super(mealName);
        this.mealType = mealType;
        this.appRecipe = appRecipe;
        this.entreeRecipe = entreeRecipe;
        this.sidesRecipes = sidesRecipes;
        this.dessertRecipe = dessertRecipe;
    }

    public String getMealName() {
        return super.getName();
    }

    public void setMealName(String mealName) {
        super.setName(mealName);
    }

    public FoodType getMealType() {
        return mealType;
    }

    public void setMealType(FoodType mealType) {
        this.mealType = mealType;
    }

    public String getAppRecipe() {
        if(appRecipe == null)
            appRecipe = new String("");
        return appRecipe;
    }

    public void setAppRecipe(String appRecipe) {
        this.appRecipe = appRecipe;
    }

    public String getEntreeRecipe() {
        if (entreeRecipe == null)
            entreeRecipe = new String("");
        return entreeRecipe;
    }

    public void setEntreeRecipe(String entreeRecipe) {
        this.entreeRecipe = entreeRecipe;
    }

    public ArrayList<String> getSidesRecipes() {

        if(sidesRecipes == null) {
            sidesRecipes = new ArrayList<>();
            sidesRecipes.add(new String(""));
        }
        return sidesRecipes;
    }

    public void setSidesRecipes(ArrayList<String> sidesRecipes) {

        this.sidesRecipes = sidesRecipes;
    }

    public String getDessertRecipe() {
        if (dessertRecipe == null)
            dessertRecipe = new String("");
        return dessertRecipe;
    }

    public void setDessertRecipe(String dessertRecipe) {
        this.dessertRecipe = dessertRecipe;
    }

}
