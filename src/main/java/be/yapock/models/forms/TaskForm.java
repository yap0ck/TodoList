package be.yapock.models.forms;

import be.yapock.models.entities.Task;
import be.yapock.validation.constraints.NotEquals;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskForm {
    @NotNull @NotBlank @NotEquals
    private String title;
    @NotBlank
    private String description;
    private boolean isFinished = false;


    public Task toEntity(){
        Task task = new Task();
        task.setTitle(this.title);
        task.setDescription(this.description);
        task.setFinished(this.isFinished);
        return task;
    }
}
