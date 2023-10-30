package be.yapock.models.forms;

import be.yapock.models.entities.Category;
import be.yapock.models.entities.User_;
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

@Value
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class CategoryForm implements Serializable {
    @NotNull @NotBlank
    String name;
    @NotNull
    User_ user;


    public Category toEntity(){
        Category category = new Category();
        category.setName(this.name);
        category.setCreationDate(LocalDate.now());
        category.setUser(this.user);
        return category;
    }
}