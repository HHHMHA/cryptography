package year5.cryptography.validators;

import year5.cryptography.algorithms.Cipher;

public class CipherTextValidator extends AlphabetValidator {
    private final Cipher cipher;

    public CipherTextValidator( Cipher cipher ) {
        super( "The cipher text can only be upper case english letters" );
        this.cipher = cipher;
    }

    @Override
    protected String getText() {
        return cipher.getCipherText();
    }

    @Override
    protected char getLowerBound() {
        return 'A';
    }

    @Override
    protected char getUpperBound() {
        return 'Z';
    }
}
