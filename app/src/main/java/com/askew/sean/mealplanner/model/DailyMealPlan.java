package com.askew.sean.mealplanner.model;

import com.askew.sean.mealplanner.database.MealDataManager;

import java.util.Calendar;
import java.util.Date;

public class DailyMealPlan extends UsableItem {


    private long key;
    private DateConverter date;
    private String breakfast;
    private String lunch;
    private String dinner;


    public DailyMealPlan(){

    }

    public DailyMealPlan(String breakfast, String lunch, String dinner,Date date){
        super();
        super.setUseDate(date);
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.date = new DateConverter(date);
    }

    public DailyMealPlan(long key, String breakfast, String lunch, String dinner,String date){
        super();
        this.key = key;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.date = new DateConverter(date);
    }

    public DailyMealPlan(Date date){
        super();
        this.date = new DateConverter(date);
    }

    public String getDate() {

        return date.getDateTimeString();
    }

    public Calendar getDateCalendar(){

        return date.getDateTimeCalendar();
    }

    public String getBreakfast() {

        return breakfast;
    }

    public void setBreakfast(String breakfast) {

        this.breakfast = breakfast;
    }

    public String getLunch() {

        return lunch;
    }

    public void setLunch(String lunch) {

        this.lunch = lunch;
    }

    public String getDinner() {

        return this.dinner;
    }

    public void setDinner(String dinner) {

        this.dinner = dinner;
    }

    public void setInUse(boolean inUse){

        MealDataManager mealDataManager = new MealDataManager();
    }

    public long  getKey() {
        return key;
    }

    public void setKey(long key) {

        this.key = key;
    }

}
