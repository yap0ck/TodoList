package be.yapock.PL.mvc.models.forms;

import be.yapock.dal.models.entities.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Category}
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