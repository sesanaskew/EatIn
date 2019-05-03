package com.askew.sean.mealplanner.model;

import android.content.Context;

import com.askew.sean.mealplanner.database.DailyMealDataManager;
import com.askew.sean.mealplanner.database.MealPlanDatesDataManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MealPlan {
    private static final String FORMAT = "MM/dd/yyyy";
    private static String currentPlan;
    private long key;
    private String  mStartDate;
    private String mEndDate;
    private SimpleDateFormat sdf;
    private String mMealPlanner;
    private List<DailyMealPlan> mPlan;
    private Context mContext;
    private List<Calendar> mPlanDates;
    private MealPlanDatesDataManager mealPlanDatesDataManager;
    private DailyMealDataManager dailyMealDataManager;


    public MealPlan(Context context){
        dailyMealDataManager = new DailyMealDataManager(context);
        mealPlanDatesDataManager = new MealPlanDatesDataManager(context);

    }
    public MealPlan(List<Calendar> planDates,Context context){
        this.mContext = context;
        this.mPlanDates = planDates;
        dailyMealDataManager = new DailyMealDataManager(context);
        mealPlanDatesDataManager = new MealPlanDatesDataManager(context);
        storeMealPlan(planDates);

    }

    private void getMealPlanDates(){
        sdf  = new SimpleDateFormat(FORMAT);
        mStartDate = sdf.format(mPlan.get(0).getUseDate().getTime());
        mEndDate = sdf.format(mPlan.get(mPlan.size()-1).getUseDate().getTime());
        StringBuilder sb = new StringBuilder(mStartDate);
            sb.append("-").append(mEndDate);
        mMealPlanner = sb.toString();
    }
    private void storeMealPlan(List<Calendar> mealPlan) {
        List<String> mealPlanDateString = new ArrayList<>();
        for (Calendar calendar : mealPlan) {
            DateConverter dateConverter = new DateConverter(calendar);
            mealPlanDateString.add(dateConverter.getDateTimeString());
        }
        mealPlanDatesDataManager.removeAll();
        mealPlanDatesDataManager.store(mealPlanDateString);
    }
    public long getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public  void addToPlan(DailyMealPlan dailyMealPlan){
        //see if it already exists in plan, if so remove old then add
    }

    public List<DailyMealPlan> getPlan() {

        DailyMealDataManager dailyMealDataManager = new DailyMealDataManager(mContext);
        List<Date> dates = sortDates(mPlanDates);
        mPlan =  dailyMealDataManager.getRandomList(dates);
        dailyMealDataManager.store(mPlan);
        return mPlan;
    }

    public List<Date> sortDates(List<Calendar> calendars){
        List<Date> dates = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Calendar calendar : mPlanDates) {
            String date = sdf.format(calendar.getTime());
            try {
                dates.add(sdf.parse(date));
            }catch(Exception e) {
                Logger logger = Logger.getLogger("EatIn");
                logger.log(Level.SEVERE, e.toString());
            }
        }
        Collections.sort(dates);
        return dates;
    }

    public void setPlan(List<DailyMealPlan> plan) {
        this.mPlan = plan;
    }

    public static String getCurrentPlanKey() {
        return currentPlan;
    }

    public static void setCurrentPlanKey(String currentPlan) {
        MealPlan.currentPlan = currentPlan;
    }
}
