package amazon.interview;

import java.io.*;

import static amazon.interview.Result.findSmallestDivisor;

class Result {

    /*
     * Complete the 'findSmallestDivisor' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. STRING t
     */
    public static String gcdOfStrings(String small, String large) {
        System.out.println("gcd " + small + " " + large);
        if (large.length() % small.length() != 0) return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i*small.length() < large.length(); i++) {
            sb.append(small);
            if (sb.toString().equals(large)) {
                return small;
            }
        }
        return "";
    }

    public static String findSmallestDivisor(String s, String t) {

        String smallestDivisor = "";
        if (s.length() > t.length()) {
            smallestDivisor = gcdOfStrings(t, s);
        } else {
            smallestDivisor = gcdOfStrings(s, t);
        }

        // look for smaller divisors if s divides t
        for (int i = 1; i <= s.length()/2; i++) {
            String substring = s.substring(0, i);
            // if substring divides s:
            String div = gcdOfStrings(substring, s);
            if (div.length() > 0) {
                smallestDivisor = substring;
            }
        }

        // Write your code here
        return smallestDivisor;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {

        String result = findSmallestDivisor("bcdbcd", "bcdbcdbcd");
        System.out.println(result);
    }
}
