package be.yapock.bll.services;

import be.yapock.PL.mvc.models.forms.TaskForm;
import be.yapock.dal.models.entities.Task;

import java.util.List;
import java.util.Set;

public interface TaskService {
    Task create(Task task);
    Task getOne(Long id);
    Set<Task> getAll(String login);
    void delete(Long id);
    void update(Long id, TaskForm form);
    List<Task> getAllByIdDesc();
    List<Task> getAllByTitleAsc();
    List<Task> getAllByTitleDesc();
    List<Task> getAllByFinishedAsc();
    Task upDateFinishedStatus(Long id);
    Set<Task> getAllUnfinished(String login);
    Set<Task> getAllFinished(String login);
    void deleteAllFinished(String login);
}
