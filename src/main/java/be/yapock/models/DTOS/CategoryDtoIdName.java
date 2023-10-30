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
        CategoryDtoIdName categoryDtoIdName = new CategoryDtoIdName();
        categoryDtoIdName.setName(category.getName());
        categoryDtoIdName.setId(category.getId());
        return categoryDtoIdName;
    }
}