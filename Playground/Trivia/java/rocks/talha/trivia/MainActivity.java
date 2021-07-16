package rocks.talha.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String questionSet = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";

    private CardView qCardView;
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;
    private int i = 0;


    private ArrayList<String>  qArrayList = new ArrayList<>();
    private JSONArray qJsonArray = new JSONArray();

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);

        queue = Volley.newRequestQueue(this);



        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, questionSet, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                int len = response.length();

                try {
                    String question = response.getJSONArray(i).get(0).toString();
                    String answer = response.getJSONArray(i).get(1).toString();

                    questionTextView.setText(question);

                    trueButton.setOnClickListener(view -> {
                        if(answer.equals("true")){
                            Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
                        }
                    });

                    falseButton.setOnClickListener(view -> {
                        if(answer.equals("false")){
                            Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
                        }
                    });

                    /* if Next button is clicked*/
                    nextButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try {
                                i++;
                                updateQuestion(i, response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });







                } catch (JSONException e) {
                    e.printStackTrace();
                }





                /*try {

                    *//*qJsonArray = response;
                    Log.d("QAtest", "onResponse: " + qJsonArray.getJSONArray(0));*//*

                    Log.d("Qset", "onCreate: " + response.getJSONArray(0).get(0));
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Qset", "onCreate: Failed to fetch data!");
            }
        });


        queue.add(jsonArrayRequest);


    }

    public void updateQuestion(int z, JSONArray res) throws JSONException {

        String question = res.getJSONArray(z).get(0).toString();
        String answer = res.getJSONArray(z).get(1).toString();
        questionTextView.setText(question);

        trueButton.setOnClickListener(view -> {
            if(answer.equals("true")){
                Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(MainActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
            }
        });

        falseButton.setOnClickListener(view -> {
            if(answer.equals("false")){
                Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
            }
        });
    }
}