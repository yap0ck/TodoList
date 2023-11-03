package be.yapock.PL.rest;

import be.yapock.PL.mvc.models.dtos.TaskDtoFull;
import be.yapock.PL.mvc.models.dtos.TaskShortDTO;
import be.yapock.PL.mvc.models.forms.TaskForm;
import be.yapock.bll.services.TaskService;
import be.yapock.dal.models.entities.Task;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/task")
public class RestTaskController {
    private final TaskService taskService;

    public RestTaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public ResponseEntity<Set<TaskShortDTO>> getAll(){
        return ResponseEntity.ok(taskService.getAll("yapock").stream()
                .map(TaskShortDTO::fromEntity)
                .collect(Collectors.toSet()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskDtoFull> getOne(@PathVariable Long id){
        return ResponseEntity.ok(TaskDtoFull.fromEntity(taskService.getOne(id)));
    }

    @PostMapping("/create")
    public void create(@RequestBody @Valid TaskForm form){
        taskService.create(form.toEntity());
    }

    @PostMapping("/update/{id}")
    public void update(@RequestBody @Valid TaskForm form, @PathVariable Long id){
        taskService.update(id, form);
    }

    @GetMapping("delete/{id:[0-9]+}")
    public void delete(@PathVariable Long id){
        taskService.delete(id);
    }

    @GetMapping("/finished")
    public ResponseEntity<Set<TaskShortDTO>> getAllFinished(){
        return ResponseEntity.ok(taskService.getAllFinished("yapock").stream()
                .map(TaskShortDTO::fromEntity)
                .collect(Collectors.toSet()));
    }

    @DeleteMapping("/finished")
    public void deleteAllFinished(){
        taskService.deleteAllFinished("login");
    }
    @GetMapping("/unfinished")
    public ResponseEntity<Set<TaskShortDTO>> getAllUnfinished(){
        return ResponseEntity.ok(taskService.getAllUnfinished("yapock").stream()
                .map(TaskShortDTO::fromEntity)
                .collect(Collectors.toSet()));
    }
}
