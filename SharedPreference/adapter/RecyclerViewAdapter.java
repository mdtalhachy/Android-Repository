package rocks.talha.recyclerviewtest1.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import rocks.talha.recyclerviewtest1.MainActivity;
import rocks.talha.recyclerviewtest1.R;
import rocks.talha.recyclerviewtest1.SavedActivity;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    //creating an Object of MainActivity to get access to its elements like ArrayList size
    //used for getItemCount() method
    MainActivity mainActivity = new MainActivity();
    SavedActivity savedActivity = new SavedActivity();


    private static final String MESSAGE_ID_R = "ideaID";

    private final ArrayList<String> idea_ArrayList;
    private final ArrayList<String> description_ArrayList;



    public RecyclerViewAdapter(ArrayList<String> ideaTitleArrayList, ArrayList<String> ideaDescriptionArrayList, SavedActivity savedActivity) {

        this.idea_ArrayList = ideaTitleArrayList;
        this.description_ArrayList = ideaDescriptionArrayList;
        this.savedActivity = savedActivity;

    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {

        Context context;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.idea_list_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull RecyclerViewAdapter.ViewHolder holder, int position) {


        String str_ideaTitle =  idea_ArrayList.get(position);
        String str_ideaDescription = description_ArrayList.get(position);


        //setting textViews to ideaTitle and ideaDescription
        holder.ideaTitle.setText(str_ideaTitle); //I can also use str_ideaTitle instead of value
        holder.ideaDescription.setText(str_ideaDescription); //ideaDescription hasn't been changed for sharedPreference

    }

    @Override
    public int getItemCount() {
        //Log.d("HeyIdeas", "ViewHolder: " + idea_ArrayList.get(0));
        return (idea_ArrayList == null) ? 0 : idea_ArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

            public TextView ideaTitle;
            public TextView ideaDescription;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            //Initializing ideaTitle and ideaDescription to set to textViews
            ideaTitle = itemView.findViewById(R.id.idea_title_text);
            ideaDescription = itemView.findViewById(R.id.idea_description_text);
        }
    }
}
