package year5.cryptography.algorithms;

public interface Cipher {
    String encrypt( String plainText, String key );
    String decrypt( String cipherText, String key );

    String getPlainText();
    String getCipherText();
    String getKey();
}
