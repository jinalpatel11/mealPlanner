package com.example.mealplanner.view.meal;
// RecipesAdapter.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.example.mealplanner.model.RecipeItem;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder> {

    private List<RecipeItem> recipeItems;

    public RecipesAdapter(List<RecipeItem> recipeItems) {
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
        RecipeItem recipeItem = recipeItems.get(position);
        holder.recipeItemImageView.setImageResource(recipeItem.getImageResource());
        holder.recipeItemTextView.setText(recipeItem.getText());

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
        ImageView recipeItemImageView;
        TextView recipeItemTextView;

        RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeItemImageView = itemView.findViewById(R.id.recipeItemImageView);
            recipeItemTextView = itemView.findViewById(R.id.recipeItemTextView);
        }
    }
}
