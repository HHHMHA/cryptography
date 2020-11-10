package year5.cryptography.validators;

import year5.cryptography.algorithms.AlphabetCipher;
import year5.cryptography.exceptions.IllegalKeyException;
import year5.cryptography.utils.ExtendedEuclidean;

public class MultiplicationKeyCipherValidator extends Validator {
    private final AlphabetCipher cipher;

    public MultiplicationKeyCipherValidator( AlphabetCipher cipher ) {
        super( "The key isn't relatively prime to alphabet size." );
        this.cipher = cipher;
    }

    @Override
    void validate() throws IllegalKeyException {
        int alphabetSize = cipher.getAlphabetSize();
        int key = cipher.getKey();

        ExtendedEuclidean extendedEuclidean = new ExtendedEuclidean( alphabetSize, key );
        if ( !extendedEuclidean.AreRelativelyPrime() )
            throw new IllegalKeyException( getMessage(), key, alphabetSize );
    }
}
