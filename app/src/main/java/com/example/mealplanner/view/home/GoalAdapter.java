package com.example.mealplanner.view.home;

        import android.util.SparseBooleanArray;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;
        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.mealplanner.R;
        import com.example.mealplanner.model.GoalItem;
        import com.example.mealplanner.model.RecipeItem;

        import java.util.ArrayList;
        import java.util.List;

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.GoalViewHolder> {

    private List<GoalItem> goalItems;

    // Variable to store the selected goals
    private List<GoalItem> selectedGoals = new ArrayList<>();


    private SparseBooleanArray selectedItems;
    public GoalAdapter(List<GoalItem> goalItems) {

        this.goalItems = goalItems;
        this.selectedItems = new SparseBooleanArray();
        this.selectedGoals = new ArrayList<>();
    }

    @NonNull
    @Override
    public GoalAdapter.GoalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goal, parent, false);
        return new GoalAdapter.GoalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalAdapter.GoalViewHolder holder, int position) {
        GoalItem goalItem = goalItems.get(position);
        holder.goalItemImageView.setImageResource(goalItem.getImageResource());
        holder.goalItemTextView.setText(goalItem.getText());

        // Set item click listener
        holder.itemView.setOnClickListener(v -> {
            // Toggle the selection state
            toggleSelection(position);
            // Update selected goals list based on selection
            if (selectedItems.get(position, false)) {
                selectedGoals.add(goalItems.get(position));
            } else {
                selectedGoals.remove(goalItems.get(position));
            }
        });

        // Set the activated state based on the selectedItems array
        holder.itemView.setActivated(selectedItems.get(position, false));



    }

    @Override
    public int getItemCount() {
        return goalItems.size();
    }

    static class GoalViewHolder extends RecyclerView.ViewHolder {
        ImageView goalItemImageView;
        TextView goalItemTextView;

        GoalViewHolder(@NonNull View itemView) {
            super(itemView);
            goalItemImageView = itemView.findViewById(R.id.goalImageView);
            goalItemTextView = itemView.findViewById(R.id.goalTextView);
        }
    }


    // Method to toggle the selection
    private void toggleSelection(int position) {
        if (selectedItems.get(position, false)) {
            selectedItems.delete(position);
        } else {
            selectedItems.put(position, true);
        }
        notifyItemChanged(position);
    }


    // Method to get the list of selected goals
    public List<GoalItem> getSelectedGoals() {
        return selectedGoals;
    }


}
