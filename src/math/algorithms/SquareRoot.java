package math.algorithms;

import java.math.BigInteger;

/**
 * @author egutarra
 *
 */
public class SquareRoot {

    private static BigInteger two = new BigInteger("2");

    public static BigInteger sqrt(BigInteger n) {
        if (n.compareTo(new BigInteger("2")) == 0) {
            System.out.println("Not an integer");
            return null;
        }
        if (n.compareTo(BigInteger.ONE) == 0) {
            return BigInteger.ONE;
        }
        if (n.compareTo(BigInteger.ZERO) <= 0) {
            return BigInteger.ZERO;
        }

        BigInteger a = BigInteger.ONE;
        BigInteger b = n.divide(two);

        while (b.compareTo(a) >= 0) {
            BigInteger mid = a.add(b).divide(two); // (a+b) >> 1

            if (mid.multiply(mid).compareTo(n) > 0)
                b = mid.subtract(BigInteger.ONE);
            else
                a = mid.add(BigInteger.ONE);
        }
        return a.subtract(BigInteger.ONE);
    }

    @SuppressWarnings("unused")
    private static void printBin(BigInteger n) {
        System.out.println(String.format("%32s", Integer.toBinaryString(n.intValue())).replace(' ', '0'));
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

    @SuppressWarnings("unused")
    private static void bigIntPrimeFactors(String number) throws Exception {
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
        // String number = "600851475143";
        // bigIntPrimeFactors(number);
        // System.out.println();
        //
        // // Only works for integers less than 2^31-1 = 2147483647
        // primeFactors(Integer.MAX_VALUE - 1);
        // System.out.println();
        // primeFactors(Integer.MAX_VALUE);
        // System.out.println();
        // // Max is 2^31-1
        // System.out.println(Integer.MAX_VALUE);
        //
        // // Min is -(2^31)
        // System.out.println(Integer.MIN_VALUE);

        String number2 = "144";
        BigInteger sqrt = null;
        try {
            sqrt = sqrt(new BigInteger(number2));
//            printBin(sqrt);
            System.out.println(sqrt);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}