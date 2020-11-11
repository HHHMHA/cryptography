package year5.cryptography.algorithms;

import org.junit.jupiter.api.Test;
import year5.cryptography.exceptions.IllegalKeyException;

import static org.junit.jupiter.api.Assertions.*;

public class MultiplicationCipherTest {
    public static final String KEY = "5";
    public static final String PLAIN_TEXT = "zend";
    public static final String CIPHER_TEXT = "VUNP";
    public final Cipher cipher = new MultiplicationCipher();

    @Test
    void testEncrypt() {
        assertEquals( CIPHER_TEXT, cipher.encrypt( PLAIN_TEXT, KEY ) );
    }

    @Test
    void testEncryptWithNonValidKey() {
        String invalidKey = "8";
        IllegalKeyException exception = assertThrows( IllegalKeyException.class, () -> cipher.encrypt( PLAIN_TEXT, invalidKey ) );
        assertEquals( "The key isn't relatively prime to alphabet size.", exception.getMessage() );
        assertEquals( invalidKey, exception.getKey() );
    }

    @Test
    void testEncryptNonAlphabetText() {
        String plainText = PLAIN_TEXT.toUpperCase();
        assertThrows( IllegalArgumentException.class, () -> cipher.encrypt( plainText, KEY ) );
    }

    @Test
    void testDecrypt() {
        assertEquals( PLAIN_TEXT, cipher.decrypt( CIPHER_TEXT, KEY ) );
    }

    @Test
    void testDecryptWithFalseKey() {
        String falseKey = "7";
        assertNotEquals( PLAIN_TEXT, cipher.decrypt( CIPHER_TEXT, falseKey ) );
    }

    @Test
    void testDecryptNonAlphabetText() {
        String cipherText = CIPHER_TEXT.toLowerCase();
        assertThrows( IllegalArgumentException.class, () -> cipher.decrypt( cipherText, KEY ) );
    }

    @Test
    void testDecryptAnotherText() {
        String anotherCipherText = "ASDASD";
        assertNotEquals( PLAIN_TEXT, cipher.decrypt( anotherCipherText, KEY ) );
    }
}
