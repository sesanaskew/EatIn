package com.askew.sean.mealplanner.config;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.askew.sean.mealplanner.R;

import java.util.List;

public class DebugDbRecyclerView {
    private RecyclerView mdbDebugRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private AppCompatActivity mActivity;
    private Context mContext;
    public DebugDbRecyclerView(AppCompatActivity activity, Context context){
        super();
        mActivity = activity;
        mContext = context;
        mActivity.setContentView(R.layout.activity_config_settings);
        createRecyclerAdapter(context);
    }
    public void createRecyclerAdapter(Context context) {
        mActivity.setContentView(R.layout.activity_config_settings);
        mdbDebugRecyclerView = mActivity.findViewById(R.id.debug_recycler_id);
        layoutManager = new LinearLayoutManager(mActivity);
        mdbDebugRecyclerView.setLayoutManager(layoutManager);
        adapter = new DebugDbRecyclerAdapter() ;
        mdbDebugRecyclerView.setAdapter(adapter);
    }
    public void updateDbEntry(List<String[]> dbEntryList){
        createRecyclerAdapter(mContext);
        DebugDbRecyclerAdapter debugDbRecyclerAdapter = (DebugDbRecyclerAdapter) mdbDebugRecyclerView.getAdapter();
        debugDbRecyclerAdapter.setDataList(dbEntryList);
        debugDbRecyclerAdapter.notifyDataSetChanged();
    }


}
