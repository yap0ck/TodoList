package be.yapock.models.DTOS;

import lombok.Data;

@Data
public class TaskShortDTO {
    private Long id;
    private String title;
    private boolean isFinished;
}
