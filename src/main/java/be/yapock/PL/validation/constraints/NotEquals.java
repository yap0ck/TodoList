package be.yapock.PL.validation.constraints;

import be.yapock.PL.validation.validators.NotEqualsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // Cette annotation peut être utilisée uniquement sur les champs (fields) des classes.
@Retention(RetentionPolicy.RUNTIME) // Cette annotation sera disponible pour l'inspection à l'exécution.
@Constraint(validatedBy = NotEqualsValidator.class) // Utilise le validateur NotEqualsValidator pour effectuer la validation.
public @interface NotEquals {
    String value() default "test"; // Une valeur par défaut (optionnelle) à comparer.
    String message() default "value can't be equal to a secret word"; // Message d'erreur en cas de validation échouée.
    Class<?>[] groups() default { }; // Groupes de validation (par défaut, vide).
    Class<? extends Payload>[] payload() default {}; // Charges utiles de validation (par défaut, vide).
}
