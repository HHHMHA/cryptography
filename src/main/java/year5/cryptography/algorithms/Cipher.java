package year5.cryptography.algorithms;

public interface Cipher {
    String encrypt( String plainText, int key );
    String decrypt( String cipherText, int key );

    String getPlainText();
    String getCipherText();
    int getKey();
}
