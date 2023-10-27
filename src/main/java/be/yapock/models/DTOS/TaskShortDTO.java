package be.yapock.models.DTOS;

import be.yapock.models.entities.Task;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskShortDTO {
    private Long id;
    private String title;
    private boolean isFinished;
    private LocalDate dateStarted;
    private LocalDate dateFinished;

    public static TaskShortDTO fromEntity(Task task){
        TaskShortDTO taskShortDTO = new TaskShortDTO();
        taskShortDTO.setId(task.getId());
        taskShortDTO.setTitle(task.getTitle());
        taskShortDTO.setFinished(task.isFinished());
        taskShortDTO.setDateStarted(task.getDateStarted());
        taskShortDTO.setDateFinished(task.getDateFinished());
        return taskShortDTO;
    }
}
