package be.yapock.PL.validation.validators;

import be.yapock.PL.validation.constraints.NotEquals;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotEqualsValidator implements ConstraintValidator<NotEquals, String> {
    private String notEqualValue;

    @Override
    public void initialize(NotEquals constraintAnnotation){
        notEqualValue = constraintAnnotation.value(); // Récupère la valeur à laquelle le champ ne doit pas être égal.
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        return !value.equals(notEqualValue); // Valide la valeur du champ : renvoie true si la valeur n'est pas égale à la valeur spécifiée.
    }
}
