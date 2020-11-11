package year5.cryptography.algorithms;

import year5.cryptography.validators.AlphabetKeyValidator;

import java.util.ArrayList;
import java.util.List;

public class AutoKeyCipher extends AlphabetCipher {
    private int keyIndex = 0;
    private final List<Integer> restOfKey = new ArrayList<>();

    public AutoKeyCipher() {
        decryptValidator.add( new AlphabetKeyValidator( this ) );
    }

    @Override
    protected int encryptSingle( int plainAlphabetCharIndex ) {
        return 0; // Not implemented yet
    }

    @Override
    protected String getDecryptionKey() {
        return getKey();
    }

    @Override
    protected int decryptSingle( int cipherAlphabetCharIndex ) {
        int decryptedSingle = cipherAlphabetCharIndex - getNextKeyAlphabetIndex(); // both are guaranteed to be between 0-25
        if ( decryptedSingle < 0 )
            decryptedSingle += getAlphabetSize();
        restOfKey.add( decryptedSingle );
        return decryptedSingle;
    }

    private int getNextKeyAlphabetIndex() {
        String key = getKey();
        return keyIndex < key.length() ? key.charAt( keyIndex++ ) - ASCII_LOWER_ALPHABET_START_INDEX : restOfKey.get( keyIndex++ - key.length() );
    }

    @Override
    protected void setUp() {
        super.setUp();
        resetKey();
    }

    private void resetKey() {
        keyIndex = 0;
        restOfKey.clear();
    }
}
