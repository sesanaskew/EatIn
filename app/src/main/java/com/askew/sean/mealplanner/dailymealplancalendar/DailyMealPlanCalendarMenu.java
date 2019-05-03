package com.askew.sean.mealplanner.dailymealplancalendar;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.askew.sean.mealplanner.R;
import com.askew.sean.mealplanner.generatedailymealplanactivity.GenDailyMealPlanActivity;

import java.util.Calendar;

public class DailyMealPlanCalendarMenu {
    private AppCompatActivity mAppCompatActivity;
    private Context mContext;
    private int mContentView;
    private ImageButton mPickDatesBtn;
    private ImageButton mGenMealPlanBtn;
    static private Calendar calendar;

    private DailyMealPlanCalendarMenu(){
        super();
    }

    public DailyMealPlanCalendarMenu(AppCompatActivity activity, Context context, int contentView) {

        this.mAppCompatActivity = activity;
        this.mContext = context;
        this.mContentView = contentView;

        mAppCompatActivity.setContentView(mContentView);
        initMealPlanCalendarMenu();
    }

    public void initMealPlanCalendarMenu(){
        mAppCompatActivity.setContentView(mContentView);

        mPickDatesBtn = mAppCompatActivity.findViewById(R.id.pickDatesBtn);
        mPickDatesBtn.setSelected(false);
        mGenMealPlanBtn = mAppCompatActivity.findViewById(R.id.genMealPlanBtn);
        mGenMealPlanBtn.setVisibility(View.INVISIBLE);
        mGenMealPlanBtn.setSelected(false);
        mGenMealPlanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do Nothing
            }
        });
        TextView genMealPlanTxtVw = mAppCompatActivity.findViewById(R.id.genMealPlanTxtView);
        genMealPlanTxtVw.setVisibility(View.INVISIBLE);
    }

    public void goToPickDates(View view) {
        mPickDatesBtn.setSelected(true);
        Intent intent = new Intent(mContext, GenDailyMealPlanActivity.class); //(mContext, mActivity);
        mAppCompatActivity.startActivity(intent);
    }

    public void registerPickDates(View.OnClickListener listener){
        mPickDatesBtn.setOnClickListener(listener);
    }

    public interface DailyMealPlanCalendarInt{
        void goToPickDates(View view);
        void registerPickDates();
    }
}
