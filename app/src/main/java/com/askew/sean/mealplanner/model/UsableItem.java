package com.askew.sean.mealplanner.model;

import java.util.Calendar;
import java.util.Date;

public class UsableItem {
    static private int daysInUsePeriod;
    private String name = "";
    private long key = 0;
    private boolean inUse = false;
    private DateConverter useDate;
    private DateConverter futureDate;

    UsableItem(long key, String name) {
        super();
        this.name = name;
        this.key = key;
    }

    UsableItem(String name) {
        super();
        this.name = name;
    }

    public UsableItem() {
    }

    public Calendar getUseDate() {
        return useDate.getDateTimeCalendar();
    }

    public void setUseDate(Date useDate) {
        this.useDate = new DateConverter(useDate);
    }

    static public void setInUsePeriod(int daysInUsePeriod) {
        UsableItem.daysInUsePeriod = daysInUsePeriod;
    }

    public String getUseDateString(){
        return useDate.getDateTimeString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setInUse(boolean inUse) {
        if (inUse) {
            futureDate = new DateConverter(useDate.getDateTimeString());
            futureDate.add(Calendar.DAY_OF_YEAR, daysInUsePeriod);
        } else {
            futureDate = null;
        }
        this.inUse = inUse;
    }

    public boolean isAvailable() {
        DateConverter currentDate = new DateConverter( Calendar.getInstance().getTimeInMillis());

        if (this.inUse) {
            if (currentDate.getDateTimeCalendar().getTimeInMillis() < futureDate.getDateTimeCalendar().getTimeInMillis()) {
                return false;
            } else {
                futureDate = null;
                useDate = null;
            }
            this.inUse = false;
        }
        return true;
    }
}

