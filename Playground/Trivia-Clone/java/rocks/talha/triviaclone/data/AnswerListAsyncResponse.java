package rocks.talha.triviaclone.data;

import java.util.ArrayList;

import rocks.talha.triviaclone.model.Question;

public interface AnswerListAsyncResponse {
    void processFinished(ArrayList<Question> questionArrayList);
}
