package be.yapock.bll.services.impl;

import be.yapock.PL.mvc.models.forms.TaskForm;
import be.yapock.bll.exceptions.NotFoundException;
import be.yapock.dal.models.entities.Task;
import be.yapock.dal.repositories.TaskRepository;
import be.yapock.bll.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

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
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    /**
     * Récupère toutes les tâches présentes dans le référentiel, triées par identifiant croissant.
     *
     * @return Une liste de toutes les tâches triées par identifiant croissant.
     */
    @Override
    public Set<Task> getAll(String login) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getCategory().getUser().getLogin().equals(login))
                .collect(Collectors.toSet());
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
    public Set<Task> getAllUnfinished(String login) {
        return taskRepository.findByIsFinishedFalse().stream()
                .filter(task -> task.getCategory().getUser().getLogin().equals(login))
                .collect(Collectors.toSet());
    }

    /**
     * Récupère toutes les tâches terminées présentes dans le référentiel.
     *
     * @return Une liste de toutes les tâches terminées.
     */
    @Override
    public Set<Task> getAllFinished(String login) {
        return taskRepository.findByIsFinishedTrue().stream()
                .filter(task -> task.getCategory().getUser().getLogin().equals(login))
                .collect(Collectors.toSet());
    }

    /**
     * Supprime toutes les tâches terminées du référentiel.
     *
     * @return La liste des tâches supprimées.
     */
    @Override
    @Transactional
    public void deleteAllFinished(String login) {
        return taskRepository.deleteTaskByIsFinishedTrue(login);
    }

    @Override
    public void update(Long id, TaskForm form) {
        if(form == null) throw new IllegalArgumentException("form can't be null");

        Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task not found"));
        task.setTitle(form.getTitle());
        task.setDescription(form.getDescription());
        task.setFinished(form.isFinished());
        if (task.isFinished()) task.setDateFinished(LocalDate.now());
        taskRepository.save(task);
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
