package be.yapock.models.DTOS;

import be.yapock.models.entities.Category;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link be.yapock.models.entities.Category}
 */
@Data
public class CategoryDtoIdName{
    Long id;
    String name;

    public static CategoryDtoIdName fromEntity(Category category){
        // Crée une nouvelle instance de CategoryDtoIdName pour stocker les données converties.
        CategoryDtoIdName categoryDtoIdName = new CategoryDtoIdName();

        // Copie la valeur de la propriété 'name' de l'entité Category vers le DTO.
        categoryDtoIdName.setName(category.getName());

        // Copie la valeur de la propriété 'id' de l'entité Category vers le DTO.
        categoryDtoIdName.setId(category.getId());

        // Renvoie le DTO CategoryDtoIdName nouvellement créé avec les données extraites.
        return categoryDtoIdName;
    }
}