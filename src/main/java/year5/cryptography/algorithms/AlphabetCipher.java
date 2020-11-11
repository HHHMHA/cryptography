package year5.cryptography.algorithms;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import year5.cryptography.validators.*;

public abstract class AlphabetCipher implements Cipher {
    // TODO: make dynamic (input)
    private static final int ALPHABET_SIZE = 26;

    protected static final int ASCII_LOWER_ALPHABET_START_INDEX = 'a';
    protected static final int ASCII_UPPER_ALPHABET_START_INDEX = 'A';

    @Getter @Setter( AccessLevel.PRIVATE )
    private String plainText;
    @Getter @Setter( AccessLevel.PRIVATE )
    private String cipherText;
    @Getter @Setter( AccessLevel.PRIVATE )
    private String key;

    protected final CompositeValidator encryptValidator = new CompositeValidator();
    protected final CompositeValidator decryptValidator = new CompositeValidator();

    public AlphabetCipher() {
        encryptValidator.add( new PlainTextValidator( this ) );
        decryptValidator.add( new CipherTextValidator( this ) );
    }

    @Override
    public String encrypt( String plainText, String key ) {
        setPlainText( plainText );
        setKey( key );
        encryptValidator.validate();

        setUp();

        StringBuilder cipherTextBuilder = new StringBuilder();

        for ( int i = 0; i < plainText.length(); ++i ) {
            char cipherChar = getCipherChar( plainText.charAt( i ) );
            cipherTextBuilder.append( cipherChar );
        }

        return cipherTextBuilder.toString();
    }

    protected void setUp() {
        // Optional setup before encryption/decryption but after validation
    }

    private char getCipherChar( char plainChar ) {
        int plainAlphabetCharIndex = plainChar - ASCII_LOWER_ALPHABET_START_INDEX;
        int cipherAlphabetCharIndex = encryptSingle( plainAlphabetCharIndex ); // TODO: Maybe a mod here would be better for safety.
        int cipherCharIndex = cipherAlphabetCharIndex + ASCII_UPPER_ALPHABET_START_INDEX;
        return (char) cipherCharIndex;
    }

    protected abstract int encryptSingle( int plainAlphabetCharIndex );

    @Override
    public String decrypt( String cipherText, String key ) {
        setCipherText( cipherText );
        setKey( key ); // set the normal key so it can be validated and later converted to decryption key if needed
        decryptValidator.validate();
        setKey( getDecryptionKey() );

        setUp();

        StringBuilder plainTextBuilder = new StringBuilder();

        for ( int i = 0; i < cipherText.length(); ++i ) {
            char plainChar = getPlainChar( cipherText.charAt( i ) );
            plainTextBuilder.append( plainChar );
        }

        return plainTextBuilder.toString();
    }

    protected abstract String getDecryptionKey();

    private char getPlainChar( char cipherChar ) {
        int cipherAlphabetCharIndex = cipherChar - ASCII_UPPER_ALPHABET_START_INDEX;
        int plainAlphabetCharIndex = decryptSingle( cipherAlphabetCharIndex );
        int plainCharIndex = plainAlphabetCharIndex + ASCII_LOWER_ALPHABET_START_INDEX;
        return (char) plainCharIndex;
    }

    protected abstract int decryptSingle( int cipherAlphabetCharIndex );

    public int getAlphabetSize() {
        return ALPHABET_SIZE;
    }
}
