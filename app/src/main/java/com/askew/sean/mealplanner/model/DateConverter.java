package com.askew.sean.mealplanner.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateConverter {
    private Date mDate;
    private Calendar mCalendar;
    private static final int MIlLIS_PER_DAY=8640000;
    private static final int HOURS_PER_DAY=24;
    private String mCalDate;
    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

    public DateConverter(Calendar calendar){

        mCalendar = calendar;
        mDate = mCalendar.getTime();
        mCalDate = sdf.format(mDate);
    }

    private long millisToDayMillis(long epochMillis){

        long  millisMillis = epochMillis;
        return millisMillis - (millisMillis/HOURS_PER_DAY);
    }

    public DateConverter(Date date){

        mDate = date;
        mCalendar = dateToCalendar(mDate);
        mCalDate = sdf.format(date);
    }

    public DateConverter (String date){
        mCalDate = date;
        String[] dateArray = date.split("/");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.valueOf(dateArray[2]),
                Integer.valueOf(dateArray[0]),
                Integer.valueOf(dateArray[1]));
        mCalendar = calendar;
        mDate = calendar.getTime();
    }

    public DateConverter(long milliseconds) {
        mDate = new Date(milliseconds);
        mCalendar = dateToCalendar(mDate);
        mCalDate = sdf.format(mDate);
    }

    private Calendar dateToCalendar(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public Calendar getDateTimeCalendar() {
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(mDate);
        return calendar;
    }

    public String getDateTimeString(){

       return mCalDate;
    }

    public void add(int field, int amount){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mDate.getTime());
        calendar.add(field,amount);
        mDate.setTime(calendar.getTimeInMillis());
        mCalDate = sdf.format(mDate);

    }



}
