package be.yapock.models.forms;

import be.yapock.models.entities.User_;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.apache.catalina.User;

import java.time.LocalDate;

@Data
public class UserForm {
    @NotNull @NotBlank
    private String firstName; // Prénom de l'utilisateur, ne peut pas être vide et doit être non nul.
    @NotNull @NotBlank
    private String lastName; // Nom de l'utilisateur, ne peut pas être vide et doit être non nul
    @NotBlank @NotNull
    private String login; // Login de l'utilisateur, ne peut pas être vide et ne doit pas être nul.
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$", message = "Le mot de passe doit contenir au moins 1 lettre majuscule, 1 chiffre et 1 caractère spécial et faire 8 caractères de long.")
    private String password; // Mot de passe de l'utilisateur, doit respecter un modèle de complexité.
    @NotNull @Past
    private LocalDate dateOfBirth; // Date de naissance de l'utilisateur, ne peut pas être dans le futur.

    /**
     * Convertit cette instance de UserForm en une instance de User_.
     * Utilisé pour créer un nouvel utilisateur à partir des données du formulaire.
     *
     * @return Une instance de User_ correspondant aux données du formulaire.
     */
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
