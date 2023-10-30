package be.yapock.models.DTOS;

import be.yapock.models.entities.User_;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link be.yapock.models.entities.User_}
 */

@Data
@NoArgsConstructor(force = true)
public class User_DtoFirstNameLastName implements Serializable {
    private Long id;
    private String lastName;
    private String firstName;

    public static User_DtoFirstNameLastName fromEntity(User_ user){
        User_DtoFirstNameLastName userDto = new User_DtoFirstNameLastName();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        return userDto;
    }
}