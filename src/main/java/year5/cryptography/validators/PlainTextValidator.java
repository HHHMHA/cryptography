package year5.cryptography.validators;

import year5.cryptography.algorithms.Cipher;

public class PlainTextValidator extends AlphabetValidator {
    private final Cipher cipher;

    public PlainTextValidator( Cipher cipher ) {
        super( "The plain text can only be lower case english letters" );
        this.cipher = cipher;
    }

    @Override
    protected String getText() {
        return cipher.getPlainText();
    }

    @Override
    protected char getLowerBound() {
        return 'a';
    }

    @Override
    protected char getUpperBound() {
        return 'z';
    }
}
