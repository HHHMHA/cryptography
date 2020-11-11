package year5.cryptography.validators;

import year5.cryptography.algorithms.AutoKeyCipher;
import year5.cryptography.algorithms.Cipher;

public class AlphabetKeyValidator extends AlphabetValidator {
    private Cipher cipher;

    public AlphabetKeyValidator( Cipher cipher ) {
        super( "The key can only be lower case english letters" );
        this.cipher = cipher;
    }

    @Override
    protected String getText() {
        return cipher.getKey();
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
