package com.askew.sean.mealplanner.dailymealplan;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;

public class DailyMealPlanMenu {
    private AppCompatActivity mAppCompatActivity;
    private Context mContext;
    private ImageButton mPrevDate;
    private ImageButton mNextDate;
    private TextView mDateTextVw;
    private Calendar mCalendarViewDate;
    private Calendar mTodayDate;
    private int mContentView;

    private static String DATE_FORMAT = "MM/dd/yyyy";


    private DailyMealPlanMenu(){
        super();
    }

    public DailyMealPlanMenu(AppCompatActivity activity, Context context,int contentView) {
        this.mContentView = contentView;
        this.mAppCompatActivity = activity;
        this.mContext = context;

        mAppCompatActivity.setContentView(mContentView);
        initDateMenu();
    }

    private void initDateMenu(){
       /* mPrevDate = mAppCompatActivity.findViewById(R.id.prevBtn );
        mPrevDate.setSelected(false);
        mPrevDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevDateButton(v);
            }
        });
        mNextDate = mAppCompatActivity.findViewById(R.id.nextBtn);
        mNextDate.setSelected(false);
        mNextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextDateButton(v);
            }
        });
        mDateTextVw = mAppCompatActivity.findViewById(R.id.curDateTextVw_ID);
        mDateTextVw.setVisibility(View.VISIBLE);
        mCalendarViewDate =  NavMenu.getsCalendarViewDate();
        setCurrentDateTxtVw(mCalendarViewDate);
    */
    }

   /* public void setTodayDate() {

        mCalendarViewDate = Calendar.getInstance();
        setCurrentDateTxtVw(mCalendarViewDate);
    }

    public void nextDateButton(View v) {
        mPrevDate.setSelected(false);
        mNextDate.setSelected(true);
        nextDate(v);
    }

    public void prevDateButton(View v) {
        mPrevDate.setSelected(true);
        mNextDate.setSelected(false);
        previousDate(v);
    }

    private void nextDate(View v) {
        MainActivity mainActivity = (MainActivity) mAppCompatActivity;
        mCalendarViewDate = mainActivity.getCurrentDate();
        mCalendarViewDate.set(mCalendarViewDate.DAY_OF_YEAR, mCalendarViewDate.get(mCalendarViewDate.DAY_OF_YEAR) + 1);
        mainActivity.setCurrentDate(mCalendarViewDate);
        setCurrentDateTxtVw(mCalendarViewDate);
        mainActivity.nextDate(v);

    }

    private void previousDate(View v) {
        MainActivity mainActivity = (MainActivity) mAppCompatActivity;
        mCalendarViewDate = mainActivity.getCurrentDate();
        mCalendarViewDate.set(mCalendarViewDate.DAY_OF_YEAR, mCalendarViewDate.get(mCalendarViewDate.DAY_OF_YEAR) - 1);
        mainActivity.setCurrentDate(mCalendarViewDate);
        setCurrentDateTxtVw(mCalendarViewDate);
        mainActivity.previousDate(v);

    }

    public void setCurrentDateTxtVw(@NonNull Calendar date) {

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        mDateTextVw.setText(sdf.format(date.getTime()));

    }
*/
    public interface DailyMealPlanMenuInt{
        public void nextDate(View view);
        public void previousDate(View view);
        public void setCurrentDate(Calendar date);
        public Calendar getCurrentDate();
    }

}
