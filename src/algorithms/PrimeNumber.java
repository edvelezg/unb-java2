/**
 * 
 */
package algorithms;

import java.math.BigInteger;

/**
 * @author egutarra
 *
 */
public class PrimeNumber {

    public static BigInteger sqrt(BigInteger n) {
        BigInteger a = BigInteger.ONE;
        BigInteger b = n.shiftRight(1).add(new BigInteger("2")); // (n >> 1) + 2
                                                                 // (ensure 0
                                                                 // doesn't show
                                                                 // up)
        while (b.compareTo(a) >= 0) {
            BigInteger mid = a.add(b).shiftRight(1); // (a+b) >> 1
            if (mid.multiply(mid).compareTo(n) > 0)
                b = mid.subtract(BigInteger.ONE);
            else
                a = mid.add(BigInteger.ONE);
        }
        return a.subtract(BigInteger.ONE);
    }

    // A function to print all prime factors
    // of a given number n
    public static void primeFactors(int n) {
        // Print the number of 2s that divide n
        while (n % 2 == 0) {
            System.out.print(2 + " ");
            n /= 2;
        }

        // n must be odd at this point. So we can
        // skip one element (Note i = i +2)
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            // While i divides n, print i and divide n
            while (n % i == 0) {
                System.out.print(i + " ");
                n /= i;
            }
        }

        // This condition is to handle the case when
        // n is a prime number greater than 2
        if (n > 2)
            System.out.print(n);
    }

    private static void bigIntPrimeFactors(String number) {
        BigInteger num = new BigInteger(number);
        BigInteger sqNum = sqrt(num);
        for (int i = 2; i < sqNum.intValue(); i++) {
            BigInteger fct = new BigInteger(String.valueOf(i));
            BigInteger res = num.mod(fct);
            if (res.intValue() == 0) {
                num = num.divide(fct);
                System.out.print(fct + " ");
            }
        }
    }

    public static void main(String[] args) {
        String number = "600851475143";
        bigIntPrimeFactors(number);
        System.out.println();

        // Only works for integers less than 2^31-1 = 2147483647
        primeFactors(Integer.MAX_VALUE - 1);
        System.out.println();
        primeFactors(Integer.MAX_VALUE);
        System.out.println();
        // Max is 2^31-1
        System.out.println(Integer.MAX_VALUE);

        // Min is -(2^31)
        System.out.println(Integer.MIN_VALUE);
    }
}