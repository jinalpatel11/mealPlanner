package com.example.mealplanner.view.setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.example.mealplanner.model.Developer;

import java.util.List;

public class DeveloperAdapter extends RecyclerView.Adapter<DeveloperAdapter.DeveloperViewHolder> {

    private List<Developer> developerList;
    private Context context;

    public DeveloperAdapter(List<Developer> developerList) {
        this.developerList = developerList;
    }

    @NonNull
    @Override
    public DeveloperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_developer, parent, false);
        return new DeveloperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeveloperViewHolder holder, int position) {
        Developer developer = developerList.get(position);

        holder.imageViewDeveloper.setImageResource(developer.getImageResourceId());
        holder.textDeveloperName.setText(developer.getName());
        holder.textDeveloperDetails.setText(developer.getDetails());
    }

    @Override
    public int getItemCount() {
        return developerList.size();
    }

    static class DeveloperViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewDeveloper;
        TextView textDeveloperName;
        TextView textDeveloperDetails;

        DeveloperViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewDeveloper = itemView.findViewById(R.id.imageDeveloper);
            textDeveloperName = itemView.findViewById(R.id.textDeveloperName);
            textDeveloperDetails = itemView.findViewById(R.id.textDeveloperDetails);
        }
    }
}

