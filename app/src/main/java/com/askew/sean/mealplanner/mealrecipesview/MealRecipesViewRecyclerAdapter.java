package com.askew.sean.mealplanner.mealrecipesview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.askew.sean.mealplanner.R;
import com.askew.sean.mealplanner.database.RecipeContract;
import com.askew.sean.mealplanner.database.RecipeDataManager;
import com.askew.sean.mealplanner.model.FoodType;
import com.askew.sean.mealplanner.model.Recipe;

import java.util.List;

public class MealRecipesViewRecyclerAdapter
        extends RecyclerView.Adapter <MealRecipesViewRecyclerAdapter.ViewHolder> {
    private List<Recipe> recipeList;
    private RecipeDataManager mRecipeDataManager;
    private AppCompatActivity activity;
    private Context context;
    public MealRecipesViewRecyclerAdapter(AppCompatActivity activity, Context context, List<Recipe> recipeList) {
        super();
        this.activity = activity;
        this.context= context;
        this.recipeList = recipeList;
        //mRecipeDataManager = new RecipeDataManager(this.context);
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView recipeNameTxtVw;
        public TextView servingAmountTxtVw;
        public TextView ingredientTxtVw;
        public TextView prepTimeAmountTxtVw;
        public TextView cookTimeAmountTxtVw;
        public TextView recipeInstructionsTxtVw;
        public ViewHolder(View itemView) {
            super(itemView);
            recipeNameTxtVw = itemView.findViewById(R.id.recipeNameTxtVw);
            servingAmountTxtVw = itemView.findViewById(R.id.servingAmountTxtVw);
            ingredientTxtVw = itemView.findViewById(R.id.ingredientTxtVw);
            prepTimeAmountTxtVw = itemView.findViewById(R.id.prepTimeAmountTxtVw);
            cookTimeAmountTxtVw = itemView.findViewById(R.id.cookTimeAmountTxtVw);
            recipeInstructionsTxtVw = itemView.findViewById(R.id.recipeInstructionsTxtVw);
        }
    }
    @NonNull
    @Override
    public MealRecipesViewRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recipe_card, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull MealRecipesViewRecyclerAdapter.ViewHolder viewHolder, int i) {
        if (recipeList != null) {
            viewHolder. recipeNameTxtVw.setText(recipeList.get(i).getName());
            viewHolder.servingAmountTxtVw.setText(recipeList.get(i).getServingSize());
            viewHolder.ingredientTxtVw.setText(recipeList.get(i).getIngredients());
            viewHolder.prepTimeAmountTxtVw.setText(recipeList.get(i).getPrepTime());
            viewHolder.cookTimeAmountTxtVw.setText(recipeList.get(i).getCookTime());
            viewHolder.recipeInstructionsTxtVw.setText(recipeList.get(i).getCookingInstructions());
        }
    }
    @Override
    public int getItemCount() {
        if (recipeList != null ){
            return recipeList.size();
        }
        return -1;
    }
    public void setRecipeList(List<Recipe> recipeList){
        this.recipeList = recipeList;
    }

    public void updateData(FoodType foodType){
        String column= RecipeContract.RecipeEntry.COLUMN_NAME_FOOD_TYPE;
        String[] values = new String[1];
        values[0]= foodType.toString();
        recipeList = mRecipeDataManager.load(column, values);

    }

}
