package year5.cryptography.exceptions;

import lombok.Getter;

@Getter
public class IllegalKeyException extends IllegalArgumentException {
    private String key;

    public IllegalKeyException( String message, String key ) {
        super( message );
        this.key = key;
    }

    public IllegalKeyException( String message ) {
        super( message );
    }
}
