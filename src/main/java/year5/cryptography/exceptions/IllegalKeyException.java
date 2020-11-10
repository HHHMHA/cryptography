package year5.cryptography.exceptions;

import lombok.Getter;

@Getter
public class IllegalKeyException extends IllegalArgumentException {
    private int key;
    private int alphabetSize;

    public IllegalKeyException( String message, int key, int alphabetSize ) {
        super( message );
        this.key = key;
        this.alphabetSize = alphabetSize;
    }
}
