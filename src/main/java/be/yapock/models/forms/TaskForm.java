package be.yapock.models.forms;

import be.yapock.models.entities.Task;
import be.yapock.validation.constraints.NotEquals;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskForm {
    @NotNull @NotBlank @NotEquals
    private String title; // Titre de la tâche, ne peut pas être vide et doit être non nul.
    @NotBlank
    private String description; // Description de la tâche, peut être vide mais ne doit pas être nul.
    private boolean isFinished = false; // Statut de la tâche, par défaut, non terminée.
    private Long categoryId; // Identifiant de la catégorie à laquelle la tâche est associée.

    /**
     * Convertit cette instance de TaskForm en une instance de Task.
     * Utilisé pour créer une nouvelle tâche à partir des données du formulaire.
     *
     * @return Une instance de Task correspondant aux données du formulaire.
     */
    public Task toEntity(){
        Task task = new Task();
        task.setTitle(this.title);
        task.setDescription(this.description);
        task.setFinished(this.isFinished);
        return task;
    }
}
