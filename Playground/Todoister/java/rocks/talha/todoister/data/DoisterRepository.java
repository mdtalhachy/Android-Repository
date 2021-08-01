package rocks.talha.todoister.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import rocks.talha.todoister.model.Task;
import rocks.talha.todoister.util.TaskRoomDatabase;

public class DoisterRepository {

    private final TaskDao taskDao;
    private final LiveData<List<Task>> allTasks;

    public DoisterRepository(Application application) {
        TaskRoomDatabase database = TaskRoomDatabase.getDatabase(application);
        taskDao = database.taskDao();
        allTasks = taskDao.getTasks();
    }

    public LiveData<List<Task>> getAllTasks(){
        return allTasks;
    }

    public void insertTask(Task task){
        TaskRoomDatabase.databaseWriterExecutor.execute(() -> taskDao.insertTask(task));
    }

    public void update(Task task){
        TaskRoomDatabase.databaseWriterExecutor.execute(() -> taskDao.update(task));
    }

    public void delete(Task task){
        TaskRoomDatabase.databaseWriterExecutor.execute(() ->taskDao.delete(task));
    }
}
