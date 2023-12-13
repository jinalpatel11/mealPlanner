package com.example.mealplanner.view.setting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealplanner.R;
import com.example.mealplanner.model.InfoPageModel;

import java.util.List;

public class InfoPagesAdapter extends RecyclerView.Adapter<InfoPagesAdapter.InfoPageViewHolder> {

    private List<InfoPageModel> infoPagesList;

    public InfoPagesAdapter(List<InfoPageModel> infoPagesList) {
        this.infoPagesList = infoPagesList;
    }

    @NonNull
    @Override
    public InfoPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info_page, parent, false);
        return new InfoPageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoPageViewHolder holder, int position) {
        InfoPageModel infoPage = infoPagesList.get(position);

        holder.textTitle.setText(infoPage.getTitle());
        holder.textContent.setText(infoPage.getContent());
    }

    @Override
    public int getItemCount() {
        return infoPagesList.size();
    }

    static class InfoPageViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        TextView textContent;

        InfoPageViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textContent = itemView.findViewById(R.id.textContent);
        }
    }
}
