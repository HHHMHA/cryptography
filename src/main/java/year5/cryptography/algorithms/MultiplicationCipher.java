package year5.cryptography.algorithms;

import year5.cryptography.utils.ExtendedEuclidean;
import year5.cryptography.validators.MultiplicationKeyCipherValidator;

public class MultiplicationCipher extends AlphabetCipher {
    private int cachedKey;

    public MultiplicationCipher() {
        var keyValidator = new MultiplicationKeyCipherValidator( this );
        encryptValidator.add( keyValidator );
        decryptValidator.add( keyValidator);
    }

    @Override
    protected void setUp() {
        super.setUp();
        cachedKey = Integer.parseInt( getKey() );
    }

    @Override
    protected int encryptSingle( int plainAlphabetCharIndex ) {
        return multiplyWithModule( cachedKey, plainAlphabetCharIndex );
    }

    @Override
    protected String getDecryptionKey() {
        int alphabetSize = getAlphabetSize();
        int inverseKey = new ExtendedEuclidean( alphabetSize, Integer.parseInt( getKey() ) ).getX(); // Cached key can only be used after setUp is called but this called before setUp
        while ( inverseKey < 0 )
            inverseKey += alphabetSize;
        return String.valueOf( inverseKey );
    }

    @Override
    protected int decryptSingle( int cipherAlphabetCharIndex ) {
        return multiplyWithModule( cachedKey, cipherAlphabetCharIndex );
    }

    private int multiplyWithModule( int key, int value ) {
        return value * key % getAlphabetSize();
    }
}
