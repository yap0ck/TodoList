package be.yapock.controllers;

import be.yapock.models.DTOS.TaskShortDTO;
import be.yapock.models.entities.Task;
import be.yapock.models.forms.TaskForm;
import be.yapock.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/create")
    public String getCreate(Model model){
        model.addAttribute("task", new TaskForm());
        return "task/create";
    }

    @PostMapping("/create")
    public String postCreate(@ModelAttribute("task") @Valid TaskForm form, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "task/create";
        }
        taskService.create(form.toEntity());
        return "redirect:/task";
    }

    @GetMapping
    public String findAll(Model model){
        List<Task> taskList = taskService.getAll();
        List<TaskShortDTO> dtoList = taskList.stream()
                .map(TaskShortDTO::fromEntity)
                .toList();
        model.addAttribute("tasklist",dtoList);
        return "task/index";
    }

    @GetMapping("finished/{id}")
    public String finished(@PathVariable Long id){
        taskService.upDateFinishedStatus(id);
        return "redirect:/task";
    }

    @GetMapping("/filterUnfinished")
    public String findAllUnfinished(Model model){
        List<Task> taskList = taskService.getAllUnfinished();
        List<TaskShortDTO> dtoList = taskList.stream()
                .map(TaskShortDTO::fromEntity)
                .toList();
        model.addAttribute("taskUnfinishedList", dtoList);
        return "task/filterUnfinished";
    }

    @GetMapping("/filterFinished")
    public String findAllFinished(Model model){
        List<Task> taskList = taskService.getAllFinished();
        List<TaskShortDTO> dtoList = taskList.stream()
                .map(TaskShortDTO::fromEntity)
                .toList();
        model.addAttribute("taskFinishedList", dtoList);
        return "task/filterFinished";
    }

    @GetMapping("/deleteAllFinished")
    public String deleteAllFinished(){
        taskService.deleteAllFinished();
        return "redirect:/task";
    }

    @GetMapping("/filterNoneOrderByIdDesc")
    public String orderByIdDesc(Model model){
        List<Task> taskList = taskService.getAllByIdDesc();
        List<TaskShortDTO> dtoList = taskList.stream()
                .map(TaskShortDTO::fromEntity)
                .toList();
        model.addAttribute("orderByIdDesc", dtoList);
        return "task/filterNoneOrderByIdDesc";
    }

    @GetMapping("/filterNoneOrderByTitleAsc")
    public String AllOrderByTitleAsc(Model model){
        List<Task> taskList = taskService.getAllByTitleAsc();
        List<TaskShortDTO> dtoList = taskList.stream()
                .map(TaskShortDTO::fromEntity)
                .toList();
        model.addAttribute("allOrderByTitleAsc", dtoList);
        return "task/filterNoneOrderByTitleAsc";
    }

    @GetMapping("/filterNoneOrderByTitleDesc")
    public String AllOrderByTitleDesc(Model model){
        List<Task> taskList = taskService.getAllByTitleDesc();
        List<TaskShortDTO> dtoList = taskList.stream()
                .map(TaskShortDTO::fromEntity)
                .toList();
        model.addAttribute("allOrderByTitleDesc", dtoList);
        return "task/filterNoneOrderByTitleDesc";
    }

    @GetMapping("/filterNoneOrderByFinishedAsc")
    public String AllOrderByFinishedAsc(Model model){
        List<Task> taskList = taskService.getAllByFinishedAsc();
        List<TaskShortDTO> dtoList = taskList.stream()
                .map(TaskShortDTO::fromEntity)
                .toList();
        model.addAttribute("allOrderByFinishedAsc", dtoList);
        return "task/filterNoneOrderByFinishedAsc";
    }
}
