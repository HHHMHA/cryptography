package year5.cryptography.validators;

import java.util.ArrayList;
import java.util.List;

public class CompositeValidator extends Validator {
    private final List<Validator> validators = new ArrayList<>();

    @Override
    public void validate() throws IllegalArgumentException {
        for ( Validator validator : validators )
            validator.validate();
    }

    public void add( Validator validator ) {
        validators.add( validator );
    }
}
