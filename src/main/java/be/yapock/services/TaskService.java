package be.yapock.services;

import be.yapock.models.entities.Task;

import java.util.List;

public interface TaskService {
    Task create(Task task);
    Task getOne(Long id);
    List<Task> getAll();
    Task upDate(Long id, Task task);
    Task delete(Long id);

    Task upDateFinishedStatus(Long id);

    List<Task> getAllUnfinished();
    List<Task> getAllFinished();
    List<Task> deleteAllFinished();
}
