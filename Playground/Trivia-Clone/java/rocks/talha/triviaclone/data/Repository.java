package rocks.talha.triviaclone.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import rocks.talha.triviaclone.controller.AppController;
import rocks.talha.triviaclone.model.Question;

public class Repository {
    String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";

    ArrayList<Question> questionArrayList = new ArrayList<>();


    public List<Question> getQuestions(final AnswerListAsyncResponse callback){

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            Question question = new Question(response.getJSONArray(i).get(0).toString(),
                                    response.getJSONArray(i).getBoolean(1));

                            questionArrayList.add(question);

                            //Log.d("questions", "getQuestions: " + questionArrayList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                    if(callback != null) callback.processFinished(questionArrayList);

                }, error -> {

        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

        return questionArrayList;
    }
}
