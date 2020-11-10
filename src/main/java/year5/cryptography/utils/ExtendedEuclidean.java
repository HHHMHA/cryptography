package year5.cryptography.utils;

import lombok.Getter;

/**
 * Performs and store results of the extended euclidean algorithm
 */
@Getter
public class ExtendedEuclidean {
    private int gcd, x, y;

    public ExtendedEuclidean( int a, int b ) {
        run( a, b );
    }

    /**
     * Calculates the gcd and the coefficients and sets the fields.
     *
     * Source: https://www.sanfoundry.com/java-program-extended-euclid-algorithm/
     * @param a The First Number
     * @param b The Second Number
     */
    private void run( int a, int b ) {
        int x = 0, y = 1, lastX = 1, lastY = 0, temp;
        while (b != 0) {
            int q = a / b;
            int r = a % b;

            a = b;
            b = r;

            temp = x;
            x = lastX - q * x;
            lastX = temp;

            temp = y;
            y = lastY - q * y;
            lastY = temp;
        }
        this.x = x;
        this.y = y;
        this.gcd = a;
    }

    /**
     * @return True if the numbers provided in the constructor are relatively prime, False otherwise.
     */
    public boolean AreRelativelyPrime() {
        return gcd == 1;
    }
}
