package be.yapock.services.impl;

import be.yapock.models.entities.Task;
import be.yapock.repositories.TaskRepository;
import be.yapock.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
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
        return taskRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public List<Task> getAllByIdDesc() {
        return taskRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public List<Task> getAllByTitleAsc() {
        return taskRepository.findAll(Sort.by(Sort.Direction.ASC,"title"));
    }

    @Override
    public List<Task> getAllByTitleDesc() {
        return taskRepository.findAll(Sort.by(Sort.Direction.DESC,"title"));
    }

    @Override
    public List<Task> getAllByFinishedAsc() {
        return taskRepository.findAll(Sort.by(Sort.Direction.ASC,"is_finished"));
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

    @Override
    @Transactional
    public List<Task> deleteAllFinished() {
        return taskRepository.deleteTaskByIsFinishedTrue();
    }
}
