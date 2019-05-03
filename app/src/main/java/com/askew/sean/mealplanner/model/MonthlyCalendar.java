
package com.askew.sean.mealplanner.model;

import java.util.Calendar;

public class MonthlyCalendar{

    private Calendar mCalendar;

    public MonthlyCalendar(){
        mCalendar = Calendar.getInstance();
    }

    public MonthlyCalendar(Calendar calendar){
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(calendar.getTime());
    }

    public MonthlyCalendar firstDayOfMonth(){
       Calendar cal = Calendar.getInstance();
        cal.setTime(mCalendar.getTime());
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        MonthlyCalendar monthlyCalendar = new MonthlyCalendar(cal);
        return monthlyCalendar;
    }

    public MonthlyCalendar lastDayOfMonth(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(mCalendar.getTime());
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        MonthlyCalendar monthlyCalendar = new MonthlyCalendar(cal);
        return monthlyCalendar;
    }

    public  int getDayOfMonth(){
        return mCalendar.get(Calendar.DAY_OF_MONTH);
    }

    public  int getDayOfYear(){
        return mCalendar.get(Calendar.DAY_OF_YEAR);
    }

    public  int getMonth(){
        return mCalendar.get(Calendar.MONTH);
    }

    public  int getYear() {
        return mCalendar.get(Calendar.YEAR);
    }

    public Calendar getCalendar(){
        return mCalendar;
        }

    public MonthlyCalendar lastDayOfLastMonth(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(mCalendar.getTime());
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        MonthlyCalendar monthlyCalendar = new MonthlyCalendar(cal);
        return monthlyCalendar;
    }

    public MonthlyCalendar firstDayOfNextMonth(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(mCalendar.getTime());
        cal.add(Calendar.MONTH, +1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        MonthlyCalendar monthlyCalendar = new MonthlyCalendar(cal);
        return monthlyCalendar;
    }

    public MonthlyCalendar nextDate(){
        mCalendar.add(Calendar.DAY_OF_YEAR,1);
        MonthlyCalendar monthlyCalendar = new MonthlyCalendar(mCalendar);
        return monthlyCalendar;
    }

    public MonthlyCalendar prevDate(){
        mCalendar.set(Calendar.DAY_OF_YEAR,mCalendar.get(Calendar.DAY_OF_YEAR-1));
        MonthlyCalendar monthlyCalendar = new MonthlyCalendar(mCalendar);
        return monthlyCalendar;
    }

    public   int nextDayOfYear(){
        mCalendar.set(Calendar.DAY_OF_YEAR,mCalendar.get(Calendar.DAY_OF_YEAR+1));
        return mCalendar.get(Calendar.DAY_OF_YEAR);

    }

    public   int prevDayOfYear() {
        mCalendar.set(Calendar.DAY_OF_YEAR, mCalendar.get(Calendar.DAY_OF_YEAR - 1));
        return mCalendar.get(Calendar.DAY_OF_YEAR);
    }

    public   String nextDayOfYearString(){
        mCalendar.set(Calendar.DAY_OF_YEAR,mCalendar.get(Calendar.DAY_OF_YEAR+1));
        DateConverter dateConverter = new DateConverter(mCalendar);
        return dateConverter.getDateTimeString();

    }


    public   String prevDayOfYearString() {
        mCalendar.set(Calendar.DAY_OF_YEAR, mCalendar.get(Calendar.DAY_OF_YEAR - 1));
        DateConverter dateConverter = new DateConverter(mCalendar);
        return dateConverter.getDateTimeString();
    }
}
