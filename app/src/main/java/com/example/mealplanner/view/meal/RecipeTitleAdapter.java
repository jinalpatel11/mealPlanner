package com.example.mealplanner.view.meal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;

import java.util.List;

public class RecipeTitleAdapter extends RecyclerView.Adapter<RecipeTitleAdapter.RecipeViewHolder> {

    private List<String> recipeItems;

    public RecipeTitleAdapter(List<String> recipeItems) {
        this.recipeItems = recipeItems;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe_title, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        String recipeItem = recipeItems.get(position);
        holder.recipeItemTextView.setText(recipeItem);

        // Set click listener or other properties as needed for the item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle item click
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeItems.size();
    }

    static class RecipeViewHolder extends RecyclerView.ViewHolder {

        TextView recipeItemTextView;

        RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeItemTextView = itemView.findViewById(R.id.recipeItemTextView);
        }
    }
}
