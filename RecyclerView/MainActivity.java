package rocks.talha.tandaram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import rocks.talha.tandaram.adapter.RecyclerViewAdapter;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnIdeaClickListener {

    private static final String TAG = "Clicked";
    private Button goButton;
    private EditText ideaInput;
    public ArrayList<String> ideaArrayList;

    public ArrayList<String> getIdeaArrayList() {
        return ideaArrayList;
    }

    private ArrayAdapter arrayAdapter;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        ideaInput = findViewById(R.id.idea_editText);
        //ideaList = findViewById(R.id.ideaList);
        ideaArrayList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idea = ideaInput.getText().toString();
                ideaArrayList.add(idea);
                ideaInput.setText("");
            }
        });

        //setup adapter
        recyclerViewAdapter = new RecyclerViewAdapter(ideaArrayList, MainActivity.this, this);

        recyclerView.setAdapter(recyclerViewAdapter);

    }

    @Override
    public void onIdeaClick(int position) {
        Log.d(TAG, "onIdeaClick: " + position);
        //startActivity(new Intent(MainActivity.this, ));

        //setContentView(R.layout.truth_details);
    }
}
