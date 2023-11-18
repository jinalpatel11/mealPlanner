package com.example.mealplanner.view.meal;
// MealAdapter.java
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.databinding.ItemMealBinding;
import com.example.mealplanner.model.network.meal.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {
    private List<Product> products = new ArrayList<>();

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMealBinding binding = ItemMealBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MealViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        Product product = products.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class MealViewHolder extends RecyclerView.ViewHolder {
        private final ItemMealBinding binding;

        MealViewHolder(@NonNull ItemMealBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Product product) {
            binding.textViewTitle.setText(product.getTitle());

            // Load the image using Picasso (replace 'imageUrl' with the actual field in your Product model)
             Picasso.get().load(product.getImage()).into(binding.imageViewThumbnail);
             Log.d("Jinal",product.getImage());
        }
    }
}
