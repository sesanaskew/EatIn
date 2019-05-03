package com.askew.sean.mealplanner.model;

public class Ingredient {
    private String mealName;
    private ShoppingGroup shoppingGroup;
    private String brandName;    //Brand name from recipe if applicable
    private String genericName;  //Generic name
    private Integer amountNeeded;      //Numerical amount of ingredient measured in scale.
    private String scale;        //Oz fl oz etc..
    private Integer amountPerUnit;  //amount per unit sold
    private String scalePerUnit;
    private UnitConversion unitConversion;

    public Ingredient(String mealName, String brandName, String genericName, Integer amountNeeded, String scale, Integer amountPerUnit, String scalePerUnit) {
        this.mealName = mealName;
        this.brandName = brandName;
        this.genericName = genericName;
        this.amountNeeded = amountNeeded;
        this.scale = scale;
        this.amountPerUnit = amountPerUnit;
        this.scalePerUnit = scalePerUnit;
    }

    public Ingredient(){
        brandName = new String("");
        genericName = new String("");
        amountNeeded = 0;
        scale = new String("");
        amountPerUnit = 0;
        scalePerUnit = new String("");
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public Integer getAmountNeeded() {
        return amountNeeded;
    }

    public void setAmountNeeded(Integer amountNeeded) {
        this.amountNeeded = amountNeeded;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public Integer getAmountPerUnit() {
        return amountPerUnit;
    }

    public void setAmountPerUnit(Integer amountPerUnit) {
        this.amountPerUnit = amountPerUnit;
    }

    public String getScalePerUnit() {
        return scalePerUnit;
    }

    public void setScalePerUnit(String scalePerUnit) {
        this.scalePerUnit = scalePerUnit;
    }

}




