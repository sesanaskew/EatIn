package com.askew.sean.mealplanner.model;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.askew.sean.mealplanner.database.DailyMealDataManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarViewDate {
    private static CalendarViewDate calendarViewDate;
    private static Calendar todayDate;
    private static Calendar calendarDate;

    private static Calendar calendarDateTracker;
    private static Calendar calendarMonthStart;
    private static Calendar calendarMonthEnd;
    private static String   mealName;
    private static DateConverter dateConverter;

    private AppCompatActivity activity;

    private CalendarViewDate(Calendar todayDate, AppCompatActivity activity){
        CalendarViewDate.todayDate = todayDate;
        this.activity = activity;
        this.mealName = new String("");

    };

    public CalendarViewDate getInstance(AppCompatActivity activity){
        if (calendarViewDate == null){
            Calendar todayCalendar = Calendar.getInstance();
            calendarViewDate = new CalendarViewDate(todayCalendar,activity);
        }

        return calendarViewDate;
    }

    public static Calendar getTodayDateCalendar() {

        if (todayDate == null){
            todayDate = Calendar.getInstance();
        }
        return todayDate;
    }

    public static Date getTodayDateDate() {
        return todayDate.getTime();
    }

    public static String getTodayDateString() {
        DateConverter dateConverter = new DateConverter(todayDate);
        return dateConverter.getDateTimeString();
    }

    public static void setTodayDate(Calendar todayDate) {
        CalendarViewDate.todayDate = todayDate;
    }

    public static Calendar getCalendarDateCalendar() {
        return calendarDate;
    }

    public static Date getCalendarDateDate() {
        return calendarDate.getTime();
    }

    public static String getCalendarDateString() {
        DateConverter dateConverter = new DateConverter(calendarDate);
        return dateConverter.getDateTimeString();
    }

    public static void setCalendarViewDate(Calendar calendarDate) {
        CalendarViewDate.calendarDate = calendarDate;
    }


    public static void setCalendarViewDate(String date){
        DateConverter dateConverter = new DateConverter(date);
        calendarDate = dateConverter.getDateTimeCalendar();
    }

    public static Calendar nextDate(){
        calendarDate.add(Calendar.DAY_OF_YEAR,1);
        return calendarDate;
    }

    public static Calendar prevDate(){
        calendarDate.set(Calendar.DAY_OF_YEAR,calendarDate.get(Calendar.DAY_OF_YEAR-1));
        return calendarDate;
    }

    public static int nextDayOfYear(){
        calendarDate.set(Calendar.DAY_OF_YEAR,calendarDate.get(Calendar.DAY_OF_YEAR+1));
        return calendarDate.get(Calendar.DAY_OF_YEAR);

    }

    public static int prevDayOfYear() {
        calendarDate.set(Calendar.DAY_OF_YEAR, calendarDate.get(Calendar.DAY_OF_YEAR - 1));
        return calendarDate.get(Calendar.DAY_OF_YEAR);
    }

    public static String nextDayOfYearString(){
        calendarDate.set(Calendar.DAY_OF_YEAR,calendarDate.get(Calendar.DAY_OF_YEAR+1));
        dateConverter = new DateConverter(calendarDate);
        return dateConverter.getDateTimeString();

    }

    public static String prevDayOfYearString() {
        calendarDate.set(Calendar.DAY_OF_YEAR, calendarDate.get(Calendar.DAY_OF_YEAR - 1));
        dateConverter = new DateConverter(calendarDate);
        return dateConverter.getDateTimeString();
    }

    public static List<DailyMealPlan> getDailyMealPlanList(Calendar calendar, Activity activity) {

        MonthlyCalendar curCalDate = new MonthlyCalendar(calendar);

        CalendarViewDate.setCalendarViewDate(curCalDate.getCalendar());
        DailyMealDataManager dailyMealDataManager = new DailyMealDataManager(activity);
        List<DailyMealPlan> monthOfMealPlans = new ArrayList<>();

        int listDay = curCalDate.lastDayOfLastMonth().getDayOfYear();
        MonthlyCalendar tCurCalDate = curCalDate.lastDayOfLastMonth();
        Calendar tCurCal = tCurCalDate.getCalendar();

        String listDaString = new DateConverter(curCalDate.lastDayOfLastMonth().getCalendar()).getDateTimeString();
        int endDay = curCalDate.firstDayOfNextMonth().getDayOfYear();
        String endDayString = new DateConverter(curCalDate.firstDayOfNextMonth().getCalendar()).getDateTimeString();
        curCalDate = curCalDate.lastDayOfLastMonth();

        while (listDay <= endDay) {
            Calendar cal = (curCalDate.getCalendar());
            DailyMealPlan dailyMealPlan = getDailyMealPlan(cal,activity);
            monthOfMealPlans.add(dailyMealPlan);
            curCalDate = curCalDate.nextDate();
            listDay = curCalDate.getDayOfYear();
        }

        return monthOfMealPlans;
    }

    public static DailyMealPlan getDailyMealPlan(Calendar curCalDate, Activity activity){
        DailyMealDataManager dailyMealDataManager = new DailyMealDataManager(activity);

        DailyMealPlan dailyMealPlan = dailyMealDataManager.get(new DateConverter(curCalDate).getDateTimeString());
        DailyMealPlan returnPlan;
        if (dailyMealPlan != null) {
           returnPlan = dailyMealPlan;
        } else {
            DailyMealPlan dailyMealPlanBlank = new DailyMealPlan(
                    new String(""),
                    new String(""),
                    new String(""),
                    curCalDate.getTime());
            returnPlan = dailyMealPlanBlank;
        }

        return returnPlan;
}

    public static List<Calendar>  getAllEventDates(AppCompatActivity activity){
        Calendar lCalendar = Calendar.getInstance();
        int month = lCalendar.get(Calendar.MONTH);
        int curMonth = month;
        lCalendar.set(Calendar.DAY_OF_MONTH,1);
        DailyMealDataManager dailyMealDataManager = new DailyMealDataManager(activity);
        List<Calendar> calendars = new ArrayList<>();
        while (curMonth == month) {
            DateConverter dateConverter = new DateConverter(lCalendar);
            DailyMealPlan dailyMealPlan = dailyMealDataManager.get(dateConverter.getDateTimeString());
            if (dailyMealPlan != null) {
                Calendar tcal = Calendar.getInstance();
                tcal.setTime(lCalendar.getTime());
                calendars.add(tcal);
            }
            lCalendar.add(Calendar.DAY_OF_YEAR, 1);
            month = lCalendar.get(Calendar.MONTH);
        }
        return calendars;
    }

    public static String getMealName() {
        return mealName;
    }

    public static void setMealName(String mealName) {
        CalendarViewDate.mealName = mealName;
    }
}
