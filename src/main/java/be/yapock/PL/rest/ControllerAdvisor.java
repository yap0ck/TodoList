package be.yapock.PL.rest;

import be.yapock.PL.mvc.models.dtos.Error;
import be.yapock.bll.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Error> handleNotFoundException(NotFoundException exception, HttpServletRequest request){
        Error error = Error.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .requestmadeAt(LocalDateTime.now())
                .URI(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

}
