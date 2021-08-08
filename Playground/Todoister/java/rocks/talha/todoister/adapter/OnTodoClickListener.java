package rocks.talha.todoister.adapter;

import rocks.talha.todoister.model.Task;

public interface OnTodoClickListener {
    void onTodoClick(int adapterPosition, Task task);
    void onTodoRadioButtonClick(Task task);
}
