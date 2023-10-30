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
    String name; // Nom de la catégorie, ne peut pas être vide et doit être non nul.
    @NotNull
    Long id; // Identifiant associé à la catégorie.

    /**
     * Convertit cette instance de CategoryForm en une instance de Category.
     * Utilisé pour créer une nouvelle catégorie à partir des données du formulaire.
     *
     * @return Une instance de Category correspondant aux données du formulaire.
     */
    public Category toEntity(){
        Category category = new Category();
        category.setName(this.name);
        category.setCreationDate(LocalDate.now()); // Date de création définie sur la date actuelle.
        return category;
    }
}