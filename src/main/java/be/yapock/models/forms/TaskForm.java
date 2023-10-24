package be.yapock.models.forms;

import be.yapock.models.entities.Task;
import lombok.Data;

@Data
public class TaskForm {
    private String title;
    private String description;
    private boolean isFinished = false;

    public static TaskForm fromEntity(Task task){
        TaskForm taskForm = new TaskForm();
        taskForm.setTitle(task.getTitle());
        taskForm.setDescription(task.getDescription());
        taskForm.setFinished(task.isFinished());
        return taskForm;
    }

    public Task toEntity(){
        Task task = new Task();
        task.setTitle(this.title);
        task.setDescription(this.description);
        task.setFinished(this.isFinished);
        return task;
    }
}
