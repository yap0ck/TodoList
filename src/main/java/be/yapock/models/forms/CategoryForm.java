package be.yapock.models.forms;

import be.yapock.models.entities.Category;
import be.yapock.models.entities.User_;
import be.yapock.services.UserService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link be.yapock.models.entities.Category}
 */


@Data
public class CategoryForm implements Serializable {

    @NotNull @NotBlank
    String name;
    @NotNull
    Long id;


    public Category toEntity(){


        Category category = new Category();
        category.setName(this.name);
        category.setCreationDate(LocalDate.now());
        return category;
    }
}