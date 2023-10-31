package be.yapock.models.DTOS;

import be.yapock.models.entities.Category;
import be.yapock.models.entities.Task;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link be.yapock.models.entities.Task}
 */
@Data
public class TaskDtoFull implements Serializable {
    String title;
    String description;
    boolean isFinished;
    LocalDate dateStarted;
    LocalDate dateFinished;
    String category;

    public static TaskDtoFull fromEntity(Task task){
        TaskDtoFull dtoFull = new TaskDtoFull();
        dtoFull.setCategory(task.getCategory().getName());
        dtoFull.setFinished(task.isFinished());
        dtoFull.setTitle(task.getTitle());
        dtoFull.setDescription(task.getDescription());
        dtoFull.setDateStarted(task.getDateStarted());
        dtoFull.setDateFinished(task.getDateFinished());
        return dtoFull;
    }
}