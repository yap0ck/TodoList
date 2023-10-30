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
        model.addAttribute("user", new UserForm());
        return "user/registration";
    }

    @PostMapping("/registration")
    public String postCreate(@ModelAttribute("user") @Valid UserForm form, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "user/registration";
        }
        userService.create(form.toEntity());
        return "redirect:/";
    }
}
