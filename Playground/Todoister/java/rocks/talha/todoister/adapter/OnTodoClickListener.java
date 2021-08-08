package rocks.talha.todoister.adapter;

import rocks.talha.todoister.model.Task;

public interface OnTodoClickListener {
    void onTodoClick(Task task);
    void onTodoRadioButtonClick(Task task);
}
