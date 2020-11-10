package year5.cryptography.validators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public abstract class Validator {
    private String message = "";

    abstract void validate() throws IllegalArgumentException;
}
