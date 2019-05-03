package com.askew.sean.mealplanner.dailymealplan;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.askew.sean.mealplanner.R;
import com.askew.sean.mealplanner.model.DailyMealPlan;
import com.askew.sean.mealplanner.recycler.MealRecyclerAdapter;
import com.askew.sean.mealplanner.utils.DisplayUtil;

import java.util.List;

public class DailyMealPlanListRecyclerAdapter
        extends RecyclerView.Adapter<DailyMealPlanListRecyclerAdapter.ColumnViewHolder>
    {
    Activity mActivity;
    Context mContext;
    List<DailyMealPlan> dailyMealPlanList;
    DailyMealPlanRecyclerAdapter itemListDataAdapter;
    private MealRecyclerAdapter.ClickListener listener;

    DailyMealPlanListRecyclerAdapter(Activity activity, Context context, List<DailyMealPlan> dailyMealPlanList) {
        super();
        this.mActivity = activity;
        this.mContext = context;
        this.dailyMealPlanList = dailyMealPlanList;

        //updateData(dailyMealPlanList);
    }


     public void setClickListener(MealRecyclerAdapter.ClickListener listener) {
        this.listener = listener;
    }

    public class ColumnViewHolder extends RecyclerView.ViewHolder {

        protected RecyclerView recycler_view_list;
        protected TextView calendarViewDateTxtVw;

        public ColumnViewHolder(View view) {
            super(view);
            this.calendarViewDateTxtVw = view.findViewById(R.id.dateDailyMealPlanListTxtVw_ID);
            LinearLayout llRecyclerView = (LinearLayout) itemView.findViewById(R.id.llDailyMealPlanItemRecyclerView_ID);
            llRecyclerView.getLayoutParams().width = (int) (DisplayUtil.getScreenWidth(itemView.getContext()));
            llRecyclerView.getLayoutParams().height = (int) (DisplayUtil.getScreenHeight(itemView.getContext()));
            this.recycler_view_list = (RecyclerView) view.findViewById(R.id.dailyMealPlanItemRecyclerView_ID);

        }
    }


    @Override
    public DailyMealPlanListRecyclerAdapter.ColumnViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.daily_meal_plan_item, null);
        ColumnViewHolder mh = new ColumnViewHolder(v);
        return mh;
    }


    @Override
    public void onBindViewHolder(ColumnViewHolder columnViewHolder, int i) {


        DailyMealPlan dailyMealPlan = dailyMealPlanList.get(i);

        columnViewHolder.calendarViewDateTxtVw.setText(dailyMealPlan.getDate());

        itemListDataAdapter = new DailyMealPlanRecyclerAdapter(mActivity, mContext, dailyMealPlan);
        itemListDataAdapter.setClickListener(listener);
        columnViewHolder.itemView.setMinimumWidth(Math.round(columnViewHolder.recycler_view_list.getWidth()));
        columnViewHolder.recycler_view_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        columnViewHolder.recycler_view_list.setAdapter(itemListDataAdapter);

    }

    @Override
    public int getItemCount() {

        return (null != dailyMealPlanList ? dailyMealPlanList.size() : 0);
    }

    public void updateData(List<DailyMealPlan> dailyMealPlanList) {
        this.dailyMealPlanList = dailyMealPlanList;
    }
}
