package com.askew.sean.mealplanner.generatedailymealplanactivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.askew.sean.mealplanner.R;
import com.askew.sean.mealplanner.dailymealplancalendar.DailyMealPlanCalendarActivity;

import java.util.Calendar;

public class GenDailyMealPlanMenu {
    private AppCompatActivity mAppCompatActivity;
    private Context mContext;
    private int mContentView;
    private ImageButton mPickDatesBtn;
    private ImageButton mGenMealPlanBtn;
    static private Calendar calendar;

    private GenDailyMealPlanMenu() {
        super();
    }

    public GenDailyMealPlanMenu(AppCompatActivity activity, Context context, int contentView) {

        this.mAppCompatActivity = activity;
        this.mContext = context;
        this.mContentView = contentView;
        mAppCompatActivity.setContentView(mContentView);
        initDailyMealPlanMenu();
    }

    public void initDailyMealPlanMenu() {
        mAppCompatActivity.setContentView(mContentView);
        mPickDatesBtn = mAppCompatActivity.findViewById(R.id.pickDatesBtn);
        mPickDatesBtn.setSelected(false);
        mGenMealPlanBtn = mAppCompatActivity.findViewById(R.id.genMealPlanBtn);
        mGenMealPlanBtn.setSelected(false);
    }

    public void goToPickDates(View view) {

        mPickDatesBtn.setSelected(true);
        mGenMealPlanBtn.setSelected(false);
        Intent intent = new Intent(mContext, GenDailyMealPlanActivity.class); //(mContext, mActivity);
        mAppCompatActivity.startActivity(intent);
    }

    public void registerPickDates(View.OnClickListener listener) {
        mPickDatesBtn.setOnClickListener(listener);
    }

    public void goToGenDailyMealPlan(View view) {
        mGenMealPlanBtn.setSelected(true);
        mPickDatesBtn.setSelected(false);
        Intent intent = new Intent(mContext, DailyMealPlanCalendarActivity.class); //(mContext, mActivity);
        mAppCompatActivity.startActivity(intent);
    }

    public void registerGenDailyMealPlan(View.OnClickListener listener) {
        mGenMealPlanBtn.setOnClickListener(listener);
    }

    public interface GenDailyMealPlanMenuInt {
        void goToPickDates(View view);

        void registerPickDates();

        void goToGenDailyMealPlan(View view);

        void registerGenDailyMealPlan();

    }
}







    /*
    private AppCompatActivity mAppCompatActivity;
    private Context mContext;
    private int mContentView;
    private ImageButton mPickDatesBtn;
    private ImageButton mGenMealPlanBtn;
    static private Calendar calendar;

    private GenDailyMealPlanMenu(){
        super();
    }

    public GenDailyMealPlanMenu(AppCompatActivity activity, Context context, int contentView) {

        this.mAppCompatActivity = activity;
        this.mContext = context;
        this.mContentView = contentView;

        mAppCompatActivity.setContentView(mContentView);
        createButtons();
    }

    public void goToPickDates(View view) {
        setMealPlanButtonIcons();
        mPickDatesBtn.setImageResource(R.drawable.greenaddnew24);
        GenDailyMealPlanActivity genDailyMealPlanActivity =  (GenDailyMealPlanActivity) mAppCompatActivity;
        genDailyMealPlanActivity.goToPickDates(view);
    }

    private void setMealPlanButtonIcons(){
        mPickDatesBtn.setImageResource(R.drawable.icons8addnew24);
        mGenMealPlanBtn.setImageResource(R.drawable.icons8list24);

    }

    public void goToGenMealPlan(View view){
        setMealPlanButtonIcons();
        mGenMealPlanBtn.setImageResource(R.drawable.greenlist24);
        GenDailyMealPlanActivity genDailyMealPlanActivity =  (GenDailyMealPlanActivity) mAppCompatActivity;
        genDailyMealPlanActivity.goToGenMealPlan(view);

    }

    private void createButtons() {
        mPickDatesBtn = mAppCompatActivity.findViewById(R.id.homeBtn) ;
        mGenMealPlanBtn =  mAppCompatActivity.findViewById(R.id.viewEditRecipesBtn);


        mPickDatesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (! (mAppCompatActivity instanceof MainActivity)){
                   // goToPickDates(view);
            }}
        });

        mGenMealPlanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (! (mAppCompatActivity instanceof GenDailyMealPlanActivity)){
                goToGenMealPlan(view);
            }}
        });


    }

    public interface MealPlanMenuInt {
        public void goToPickDates(View view);
        public void goToGenMealPlan(View view);
    }*/

