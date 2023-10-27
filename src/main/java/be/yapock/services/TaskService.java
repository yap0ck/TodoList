package be.yapock.services;

import be.yapock.models.entities.Task;

import java.util.List;

public interface TaskService {
    Task create(Task task);
    Task getOne(Long id);
    List<Task> getAll();
    List<Task> getAllByIdDesc();
    List<Task> getAllByTitleAsc();
    List<Task> getAllByTitleDesc();
    List<Task> getAllByFinishedAsc();
    Task upDateFinishedStatus(Long id);
    List<Task> getAllUnfinished();
    List<Task> getAllFinished();
    List<Task> deleteAllFinished();
}
