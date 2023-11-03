package be.yapock.PL.mvc.models.dtos;

import be.yapock.dal.models.entities.Task;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskShortDTO {
    private Long id;
    private String title;
    private boolean isFinished;
    private LocalDate dateStarted;
    private LocalDate dateFinished;
    private String category;

    // Méthode statique pour convertir un objet Task en TaskShortDTO
    public static TaskShortDTO fromEntity(Task task){
        // Crée une nouvelle instance de TaskShortDTO pour stocker les données converties.
        TaskShortDTO taskShortDTO = new TaskShortDTO();
        // Copie l'identifiant de la tâche (Task) vers le DTO.
        taskShortDTO.setId(task.getId());
        // Copie le titre de la tâche (Task) vers le DTO.
        taskShortDTO.setTitle(task.getTitle());
        // Copie l'état de finition de la tâche (Task) vers le DTO.
        taskShortDTO.setFinished(task.isFinished());
        // Copie la date de début de la tâche (Task) vers le DTO.
        taskShortDTO.setDateStarted(task.getDateStarted());
        // Copie la date de fin de la tâche (Task) vers le DTO.
        taskShortDTO.setDateFinished(task.getDateFinished());
        // Copie le nom de la catégorie associée à la tâche (Task) vers le DTO.
        taskShortDTO.setCategory(task.getCategory().getName());
        // Renvoie le DTO TaskShortDTO nouvellement créé avec les données extraites.
        return taskShortDTO;
    }
}
