package rocks.talha.tandaram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button goButton;
    private EditText ideaInput;
    private ListView ideaList;
    private ArrayList<String> ideaArrayList;
    private ArrayAdapter arrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        ideaInput = findViewById(R.id.idea_editText);
        ideaList = findViewById(R.id.ideaList);
        ideaArrayList = new ArrayList<>();


        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idea = ideaInput.getText().toString();
                ideaArrayList.add(idea);
                ideaInput.setText("");
            }
        });

        //create array adapter
        arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                ideaArrayList
        );

        //add to our listview
        ideaList.setAdapter(arrayAdapter);

        //attaching event listener to items
        ideaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("Position", "onItemClick: " + i);
            }
        });

    }
}
