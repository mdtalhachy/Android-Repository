package rocks.talha.todoister.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

import java.util.List;

import rocks.talha.todoister.R;
import rocks.talha.todoister.model.Task;
import rocks.talha.todoister.util.Utils;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final List<Task> allTasks;

    public RecyclerViewAdapter(List<Task> allTasks) {
        this.allTasks = allTasks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        Task task = allTasks.get(position);

        String formatted = Utils.formatDate(task.dueDate);

        holder.task.setText(task.getTask());
        holder.todayChip.setText(formatted);

    }

    @Override
    public int getItemCount() {
        return allTasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public AppCompatRadioButton radioButton;
        public AppCompatTextView task;
        public Chip todayChip;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            radioButton = itemView.findViewById(R.id.todo_radio_button);
            task = itemView.findViewById(R.id.todo_row_todo);
            todayChip = itemView.findViewById(R.id.todo_row_chip);
        }
    }
}
