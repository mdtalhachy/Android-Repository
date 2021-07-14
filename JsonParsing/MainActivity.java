package rocks.talha.parsedata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    RequestQueue queue;
    String url = "https://www.google.com";
    String apiUrl = "https://jsonplaceholder.typicode.com/todos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, apiUrl, null,
                response -> {

                    for (int i = 0; i < response.length(); i++) {

                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Log.d("JSON", "onCreate: " + jsonObject.getString("title"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, error -> {
                    Log.d("JSON", "onCreate: Failed");
                });


        queue.add(jsonArrayRequest);

        getString(queue);
    }

    @org.jetbrains.annotations.NotNull
    private StringRequest getString(RequestQueue queue) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {

            //displaying contents of our URL
            Log.d("Main", "onCreate: " + response.substring(0, 500));

        }, error -> {
            Log.d("Main", "Failed to fetch data");
        });

        queue.add(stringRequest);

        return stringRequest;
    }
}
