package be.yapock.PL.mvc.controllers;



import be.yapock.bll.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler({NotFoundException.class})
    public String handleObjectNotFound(NotFoundException ex, Model model, HttpServletRequest req){
        model.addAttribute("exception", ex);
        return "error/404";
    }
}
