package be.yapock.controllers;

import be.yapock.models.DTOS.User_DtoFirstNameLastName;
import be.yapock.models.entities.User_;
import be.yapock.models.forms.CategoryForm;
import be.yapock.services.CategoryService;
import be.yapock.services.UserService;
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
        model.addAttribute("category", new CategoryForm());
        List<User_> userList = userService.getAll();
        List<User_DtoFirstNameLastName> userDtos = userList.stream()
                .map(User_DtoFirstNameLastName::fromEntity)
                .toList();
        model.addAttribute("userList", userDtos);
        return "/category/create";
    }

    @PostMapping("/create")
    public String postCreate(@ModelAttribute("category") @Valid CategoryForm form, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "category/create";
        categoryService.create(form.toEntity());
        return "redirect:index";
    }
}
