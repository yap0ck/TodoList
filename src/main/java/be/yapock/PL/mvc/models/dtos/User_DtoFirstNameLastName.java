package be.yapock.PL.mvc.models.dtos;

import be.yapock.dal.models.entities.User_;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link User_}
 */

@Data
@NoArgsConstructor(force = true)
public class User_DtoFirstNameLastName implements Serializable {
    private Long id;
    private String lastName;
    private String firstName;
    // Méthode statique pour convertir un objet User_ en User_DtoFirstNameLastName
    public static User_DtoFirstNameLastName fromEntity(User_ user){
    // Crée une nouvelle instance de User_DtoFirstNameLastName pour stocker les données converties
        User_DtoFirstNameLastName userDto = new User_DtoFirstNameLastName();
        // Copie l'identifiant de l'utilisateur (User_) vers le DTO.
        userDto.setId(user.getId());
        // Copie le prénom de l'utilisateur (User_) vers le DTO.
        userDto.setFirstName(user.getFirstName());
        // Copie le nom de famille de l'utilisateur (User_) vers le DTO.
        userDto.setLastName(user.getLastName());
        // Renvoie le DTO User_DtoFirstNameLastName nouvellement créé avec les données extraites.
        return userDto;
    }
}