package com.askew.sean.mealplanner.dailymealplan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.askew.sean.mealplanner.R;
import com.askew.sean.mealplanner.mealrecipesview.MealRecipesViewActivity;
import com.askew.sean.mealplanner.menu.NavigationMenu;
import com.askew.sean.mealplanner.model.CalendarViewDate;
import com.askew.sean.mealplanner.model.DailyMealPlan;
import com.askew.sean.mealplanner.model.UsableItem;
import com.askew.sean.mealplanner.recycler.MealRecyclerAdapter;
import com.askew.sean.mealplanner.tools.AppDebug;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MealRecyclerAdapter.ClickListener {

    private static final int USE_PERIOD_MEALS = 7;
    private AppDebug appDebug;
    private DailyMealPlanMenu mDailyMealPlanMenu;
    private DailyMealPlanListRecyclerAdapter mAdapter;
    private RecyclerView mDailyMealPlanListRecyclerView;
    private NavigationMenu mNavigationMenu;
    private List<DailyMealPlan> monthlyDailyMealPlanList;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        appDebug = AppDebug.getInstance(this,getApplicationContext());
        activity = this;
        createMenus();
        initializeDB();
        initializeRecyclerView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        appDebug.onResumeDb();
        updateRecyclerView(CalendarViewDate.getCalendarDateCalendar());
    }

    private void createMenus() {

        mNavigationMenu = new NavigationMenu(getApplicationContext(),this);
        mDailyMealPlanMenu = new DailyMealPlanMenu(this, getApplicationContext(), R.layout.activity_main2);
        mOnNavigationItemSelectedListener
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
                    default:
                        Log.i(this.getClass().toString(),"@string/unknown_nav");
                        return false;
                }
            }
        };

        BottomNavigationView navigation = findViewById(R.id.navigation_layout_id);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private void initializeDB() {
        UsableItem.setInUsePeriod(USE_PERIOD_MEALS);
    }

    private void initializeRecyclerView() {

        Calendar calendar = Calendar.getInstance();

        monthlyDailyMealPlanList =  getDailyMealPlanList(calendar);

        mDailyMealPlanListRecyclerView = findViewById(R.id.dayRecyclerView);

        mDailyMealPlanListRecyclerView.setItemAnimator(new DefaultItemAnimator());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        mDailyMealPlanListRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new DailyMealPlanListRecyclerAdapter(activity, getApplicationContext(),  monthlyDailyMealPlanList);

        mAdapter.setClickListener(this);

        mDailyMealPlanListRecyclerView.setAdapter(mAdapter);

        DailyMealPlanSnapHelper dailyMealPlanSnapHelper =  new DailyMealPlanSnapHelper();

        dailyMealPlanSnapHelper.attachToRecyclerView(mDailyMealPlanListRecyclerView);

        mDailyMealPlanListRecyclerView.setHasFixedSize(true);

        int today = CalendarViewDate.getTodayDateCalendar().get(Calendar.DAY_OF_MONTH);

        mDailyMealPlanListRecyclerView.getLayoutManager().scrollToPosition(today);

        mDailyMealPlanListRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int position = getCurrentItem();
                    int endPosition = monthlyDailyMealPlanList.size() - 1;
                    if (position == endPosition) {
                        DailyMealPlan  dailyMealPlan = monthlyDailyMealPlanList.get(monthlyDailyMealPlanList.size()-1);
                        Calendar cal = dailyMealPlan.getDateCalendar();
                        updateRecyclerView(monthlyDailyMealPlanList.get(monthlyDailyMealPlanList.size()-1).getDateCalendar());
                        setCurrentItem(1,true);
                    }
                    if (position == 0){
                        DailyMealPlan  dailyMealPlan = monthlyDailyMealPlanList.get(0);
                        Calendar cal = dailyMealPlan.getDateCalendar();
                        updateRecyclerView(monthlyDailyMealPlanList.get(0).getDateCalendar());
                        setCurrentItem(monthlyDailyMealPlanList.size()-2,true);
                    }
                    Log.e("MainActivity", new String("Position " + String.valueOf(position)));
                }
            }});

    }

    public List<DailyMealPlan>  getDailyMealPlanList(Calendar date){
        return  CalendarViewDate.getDailyMealPlanList(date,this);
    }

    private void updateRecyclerView(Calendar date) {

        mAdapter.updateData(getDailyMealPlanList(date));

        mAdapter.notifyDataSetChanged();
    }

    private int getCurrentItem(){
        return ((LinearLayoutManager)mDailyMealPlanListRecyclerView.getLayoutManager())
                .findFirstVisibleItemPosition();
    }

    private void setCurrentItem(int position, boolean smooth){
        if (smooth)
            mDailyMealPlanListRecyclerView.smoothScrollToPosition(position);
        else
            mDailyMealPlanListRecyclerView.scrollToPosition(position);
    }

    public void goToMealRecipeView(View view) {

        Intent intent = new Intent(getApplicationContext(), MealRecipesViewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityIfNeeded(intent, 0);

    }

    @Override
    public void itemClick(View v) {
        goToMealRecipeView(v);

    }

    /*
    public boolean hasPreview() {
        return getCurrentItem() > 0;
    }

    public boolean hasNext() {
        return mDailyMealPlanListRecyclerView.getAdapter() != null &&
                getCurrentItem() < (mDailyMealPlanListRecyclerView.getAdapter().getItemCount()- 1);
    }

    public void preview() {
        int position = getCurrentItem();
        if (position > 0)
            setCurrentItem(position -1, true);
    }

    public void next() {
        RecyclerView.Adapter adapter = mDailyMealPlanListRecyclerView.getAdapter();
        if (adapter == null)
            return;

        int position = getCurrentItem();
        int count = adapter.getItemCount();
        if (position < (count -1))
            setCurrentItem(position + 1, true);
    }
    */


}