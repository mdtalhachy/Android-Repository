package rocks.talha.tandaram.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import rocks.talha.tandaram.MainActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

import rocks.talha.tandaram.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private OnIdeaClickListener ideaClickListener;

    private final ArrayList<String> ideaArrayList;
    MainActivity mainActivity = new MainActivity();

    public RecyclerViewAdapter(ArrayList<String> ideaArrayList, MainActivity mainActivity, OnIdeaClickListener onIdeaClickListener) {
        this.mainActivity = mainActivity;
        this.ideaArrayList = ideaArrayList;
        this.ideaClickListener = onIdeaClickListener;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.idea_row, parent, false);

        return new ViewHolder(view, ideaClickListener);

    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull RecyclerViewAdapter.ViewHolder holder, int position) {
        String s = (mainActivity.getIdeaArrayList()).get(position);
        holder.idea.setText(s);
    }

    @Override
    public int getItemCount() {

        return Objects.requireNonNull(mainActivity.getIdeaArrayList().size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        OnIdeaClickListener onIdeaClickLis;
        public TextView idea;
        public TextView description;

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView, OnIdeaClickListener onIdeaClickLis) {
            super(itemView);

            idea = itemView.findViewById(R.id.row_name_text);
            description = itemView.findViewById(R.id.row_description_text);
            this.onIdeaClickLis = onIdeaClickLis;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onIdeaClickLis.onIdeaClick(getAdapterPosition());
        }
    }

    public interface OnIdeaClickListener {
        void onIdeaClick(int position);
    }
}
