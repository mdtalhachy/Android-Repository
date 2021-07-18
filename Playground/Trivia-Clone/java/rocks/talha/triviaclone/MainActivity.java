package rocks.talha.triviaclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import rocks.talha.triviaclone.data.Repository;
import rocks.talha.triviaclone.databinding.ActivityMainBinding;
import rocks.talha.triviaclone.model.Question;

public class MainActivity extends AppCompatActivity {

    String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";


    private ActivityMainBinding binding;
    private int currentQuestionIndex = 0;
    List<Question> questionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        questionsList = new Repository().getQuestions(questionArrayList -> {
            binding.questionTextView.setText(questionArrayList.get(currentQuestionIndex)
            .getAnswer());

            updateCounter();

        });

        binding.nextButton.setOnClickListener(view -> {
            currentQuestionIndex = (currentQuestionIndex + 1) % questionsList.size();
            updateQuestion(questionsList);
        });

        binding.trueButton.setOnClickListener(view -> {
            checkAnswer(true);
            updateQuestion(questionsList);
        });

        binding.falseButton.setOnClickListener(view -> {
            checkAnswer(false);
            updateQuestion(questionsList);
            Log.d("lol", "onCreate: " + currentQuestionIndex);
        });

    }

    private void checkAnswer(boolean userChose) {
        boolean answer = questionsList.get(currentQuestionIndex).isAnswerTrue();
        int snackMsgID = 0;

        if(answer == userChose){
            snackMsgID = R.string.correct_answer;
            fadeAnimation();
        }else{
            snackMsgID = R.string.incorrect_answer;
            shakeAnimation();
        }

        Snackbar.make(binding.questionCardView, snackMsgID, Snackbar.LENGTH_SHORT).show();
    }

    private void updateCounter() {
        binding.progressTextView.setText("Question: " + (currentQuestionIndex + 1) + "/" + questionsList.size());
    }


    public void updateQuestion(List<Question> qAList){
        binding.questionTextView.setText(qAList.get(currentQuestionIndex).getAnswer());
        updateCounter();
    }

    private void fadeAnimation(){
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);

        binding.questionCardView.setAnimation(alphaAnimation);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.questionTextView.setTextColor(Color.GREEN);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.questionTextView.setTextColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void shakeAnimation(){
        Context context;
        Animation shake = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake_animation);
        binding.questionCardView.setAnimation(shake);

        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.questionTextView.setTextColor(Color.RED);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.questionTextView.setTextColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
