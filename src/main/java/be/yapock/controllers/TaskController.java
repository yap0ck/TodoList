package be.yapock.controllers;

import be.yapock.models.DTOS.CategoryDtoIdName;
import be.yapock.models.DTOS.TaskDtoFull;
import be.yapock.models.DTOS.TaskShortDTO;
import be.yapock.models.entities.Category;
import be.yapock.models.entities.Task;
import be.yapock.models.forms.TaskForm;
import be.yapock.services.CategoryService;
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
    private final CategoryService categoryService;

    public TaskController(TaskService taskService, CategoryService categoryService) {
        this.taskService = taskService;
        this.categoryService = categoryService;
    }

    @GetMapping("/create")
    public String getCreate(Model model){
        // Crée un objet TaskForm vide et le passe à la vue pour la création
        model.addAttribute("task", new TaskForm());

        // Récupère la liste des catégories et les convertit en DTOs pour l'affichage
        List<Category> categories = categoryService.getAll();
        List<CategoryDtoIdName> categoryDtos = categories.stream()
                .map(CategoryDtoIdName::fromEntity)
                .toList();

        // Passe la liste de catégories DTO à la vue
        model.addAttribute("categories", categoryDtos);

        // Retourne la vue de création de tâche
        return "task/create";
    }

    @PostMapping("/create")
    // Vérifie s'il y a des erreurs de validation dans le formulaire
    public String postCreate(@ModelAttribute("task") @Valid TaskForm form, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            // S'il y a des erreurs, retourne la vue de création de tâche avec les messages d'erreur
            return "task/create";
        }

        // Convertit le formulaire (TaskForm) en entité Task
        Task task = form.toEntity();

        // Récupère la catégorie associée à la tâche à partir du formulaire
        task.setCategory(categoryService.getOne(form.getCategoryId()));

        // Enregistre la tâche créée
        taskService.create(task);

        // Redirige l'utilisateur vers la liste des tâches
        return "redirect:/task";
    }

    @GetMapping
    public String findAll(Model model){
        // Récupère la liste de toutes les tâches
        List<Task> taskList = taskService.getAll();

        // Convertit la liste de tâches en une liste de DTO (TaskShortDTO)
        List<TaskShortDTO> dtoList = taskList.stream()
                .map(TaskShortDTO::fromEntity)
                .toList();

        // Ajoute la liste de DTO à l'objet Model pour l'affichage dans la vue
        model.addAttribute("tasklist",dtoList);

        // Retourne la vue "task/index" pour afficher la liste de tâches
        return "task/index";
    }

    @GetMapping("finished/{id}")
    public String finished(@PathVariable Long id){
        // Appelle le service pour mettre à jour l'état "terminé" de la tâche avec l'ID spécifié
        taskService.upDateFinishedStatus(id);

        // Redirige l'utilisateur vers la page des tâches après la mise à jour
        return "redirect:/task";
    }

    @GetMapping("/filterUnfinished")
    public String findAllUnfinished(Model model){
        // Récupère la liste des tâches non terminées en utilisant le service
        List<Task> taskList = taskService.getAllUnfinished();

        // Convertit la liste des tâches en une liste de DTO (Data Transfer Objects) pour l'affichage
        List<TaskShortDTO> dtoList = taskList.stream()
                .map(TaskShortDTO::fromEntity)
                .toList();

        // Ajoute la liste de tâches non terminées au modèle pour l'affichage dans la vue
        model.addAttribute("taskUnfinishedList", dtoList);

        // Retourne le nom de la vue à afficher (dans ce cas, "task/filterUnfinished")
        return "task/filterUnfinished";
    }

    @GetMapping("/filterFinished")
    public String findAllFinished(Model model){
        // Récupère la liste des tâches terminées en utilisant le service
        List<Task> taskList = taskService.getAllFinished();

        // Convertit la liste des tâches en une liste de DTO (Data Transfer Objects) pour l'affichage
        List<TaskShortDTO> dtoList = taskList.stream()
                .map(TaskShortDTO::fromEntity)
                .toList();

        // Ajoute la liste de tâches terminées au modèle pour l'affichage dans la vue
        model.addAttribute("taskFinishedList", dtoList);

        // Retourne le nom de la vue à afficher (dans ce cas, "task/filterFinished")
        return "task/filterFinished";
    }

    @GetMapping("/deleteAllFinished")
    public String deleteAllFinished(){
        // Utilise le service taskService pour supprimer toutes les tâches terminées
        taskService.deleteAllFinished();

        // Redirige l'utilisateur vers la page d'accueil des tâches (en utilisant "redirect:/task")
        return "redirect:/task";
    }

    @GetMapping("/filterNoneOrderByIdDesc")
    public String orderByIdDesc(Model model){
        // Utilise le service taskService pour récupérer toutes les tâches triées par identifiant décroissant
        List<Task> taskList = taskService.getAllByIdDesc();

        // Convertit les tâches en DTO (Data Transfer Objects) pour l'affichage
        List<TaskShortDTO> dtoList = taskList.stream()
                .map(TaskShortDTO::fromEntity)
                .toList();

        // Ajoute la liste triée à l'objet Model pour l'affichage dans la vue
        model.addAttribute("orderByIdDesc", dtoList);

        // Retourne le nom de la vue qui affichera la liste triée
        return "task/filterNoneOrderByIdDesc";
    }

    @GetMapping("/filterNoneOrderByTitleAsc")
    public String AllOrderByTitleAsc(Model model){
        // Utilise le service taskService pour récupérer toutes les tâches triées par titre en ordre croissant
        List<Task> taskList = taskService.getAllByTitleAsc();

        // Convertit les tâches en DTO (Data Transfer Objects) pour l'affichage
        List<TaskShortDTO> dtoList = taskList.stream()
                .map(TaskShortDTO::fromEntity)
                .toList();

        // Ajoute la liste triée à l'objet Model pour l'affichage dans la vue
        model.addAttribute("allOrderByTitleAsc", dtoList);

        // Retourne le nom de la vue qui affichera la liste triée
        return "task/filterNoneOrderByTitleAsc";
    }

    @GetMapping("/filterNoneOrderByTitleDesc")
    public String AllOrderByTitleDesc(Model model){
        // Utilise le service taskService pour récupérer toutes les tâches triées par titre en ordre décroissant
        List<Task> taskList = taskService.getAllByTitleDesc();

        // Convertit les tâches en DTO (Data Transfer Objects) pour l'affichage
        List<TaskShortDTO> dtoList = taskList.stream()
                .map(TaskShortDTO::fromEntity)
                .toList();

        // Ajoute la liste triée à l'objet Model pour l'affichage dans la vue
        model.addAttribute("allOrderByTitleDesc", dtoList);

        // Retourne le nom de la vue qui affichera la liste triée
        return "task/filterNoneOrderByTitleDesc";
    }

    @GetMapping("/{id}")
    public String detailTask(@PathVariable Long id, Model model){
        Task task = taskService.getOne(id);
        TaskDtoFull dtoFull = TaskDtoFull.fromEntity(task);
        model.addAttribute("tache", dtoFull);
        return "task/detail";
    }
}
