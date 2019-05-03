package com.askew.sean.mealplanner.dailymealplancalendar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.askew.sean.mealplanner.R;
import com.askew.sean.mealplanner.database.DailyMealDataManager;
import com.askew.sean.mealplanner.menu.NavigationMenu;
import com.askew.sean.mealplanner.model.CalendarViewDate;
import com.askew.sean.mealplanner.model.DailyMealPlan;
import com.askew.sean.mealplanner.model.DateConverter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DailyMealPlanCalendarActivity extends AppCompatActivity implements DailyMealPlanCalendarMenu.DailyMealPlanCalendarInt {

    private NavigationMenu navigationMenu;
    private DailyMealPlanCalendarMenu mDailyMealPlanCalendarMenu;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.nav_home_btn:
                    NavigationMenu.goToHome(getCurrentFocus());
                    return true;
                case R.id.nav_view_edit_meals_recipes:
                    NavigationMenu.goToViewEditMealsRecipes(getCurrentFocus());
                    return true;
                case R.id.nav_create_mealplan:
                    NavigationMenu.goToCreateMealPlan(getCurrentFocus());
                    return true;
                case R.id.nav_grocery_list:
                    NavigationMenu.goToViewGroceryList(getCurrentFocus());
                    return true;
                case R.id.nav_settings:
                    NavigationMenu.goToConfigSettings(getCurrentFocus());
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_mealplan_calendar2);
        BottomNavigationView navigation = findViewById(R.id.navigation_layout_id);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        createMenu();
        onCreateCalendarView();
    }

    @Override
    protected void onResume(){
        super.onResume();;
    }

    private List<Calendar> getSelectedDays() {
        List<Calendar> calendars = new ArrayList<>();
        return calendars;
    }

    private void createMenu(){
        navigationMenu = new NavigationMenu(getApplicationContext(),this);
        mDailyMealPlanCalendarMenu = new DailyMealPlanCalendarMenu(this,getApplicationContext(), R.layout.activity_daily_mealplan_calendar2);
        registerMenu();

    }

    @Override
    public void goToPickDates(View view) {
        mDailyMealPlanCalendarMenu.goToPickDates(view);
    }

    private void onCreateCalendarView() {
        CalendarView calendarView = findViewById(R.id.viewCalendarView);
        updateCalDailyMealPlanEvents();
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                dayClick(eventDay);
            }
        });
    }

    public void dayClick(EventDay eventDay){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(eventDay.getCalendar().getTime());
            //NavMenu.setsCalendarViewDate(calendar);
            CalendarViewDate.setCalendarViewDate(calendar);
            NavigationMenu.goToHome(getCurrentFocus());
    }

    private void updateCalDailyMealPlanEvents(){
        Calendar lCalendar = Calendar.getInstance();
        int month = lCalendar.get(Calendar.MONTH);
        int curMonth = month;
        lCalendar.set(Calendar.DAY_OF_MONTH,1);
        DailyMealDataManager dailyMealDataManager = new DailyMealDataManager(getApplicationContext());
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
        updateCalendarView(calendars);
    }

    private void updateCalendarView(List<Calendar> calendars) {
        CalendarView calendarView = findViewById(R.id.viewCalendarView);
        List<EventDay> mealEvents = new ArrayList<>();
        for (Calendar calendar : calendars) {
            mealEvents.add(new EventDay(calendar, R.drawable.mealplan_generated));
        }
        calendarView.setEvents(mealEvents);
    }

    public void registerMenu(){
        registerDailyMealPlanCalendarMenu();
        //registerNavMenu();
    }

    public void registerDailyMealPlanCalendarMenu(){
        registerPickDates();
    }

    public void registerPickDates(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPickDates(view);
            }
        };

        mDailyMealPlanCalendarMenu.registerPickDates(listener);
    }

}



