package year5.cryptography.algorithms;

import year5.cryptography.utils.ExtendedEuclidean;
import year5.cryptography.validators.MultiplicationKeyCipherValidator;

public class MultiplicationCipher extends AlphabetCipher {
    public MultiplicationCipher() {
        var keyValidator = new MultiplicationKeyCipherValidator( this );
        encryptValidator.add( keyValidator );
        decryptValidator.add( keyValidator);
    }

    @Override
    protected int encryptSingle( int key, int plainAlphabetCharIndex ) {
        return multiplyWithModule( key, plainAlphabetCharIndex );
    }

    @Override
    protected int getDecryptionKey( int key ) {
        int alphabetSize = getAlphabetSize();
        int inverseKey = new ExtendedEuclidean( alphabetSize, key ).getX();
        while ( inverseKey < 0 )
            inverseKey += alphabetSize;
        return inverseKey;
    }

    @Override
    protected int decryptSingle( int key, int cipherAlphabetCharIndex ) {
        return multiplyWithModule( key, cipherAlphabetCharIndex );
    }

    private int multiplyWithModule( int key, int value ) {
        return value * key % getAlphabetSize();
    }
}
