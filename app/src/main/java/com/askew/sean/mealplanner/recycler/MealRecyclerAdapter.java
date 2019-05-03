package com.askew.sean.mealplanner.recycler;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.askew.sean.mealplanner.R;
import com.askew.sean.mealplanner.model.CalendarViewDate;
import com.askew.sean.mealplanner.model.Meal;

import java.util.List;

public class MealRecyclerAdapter extends RecyclerView.Adapter<MealRecyclerAdapter.ViewHolder> {
    private List<Meal> mealList;
    private String mealName;
    private Activity activity;
    private Context context;
    private ClickListener listener;

    protected MealRecyclerAdapter(){

        super();
    }

    protected MealRecyclerAdapter(Activity activity, Context context) {
        super();
        this.activity = activity;
        this.context = context;

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mealNameTxtVw;
        public TextView appetizerNameTxtVw;
        public TextView appetizerHeaderTxtVw;
        public TextView entreeNameTxtVw;
        public TextView entreeHeaderTxtVw;
        public TextView dessertNameTxtVw;
        public TextView dessertHeaderTxtVw;
        public TextView sideNameTxtVw;
        public TextView sideHeaderTxtVw;

        public ViewHolder(View itemView) {
            super(itemView);
            mealNameTxtVw = itemView.findViewById(R.id.mealNameTxtVw);
            appetizerNameTxtVw = itemView.findViewById(R.id.appetizerNameTxtVw);
            appetizerHeaderTxtVw = itemView.findViewById(R.id.appetizerHdr);
            entreeNameTxtVw = itemView.findViewById(R.id.entreeNameTxtVw);
            entreeHeaderTxtVw = itemView.findViewById(R.id.entreeHdr);
            sideNameTxtVw = itemView.findViewById(R.id.sideNameTxtVw_1);
            sideHeaderTxtVw = itemView.findViewById(R.id.sideHdr);
            dessertNameTxtVw = itemView.findViewById(R.id.dessertNameTxtVw);
            dessertHeaderTxtVw = itemView.findViewById(R.id.dessertHdr);
        }
    }

    @NonNull
    @Override
    public MealRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.meal_card, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarViewDate.setMealName(mealName);
                if (listener != null){
                    listener.itemClick(v);
                }

            }
        });
        return viewHolder;
    }
    public void setClickListener(ClickListener listener){
        if ( listener != null){
            this.listener = listener;
        }
    }
    @Override
    public void onBindViewHolder(@NonNull MealRecyclerAdapter.ViewHolder viewHolder, int i) {
        if (mealList != null) {
            viewHolder.mealNameTxtVw.setText(mealList.get(i).getMealName());
            mealName = mealList.get(i).getMealName();
            CalendarViewDate.setMealName(mealName);

            if (mealList.get(i).getAppRecipe().isEmpty()) {
                viewHolder.appetizerHeaderTxtVw.setVisibility(View.INVISIBLE);
            } else {
                viewHolder.appetizerNameTxtVw.setText(mealList.get(i).getAppRecipe());
            }
            if (mealList.get(i).getEntreeRecipe().isEmpty()) {
                viewHolder.entreeHeaderTxtVw.setVisibility(View.INVISIBLE);
            } else {
                viewHolder.entreeNameTxtVw.setText(mealList.get(i).getEntreeRecipe());
            }

            if (mealList.get(i).getSidesRecipes().get(0).isEmpty()) {
                viewHolder.sideHeaderTxtVw.setVisibility(View.INVISIBLE);
            } else {
                viewHolder.sideNameTxtVw.setText(mealList.get(i).getSidesRecipes().get(0));
            }

            if ( mealList.get(i).getDessertRecipe().isEmpty()) {
                viewHolder.dessertHeaderTxtVw.setVisibility(View.INVISIBLE);
            }
            else {
                viewHolder.dessertNameTxtVw.setText(mealList.get(i).getName());
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mealList != null ){
            return mealList.size();
        }
        return -1;
    }

    public void setMealList(List<Meal> mealList){
        this.mealList = mealList;
    }

    public interface ClickListener{
        void itemClick(View v);
    }

}

