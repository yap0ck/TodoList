package be.yapock.controllers;

import be.yapock.models.forms.UserForm;
import be.yapock.services.UserService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String getCreate(Model model){
        // Crée un nouvel objet UserForm et l'ajoute à l'objet Model pour le formulaire d'inscription
        model.addAttribute("user", new UserForm());
        // Retourne le nom de la vue qui affichera le formulaire d'inscription
        return "user/registration";
    }

    @PostMapping("/registration")
    public String postCreate(@ModelAttribute("user") @Valid UserForm form, BindingResult bindingResult){
        // Vérifie s'il y a des erreurs de validation dans le formulaire
        if (bindingResult.hasErrors()){
            // S'il y a des erreurs, retourne la vue du formulaire d'inscription pour les afficher
            return "user/registration";
        }

        // Si le formulaire est valide, crée un nouvel utilisateur à partir des données du formulaire
        userService.create(form.toEntity());

        // Redirige l'utilisateur vers la page d'accueil (ou une autre page souhaitée) après l'inscription réussie
        return "redirect:/";
    }
}
