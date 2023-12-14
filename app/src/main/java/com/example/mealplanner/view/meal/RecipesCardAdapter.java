package com.example.mealplanner.view.meal;
// RecipesCardAdapter.java

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.databinding.ItemRecipeCardBinding;
import com.example.mealplanner.model.meal.Recipe;
import com.squareup.picasso.Picasso;
import java.util.List;

public class RecipesCardAdapter extends RecyclerView.Adapter<RecipesCardAdapter.RecipeViewHolder> {

    private static Context context;
    private List<Recipe> recipes;

    public RecipesCardAdapter(Context context, List<Recipe> recipes) {

        this.context = context;
        this.recipes = recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecipeCardBinding binding = ItemRecipeCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecipeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.bind(recipe);
        Log.d("RecipesCardAdapter",recipe.getTitle());

      }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {

        private final ItemRecipeCardBinding binding;
        // Declare your views here

         RecipeViewHolder(@NonNull ItemRecipeCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
         }



        // Inside the RecipeViewHolder class in RecipesCardAdapter
        void bind(final Recipe recipe) {
             binding.recipeTitleTextView.setText(recipe.getTitle());
            // Load the image using Picasso (replace 'imageUrl' with the actual field in your Product model)
            Picasso.get().load(recipe.getImage()).into(binding.recipeImageView);

            // Set click listener for the recipe card
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle the click event, e.g., launch RecipeDetails activity/fragment
                    // You may want to pass the recipe details to the RecipeDetails view
                    Intent intent = new Intent(context, RecipeDetailsActivity.class);
                    intent.putExtra("recipe_id", recipe.getId()); // Pass any necessary data
                    context.startActivity(intent);
                }
            });
        }

    }
}

