package be.yapock.services.impl;

import be.yapock.models.entities.Task;
import be.yapock.repositories.TaskRepository;
import be.yapock.services.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task create(Task task) {
        task.setDateStarted(LocalDate.now());
        return taskRepository.save(task);
    }

    @Override
    public Task getOne(Long id) {
        return taskRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task upDate(Long id, Task task) {
        Task existingTask = getOne(id);
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setFinished(task.isFinished());
        return taskRepository.save(existingTask);
    }

    @Override
    public Task delete(Long id) {
        Task existingTask = getOne(id);
        taskRepository.delete(existingTask);
        return existingTask;
    }

    @Override
    public Task upDateFinishedStatus(Long id) {
        Task existingTask = getOne(id);
        existingTask.setFinished(true);
        existingTask.setDateFinished(LocalDate.now());
        return taskRepository.save(existingTask);
    }

    @Override
    public List<Task> getAllUnfinished() {
        return taskRepository.findByIsFinishedFalse();
    }

    @Override
    public List<Task> getAllFinished() {
        return taskRepository.findByIsFinishedTrue();
    }
}
