package com.askew.sean.mealplanner.config;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.askew.sean.mealplanner.R;

import java.util.ArrayList;
import java.util.List;

public class DebugDbRecyclerAdapter extends RecyclerView.Adapter<DebugDbRecyclerAdapter.ViewHolder> {
    private List<String[]> dataList = new ArrayList<String[]>();


    protected DebugDbRecyclerAdapter(){
        super();
    }
    /*protected MealRecyclerAdapter(String adapterType) {
        super();

    }*/
    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dbEntryId;
        public TextView dbEntryData;

        public ViewHolder(View itemView) {
            super(itemView);
            dbEntryId = itemView.findViewById(R.id.db_entry_id);
            dbEntryData = itemView.findViewById(R.id.db_entry_data_id);
        }
    }
    @NonNull
    @Override
    public DebugDbRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.db_entry_card, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull DebugDbRecyclerAdapter.ViewHolder viewHolder, int i) {
        if (dataList != null) {
            viewHolder.dbEntryId.setText(dataList.get(i)[0]);
            viewHolder.dbEntryData.setText(dataList.get(i)[1]);
        }
    }
    @Override
    public int getItemCount() {
        if (dataList != null ){
            return dataList.size();
        }
        return -1;
    }

    public void setDataList(List<String[]> dataList){
        this.dataList = dataList;
    }
}
