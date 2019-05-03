package com.askew.sean.mealplanner.dailymealplan;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.askew.sean.mealplanner.R;
import com.askew.sean.mealplanner.model.DailyMealPlan;

public class DailyMealPlanRecyclerView extends RecyclerView  {
    private RecyclerView dayRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private Activity mActivity;
    private Context mContext;
    public DailyMealPlanRecyclerView(Activity activity, Context context, DailyMealPlan dailyMealPlan){
        super(context);
        mActivity = activity;
        mContext = context;
        createRecyclerAdapter(context,dailyMealPlan);
    }
    public void createRecyclerAdapter(Context context, DailyMealPlan dailyMealPlan) { ;
        dayRecyclerView = mActivity.findViewById(R.id.dailyMealPlanListRecyclerVw_ID);
        layoutManager = new LinearLayoutManager(mActivity);
        dayRecyclerView.setLayoutManager(layoutManager);
        adapter = new DailyMealPlanRecyclerAdapter(mActivity, mContext,dailyMealPlan);
        dayRecyclerView.setAdapter(adapter);
    }
    public void updateDate(DailyMealPlan dailyMealPlan){
        DailyMealPlanRecyclerAdapter dailyMealPlanRecyclerAdapter = (DailyMealPlanRecyclerAdapter) dayRecyclerView.getAdapter();
        dailyMealPlanRecyclerAdapter.updateData(dailyMealPlan);
        dailyMealPlanRecyclerAdapter.notifyDataSetChanged();
    }
}
