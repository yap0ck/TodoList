package be.yapock.models.DTOS;

import be.yapock.models.entities.Task;
import lombok.Data;

@Data
public class TaskShortDTO {
    private Long id;
    private String title;
    private boolean isFinished;

    public static TaskShortDTO fromEntity(Task task){
        TaskShortDTO taskShortDTO = new TaskShortDTO();
        taskShortDTO.setId(task.getId());
        taskShortDTO.setTitle(task.getTitle());
        taskShortDTO.setFinished(task.isFinished());
        return taskShortDTO;
    }
}
