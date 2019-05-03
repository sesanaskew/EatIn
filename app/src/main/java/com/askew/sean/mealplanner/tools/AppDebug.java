package com.askew.sean.mealplanner.tools;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.askew.sean.mealplanner.database.DebugToolDataManager;

import java.util.List;

public class AppDebug {

    private static AppDebug appDebug;
    private DebugToolDataManager debugToolDataManager;

    private Database database;
    private Boolean debugMode = false;
    private Boolean cleanDb = false;
    private Boolean updateDb = false;
    private Context context;
    private AppCompatActivity appCompatActivity;

    public static AppDebug getInstance(AppCompatActivity appCompatActivity, Context context){

        if (AppDebug.appDebug == null){
            AppDebug.appDebug = new AppDebug(appCompatActivity,context);
        }

        return AppDebug.appDebug;
    }

    private AppDebug(AppCompatActivity appCompatActivity, Context context){
        this.appCompatActivity = appCompatActivity;
        this.context = context;
        debugToolDataManager = new DebugToolDataManager(appCompatActivity.getApplicationContext());
        database = new Database(appCompatActivity,context);
        onResumeDb();

    }

    public void onResumeDb(){

        List<Boolean> debugList = debugToolDataManager.load(null,null);

        if (debugList.isEmpty()) {
            debugMode = cleanDb = updateDb = false;
        }
        else {
            debugMode = debugList.get(0);
            cleanDb = debugList.get(1);
            updateDb = debugList.get(2);
        }

        database.setDebugMode(debugMode);

        if (debugMode && cleanDb){
            database.cleanDb();
            cleanDb = false;
        }

        if (debugMode && updateDb) {
            database.updateDb();
            updateDb = false;
        }
        updateConfigDb();


    }

    public void updateConfigDb(){
        debugToolDataManager.removeAll();
        debugToolDataManager.insert(debugMode,cleanDb,updateDb);
    }

    public Boolean getCleanDb() {
        return cleanDb;
    }

    public void setCleanDb(Boolean cleanDb) {
        this.cleanDb = cleanDb;
    }

    public Boolean getUpdateDb() {
        return updateDb;
    }

    public void setUpdateDb(Boolean updateDb) {
        this.updateDb = updateDb;
    }

    public Boolean getDebugMode() {
        return debugMode;
    }

    public void setDebugMode(Boolean debugMode) {
        this.debugMode = debugMode;
    }
}
