package year5.cryptography.validators;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class AlphabetValidator extends Validator {

    public AlphabetValidator( String message ) {
        super( message );
    }

    @Override
    public void validate() throws IllegalArgumentException {
        String text = getText();
        if ( text == null || text.isEmpty() || !isValidAlphabet( text, getLowerBound(), getUpperBound() ) )
            throw new IllegalArgumentException( getMessage() );
    }

    protected abstract String getText();

    protected abstract char getLowerBound();

    protected abstract char getUpperBound();

    private boolean isValidAlphabet( String text, char lowerBound, char upperBound ) {
        for ( int i = 0; i < text.length(); ++i ) {
            char c = text.charAt( i );
            if ( c < lowerBound || c > upperBound )
                return false;
        }
        return true;
    }
}
