package be.yapock.models.forms;

import be.yapock.models.entities.User_;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.apache.catalina.User;

import java.time.LocalDate;

@Data
public class UserForm {
    @NotNull @NotBlank
    private String firstName;
    @NotNull @NotBlank
    private String lastName;
    @NotBlank @NotNull
    private String login;
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$", message = "Le mot de passe doit contenir au moins 1 lettre majuscule, 1 chiffre et 1 caractère spécial et faire 8 caractères de long.")
    private String password;
    @NotNull @Past
    private LocalDate dateOfBirth;

    public User_ toEntity(){
        User_ user = new User_();
        user.setLogin(this.login);
        user.setPassword(this.password);
        user.setDateOfBirth(this.dateOfBirth);
        user.setLastName(this.lastName);
        user.setFirstName(this.firstName);
        return user;
    }
}
