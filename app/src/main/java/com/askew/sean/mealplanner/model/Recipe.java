package com.askew.sean.mealplanner.model;

import java.util.List;

public class Recipe extends UsableItem {
    private String mName;
    private String mIngredients;
    private String mServes;
    private String mCookingInstructions;
    private String mPrepTime;
    private String mCookTime;
    private byte[] mImageInBytes;
    private FoodType mFoodType;
    private String servingSize;



    public Recipe(int key, String name, FoodType foodType){
        super(key,name);
        this.mFoodType = foodType;
    }

    public Recipe(String name, FoodType foodType){
        super(name);
        this.mFoodType = foodType;
    }

    public Recipe(String name,
                  FoodType foodType,
                  String ingredients,
                  String servings,
                  String prepTIme,
                  String cookTime,
                  String cookingInstructions,
                  byte[] imageInBytes){
        super(name);
        this.mName = name;
        this.mFoodType = foodType;
        this.mServes = servings;
        this.mIngredients = ingredients;
        this.mPrepTime = prepTIme;
        this.mCookTime = cookTime;
        this.mCookingInstructions = cookingInstructions;
        this.mImageInBytes = imageInBytes;
    }


    public String getName(){
       return super.getName();
    }

    public FoodType getFoodType() {
        return mFoodType;
    }

    public void setFoodType(FoodType foodType) {
        this.mFoodType = foodType;
    }

    public String getServingSize() {
        return this.mServes;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    public String getIngredients() {

        return mIngredients;
    }

    public void setIngredients(List<String> ingredients) {

        StringBuilder stringBuilder = new StringBuilder();
        for (String ingredient : ingredients) {
            stringBuilder.append(ingredient).append(System.lineSeparator());
        }
        this.mIngredients = stringBuilder.toString();
    }

    public void ssetIngredients(String ingredients){
        this.mIngredients = ingredients;
    }

    public String getCookingInstructions() {
        return mCookingInstructions;
    }

    public void setCookingInstructions(String cookingInstructions) {
        this.mCookingInstructions = cookingInstructions;
    }

    public String getPrepTime() {
        return mPrepTime;
    }

    public void setPrepTime(String prepTime) {
        this.mPrepTime = prepTime;
    }

    public String getCookTime() {
        return mCookTime;
    }

    public void setCookTime(String cookTime) {
        this.mCookTime = cookTime;
    }

    public byte[] getPicture() {
        return mImageInBytes;
    }

    public void setPicture(byte[] imageInBytes) {
        this.mImageInBytes = imageInBytes;
    }
}
