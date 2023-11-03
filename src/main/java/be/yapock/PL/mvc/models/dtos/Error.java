package be.yapock.PL.mvc.models.dtos;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class Error {
    private String message;
    private HttpStatus status;
    private LocalDateTime requestmadeAt;
    private String URI;
}
