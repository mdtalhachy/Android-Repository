package rocks.talha.todoister.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Task {
    @ColumnInfo(name = "task_id")
    @PrimaryKey(autoGenerate = true)
    public long taskId;
    public String task;
    public Priority priority;

    @ColumnInfo(name = "date_created")
    public Date dateCreated;

    @ColumnInfo(name = "due_date")
    public Date dueDate;

    @ColumnInfo(name = "is_done")
    public boolean isDone;

    public Task(String task, Priority priority, Date dateCreated, Date dueDate, boolean isDone) {
        this.task = task;
        this.priority = priority;
        this.dateCreated = dateCreated;
        this.dueDate = dueDate;
        this.isDone = isDone;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
