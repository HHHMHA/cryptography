package year5.cryptography.algorithms;

import org.junit.jupiter.api.Test;
import year5.cryptography.exceptions.IllegalKeyException;

import static org.junit.jupiter.api.Assertions.*;

class AutoKeyCipherTest {
    public static final String KEY = "people";
    public static final String PLAIN_TEXT = "sharingiscaring";
    public static final String CIPHER_TEXT = "HLOGTRYPSTIEOVY";
    public final Cipher cipher = new AutoKeyCipher();

    @Test
    void testDecrypt() {
        assertEquals( PLAIN_TEXT, cipher.decrypt( CIPHER_TEXT, KEY ) );
    }
}