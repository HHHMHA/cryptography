package year5.cryptography.algorithms;

import year5.cryptography.validators.*;

public abstract class AlphabetCipher implements Cipher {
    // TODO: make dynamic (input)
    private static final int ALPHABET_SIZE = 26;
    private static final int ASCII_LOWER_ALPHABET_START_INDEX = 'a';
    private static final int ASCII_UPPER_ALPHABET_START_INDEX = 'A';

    private String plainText;
    private String cipherText;
    private int key;

    protected final CompositeValidator encryptValidator = new CompositeValidator();
    protected final CompositeValidator decryptValidator = new CompositeValidator();

    public AlphabetCipher() {
        encryptValidator.add( new PlainTextValidator( this ) );
        decryptValidator.add( new CipherTextValidator( this ) );
    }

    @Override
    public String encrypt( String plainText, int key ) {
        this.plainText = plainText;
        this.key = key;

        encryptValidator.validate();

        StringBuilder cipherTextBuilder = new StringBuilder();

        for ( int i = 0; i < plainText.length(); ++i ) {
            char cipherChar = getCipherChar( plainText.charAt( i ), key );
            cipherTextBuilder.append( cipherChar );
        }

        return cipherTextBuilder.toString();
    }

    private char getCipherChar( char plainChar, int key ) {
        int plainAlphabetCharIndex = plainChar - ASCII_LOWER_ALPHABET_START_INDEX;
        int cipherAlphabetCharIndex = encryptSingle( key, plainAlphabetCharIndex ); // TODO: Maybe a mod here would be better for safety.
        int cipherCharIndex = cipherAlphabetCharIndex + ASCII_UPPER_ALPHABET_START_INDEX;
        return (char) cipherCharIndex;
    }

    protected abstract int encryptSingle( int key, int plainAlphabetCharIndex );

    @Override
    public String decrypt( String cipherText, int key ) {
        this.cipherText = cipherText;
        this.key = key;

        decryptValidator.validate();

        int inverseKey = getDecryptionKey( key );

        StringBuilder plainTextBuilder = new StringBuilder();

        for ( int i = 0; i < cipherText.length(); ++i ) {
            char plainChar = getPlainChar( cipherText.charAt( i ), inverseKey );
            plainTextBuilder.append( plainChar );
        }

        return plainTextBuilder.toString();
    }

    protected abstract int getDecryptionKey( int key );

    private char getPlainChar( char cipherChar, int key ) {
        int cipherAlphabetCharIndex = cipherChar - ASCII_UPPER_ALPHABET_START_INDEX;
        int plainAlphabetCharIndex = decryptSingle( key, cipherAlphabetCharIndex );
        int plainCharIndex = plainAlphabetCharIndex + ASCII_LOWER_ALPHABET_START_INDEX;
        return (char) plainCharIndex;
    }

    protected abstract int decryptSingle( int key, int cipherAlphabetCharIndex );

    public static int getAlphabetSize() {
        return ALPHABET_SIZE;
    }

    @Override
    public String getPlainText() {
        return plainText;
    }

    @Override
    public String getCipherText() {
        return cipherText;
    }

    @Override
    public int getKey() {
        return key;
    }
}
