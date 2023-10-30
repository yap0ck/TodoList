package be.yapock.services.impl;

import be.yapock.models.entities.Task;
import be.yapock.repositories.TaskRepository;
import be.yapock.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Crée une nouvelle tâche en l'ajoutant au référentiel.
     * La date de début est automatiquement définie sur la date actuelle.
     *
     * @param task La tâche à créer.
     * @return La tâche créée.
     */
    @Override
    public Task create(Task task) {
        task.setDateStarted(LocalDate.now());
        return taskRepository.save(task);
    }

    /**
     * Récupère une tâche spécifique par son identifiant.
     *
     * @param id L'identifiant de la tâche à récupérer.
     * @return La tâche correspondant à l'identifiant.
     * @throws NoSuchElementException si la tâche n'est pas trouvée.
     */
    @Override
    public Task getOne(Long id) {
        return taskRepository.findById(id).orElseThrow();
    }

    /**
     * Récupère toutes les tâches présentes dans le référentiel, triées par identifiant croissant.
     *
     * @return Une liste de toutes les tâches triées par identifiant croissant.
     */
    @Override
    public List<Task> getAll() {
        return taskRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    /**
     * Récupère toutes les tâches présentes dans le référentiel, triées par identifiant décroissant.
     *
     * @return Une liste de toutes les tâches triées par identifiant décroissant.
     */
    @Override
    public List<Task> getAllByIdDesc() {
        return taskRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    /**
     * Récupère toutes les tâches présentes dans le référentiel, triées par titre de tâche par ordre croissant.
     *
     * @return Une liste de toutes les tâches triées par titre de tâche par ordre croissant.
     */
    @Override
    public List<Task> getAllByTitleAsc() {
        return taskRepository.findAll(Sort.by(Sort.Direction.ASC,"title"));
    }

    /**
     * Récupère toutes les tâches présentes dans le référentiel, triées par titre de tâche par ordre décroissant.
     *
     * @return Une liste de toutes les tâches triées par titre de tâche par ordre décroissant.
     */
    @Override
    public List<Task> getAllByTitleDesc() {
        return taskRepository.findAll(Sort.by(Sort.Direction.DESC,"title"));
    }

    /**
     * Récupère toutes les tâches présentes dans le référentiel, triées par statut "isFinished" par ordre croissant.
     *
     * @return Une liste de toutes les tâches triées par statut "isFinished" par ordre croissant.
     */
    @Override
    public List<Task> getAllByFinishedAsc() {
        return taskRepository.findAll(Sort.by(Sort.Direction.ASC,"is_finished"));
    }

    /**
     * Marque une tâche comme terminée en mettant à jour son statut "isFinished" et en définissant la date de fin sur la date actuelle.
     *
     * @param id L'identifiant de la tâche à marquer comme terminée.
     * @return La tâche mise à jour.
     * @throws NoSuchElementException si la tâche n'est pas trouvée.
     */
    @Override
    public Task upDateFinishedStatus(Long id) {
        Task existingTask = getOne(id);
        existingTask.setFinished(true);
        existingTask.setDateFinished(LocalDate.now());
        return taskRepository.save(existingTask);
    }

    /**
     * Récupère toutes les tâches non terminées présentes dans le référentiel.
     *
     * @return Une liste de toutes les tâches non terminées.
     */
    @Override
    public List<Task> getAllUnfinished() {
        return taskRepository.findByIsFinishedFalse();
    }

    /**
     * Récupère toutes les tâches terminées présentes dans le référentiel.
     *
     * @return Une liste de toutes les tâches terminées.
     */
    @Override
    public List<Task> getAllFinished() {
        return taskRepository.findByIsFinishedTrue();
    }

    /**
     * Supprime toutes les tâches terminées du référentiel.
     *
     * @return La liste des tâches supprimées.
     */
    @Override
    @Transactional
    public List<Task> deleteAllFinished() {
        return taskRepository.deleteTaskByIsFinishedTrue();
    }
}
