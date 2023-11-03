package be.yapock.PL.mvc.controllers;

import be.yapock.PL.mvc.models.dtos.User_DtoFirstNameLastName;
import be.yapock.dal.models.entities.Category;
import be.yapock.dal.models.entities.User_;
import be.yapock.PL.mvc.models.forms.CategoryForm;
import be.yapock.bll.services.CategoryService;
import be.yapock.bll.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {
    private final CategoryService categoryService;
    private final UserService userService;

    public CategoryController(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }


    @GetMapping("/create")
    public String getCreate(Model model){
        // Crée un objet CategoryForm vide et le passe à la vue pour la création
        model.addAttribute("category", new CategoryForm());

        // Récupère la liste des utilisateurs et les convertit en DTOs pour l'affichage
        List<User_> userList = userService.getAll();
        List<User_DtoFirstNameLastName> userDtos = userList.stream()
                .map(User_DtoFirstNameLastName::fromEntity)
                .toList();

        // Passe la liste d'utilisateurs DTO à la vue
        model.addAttribute("userList", userDtos);

        //retourne la vue de création de catégorie
        return "/category/create";
    }

    @PostMapping("/create")
    public String postCreate(@ModelAttribute("category") @Valid CategoryForm form, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            // S'il y a des erreurs de validation, retourne à la vue de création avec les erreurs
            return "category/create";
        }

        // Crée une entité Category à partir du formulaire
        Category category = form.toEntity();

        // Associe un utilisateur à la catégorie en utilisant l'ID du formulaire
        category.setUser(userService.getOne(form.getId()));

        // Crée la catégorie dans le service
        categoryService.create(category);

        // Redirige vers la page d'accueil
        return "redirect:/";
    }
}
