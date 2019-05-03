package com.askew.sean.mealplanner.generatedailymealplanactivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.askew.sean.mealplanner.R;
import com.askew.sean.mealplanner.database.MealPlanDatesDataManager;
import com.askew.sean.mealplanner.menu.NavigationMenu;
import com.askew.sean.mealplanner.model.CalendarViewDate;
import com.askew.sean.mealplanner.model.MealPlan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.askew.sean.mealplanner.menu.NavigationMenu.goToCreateMealPlan;

public class GenDailyMealPlanActivity extends AppCompatActivity implements GenDailyMealPlanMenu.GenDailyMealPlanMenuInt {
   // private NavMenu mNavMenu;
    private NavigationMenu navigationMenu;
    private GenDailyMealPlanMenu mGenDailyMealPlanMenu;
    private MealPlanDatesDataManager mMealPlanDatesDataManager;
    private List<Calendar> mMealCalendarList;
 /*   private ImageButton mGenMealPlanBtn;
    private ImageButton mPickDatesBtn;
    private TextView mGenMealPlanTextVw;
*/
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
        setContentView(R.layout.activity_gen_daily_mealplan2);
        createMenu();
        createCalendarView();

    }

    @Override
    protected void onResume(){
        super.onResume();;
    }
    private void createCalendarView() {
        List<Calendar> calendars = CalendarViewDate.getAllEventDates(this  );
        updateCalendarView(calendars);
        mMealPlanDatesDataManager = new MealPlanDatesDataManager(getApplicationContext());
    }
    private void createMenu(){
        //mNavMenu = new NavMenu(this, getApplicationContext(),R.layout.activity_gen_daily_mealplan);
        navigationMenu = new NavigationMenu(getApplicationContext(),this);
        mGenDailyMealPlanMenu = new GenDailyMealPlanMenu(this,getApplicationContext(), R.layout.activity_gen_daily_mealplan2);
        BottomNavigationView navigation = findViewById(R.id.navigation_layout_id);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        registerMenu();
    }

    public void registerMenu(){
        registerGenDailyMealPlanMenu();
    }
    public void registerGenDailyMealPlanMenu(){
        registerPickDates();
        registerGenDailyMealPlan();

    }
  /*  public void registerNavMenu(){
        registerHome();
        registerFav();
        registerCreateMealPlan();
        registerViewEditMealRecipes();
        registerViewGroceryList();
    }


    //Nav Menu
    public void goToHome(View view) {

        mNavMenu.goToHome(view);
    }
    public void goToFav(View view) {

        mNavMenu.goToFav(view);
    }
    public void goToCreateMealPlan(View view) {

        mNavMenu.goToCreateMealPlan(view);
    }
    public void goToViewEditRecipes(View view) {

        mNavMenu.goToViewEditMealsRecipes(view);
    }
    public void goToViewGroceryList(View view) {

        mNavMenu.goToViewEditMealsRecipes(view);
    }
    public void registerHome() {

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if (! (this instanceof MainActivity)){
                goToHome(view);
                // }}
            }
        };

        mNavMenu.registerHome(listener);
    }
    public void registerFav() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if (! (this instanceof MainActivity)){
                goToHome(view);
                // }}
            }
        };

        mNavMenu.registerFav(listener);
    }
    public void registerViewEditMealRecipes(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if (! (this instanceof MainActivity)){
                goToViewEditRecipes(view);
                // }}
            }
        };

        mNavMenu.registerViewEditMealsRecipes(listener);
    }
    public void registerCreateMealPlan(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if (! (this instanceof MainActivity)){
                goToCreateMealPlan(view);
                // }}
            }
        };

        mNavMenu.registerViewEditMealsRecipes(listener);
    }
    public void registerViewGroceryList(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if (! (this instanceof MainActivity)){
                goToViewGroceryList(view);
                // }}
            }
        };

        mNavMenu.registerViewGroceryList(listener);
    }
*/
    //GenDailyMealPlanMenu
    public void goToPickDates(View view) {

        mGenDailyMealPlanMenu.goToPickDates(view);
    }
    public void goToGenDailyMealPlan(View view){
        genMealPlan();
        mGenDailyMealPlanMenu.goToGenDailyMealPlan(view);
    }

    public void registerGenDailyMealPlan() {

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if (! (this instanceof MainActivity)){
                goToCreateMealPlan(view);
                // }}
            }
        };
        mGenDailyMealPlanMenu.registerGenDailyMealPlan(listener);
    }
    public void registerPickDates(){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPickDates(view);
            }
        };

        mGenDailyMealPlanMenu.registerPickDates(listener);
    }

    //Generating Meal Plan
    private void genMealPlan(){
        CalendarView calendarView = findViewById(R.id.calendarView);
        mMealCalendarList = calendarView.getSelectedDates();
        List<EventDay> newEvents =  getSelectedDateEvents(mMealCalendarList);
        calendarView.setEvents(newEvents);
        generateMealList(this.getCurrentFocus());
        List<Calendar> calendars = CalendarViewDate.getAllEventDates(this);
        updateCalendarView(calendars);
    }
    private List<EventDay> getSelectedDateEvents(List<Calendar> mealPlanDates){

        List<EventDay> newEvents = new ArrayList<>();
        for (Calendar calendar : mealPlanDates) {
            newEvents.add(new EventDay(calendar, R.drawable.date_picked));
        }
        return newEvents;
    }
    public void generateMealList(View view) {

        MealPlan mealPlan = new MealPlan(mMealCalendarList, getApplicationContext());
        mealPlan.getPlan();
        updateCalendarView(mMealCalendarList);

    }
    private void updateCalendarView(List<Calendar> calendars) {
        setContentView(R.layout.activity_gen_daily_mealplan2);
        CalendarView calendarView = this.findViewById(R.id.calendarView);
        List<EventDay> mealEvents = new ArrayList<>();
        for (Calendar calendar : calendars) {
            mealEvents.add(new EventDay(calendar, R.drawable.mealplan_generated));
        }
        calendarView.setEvents(mealEvents);
    }
}
/*
        mNavMenu = new NavMenu(this, getApplicationContext(),R.layout.activity_gen_daily_mealplan);
        mGenDailyMealPlanMenu = new GenDailyMealPlanMenu(this, getApplicationContext(),R.layout.activity_gen_daily_mealplan);
        onCreateButtons();
        onCreateCalendarView();
    }

    private void onCreateButtons() {
        mGenMealPlanBtn = findViewById(R.id.genMealPlanBtn);
        mGenMealPlanTextVw = findViewById(R.id.genMealPlanTxtView);
        mGenMealPlanBtn.setVisibility(View.VISIBLE);
        mGenMealPlanTextVw.setVisibility(View.VISIBLE);

        mPickDatesBtn = findViewById(R.id.pickDatesBtn);
        mPickDatesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPickDates(view);
            }
        });
    }

    public void goToPickDates(View view){
        mGenMealPlanBtn = findViewById(R.id.genMealPlanBtn);
        mGenMealPlanTextVw = findViewById(R.id.genMealPlanTxtView);
        mGenMealPlanBtn.setVisibility(View.VISIBLE);
        mGenMealPlanTextVw.setVisibility(View.VISIBLE);
    }

    public void goToGenMealPlan(View view){
        CalendarView calendarView = findViewById(R.id.calendarView);
        mMealPlanDates = calendarView.getSelectedDates();
        Calendar curCalendar = Calendar.getInstance();
        List<EventDay> newEvents = new ArrayList<>();
        for (Calendar calendar : mCalendars) {
            newEvents.add(new EventDay(calendar, R.drawable.date_picked));
        }
        calendarView.setEvents(newEvents);
        generateMealList(this.getCurrentFocus());
        setPlannedDates();
        mGenMealPlanBtn = findViewById(R.id.genMealPlanBtn);
        mGenMealPlanTextVw = findViewById(R.id.genMealPlanTxtView);
        mGenMealPlanBtn.setVisibility(View.VISIBLE);
        mGenMealPlanTextVw.setVisibility(View.VISIBLE);
        mNavMenu.goToCreateMealPlan(view);

    }

    private void onCreateCalendarView() {
        CalendarView calendarView = findViewById(R.id.calendarView);
        Calendar curCalendar = Calendar.getInstance();
        setPlannedDates();
    }

    private void setPlannedDates(){
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

    public void generateMealList(View view) {

        MealPlan mealPlan = new MealPlan(mCalendars, getApplicationContext());
        mealPlan.getPlan();

        updateCalendarView(mCalendars);

    }

    private void updateCalendarView(List<Calendar> calendars) {
        setContentView(R.layout.activity_gen_daily_mealplan);
        CalendarView calendarView = this.findViewById(R.id.calendarView);
        List<EventDay> mealEvents = new ArrayList<>();
        for (Calendar calendar : calendars) {
            mealEvents.add(new EventDay(calendar, R.drawable.mealplan_generated));
        }
        calendarView.setEvents(mealEvents);
    }

}*/


