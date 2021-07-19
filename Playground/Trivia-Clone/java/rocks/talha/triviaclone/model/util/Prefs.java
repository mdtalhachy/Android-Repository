package rocks.talha.triviaclone.model.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    private SharedPreferences preferences;

    public Prefs(Activity context) {
        this.preferences = context.getPreferences(Context.MODE_PRIVATE);
    }

    public void savedHighestScore(int score){
        int currentScore = score;
        int prevScore = preferences.getInt("high_score_key", 0);
        if(currentScore > prevScore){
            preferences.edit().putInt("high_score_key", currentScore).apply();
        }
    }

    public int getHighestScore(){
        return preferences.getInt("high_score_key", 0);
    }

}
