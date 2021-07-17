package rocks.talha.recyclerviewtest1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rocks.talha.recyclerviewtest1.adapter.RecyclerViewAdapter;

public class SavedActivity extends AppCompatActivity {

    private static final String MESSAGE_ID = "ideaID";


    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    MainActivity mainActivity = new MainActivity();

    private ArrayList<String> saved_idea_list;
    private ArrayList<String> saved_description_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

        //SharedPreference Setup
        SharedPreferences sharedPreferences = getSharedPreferences("mykey", MODE_PRIVATE);
        String value = sharedPreferences.getString("value", "");
        String description = sharedPreferences.getString("description", "");


        //converting idea string to arraylist
        String replace = value.replace("[","");
        String replace1 = replace.replace("]","");
        List<String> myList = new ArrayList<String>(Arrays.asList(replace1.split(",")));

        saved_idea_list = (ArrayList<String>) myList;

        //saved_idea_list = getIntent().getStringArrayListExtra("ideas");
        //saved_description_list = getIntent().getStringArrayListExtra("descriptions");


        //converting description string to arraylist
        String desReplace = description.replace("[", "");
        String desReplace1 = desReplace.replace("]", "");
        List<String> desList = new ArrayList<String>(Arrays.asList(desReplace1.split(",")));

        saved_description_list = (ArrayList<String>) desList;



        //Initializing RecyclerView
        recyclerView = findViewById(R.id.ideaList_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //setting up RecyclerViewAdapter
        recyclerViewAdapter = new RecyclerViewAdapter(saved_idea_list, saved_description_list, SavedActivity.this);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
