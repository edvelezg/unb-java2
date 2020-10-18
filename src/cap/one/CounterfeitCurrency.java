package cap.one;


import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

class CFResult {

    /*
     * Complete the 'countCounterfeit' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING_ARRAY serialNumber as parameter.
     */

    private static final HashSet<Integer> validSet = new HashSet<>(Arrays.asList(10, 20, 50, 100, 200, 500, 1000));

    private static boolean has10To12Chars(String s) {
        return 9 < s.length() && 13 > s.length();
    }

    private static boolean hasValidYear(String s) {
        String yearStr = s.substring(3, 7);
        int year = Integer.parseInt(yearStr);

        if (year > 1899 && year < 2020) {
            return true;
        }
        return false;
    }


    private static boolean first3AreDistinctUpper(String s) {
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            char c = s.charAt(i);
            if (set.contains(c)) return false;
            if (Character.isUpperCase(c)) {
                set.add(c);
                continue;
            }
            else
                return false;
        }
        return true;
    }

    private static boolean lastIsUpper(String s) {
        char c = s.charAt(s.length()-1);
        if (Character.isUpperCase(c)) return true;
        return false;
    }

    private static int getCurrency(String s) {
        String currencyStr = s.substring(7, s.length()-1);
        int currency = 0;
        try {
            currency = Integer.parseInt(currencyStr);
            if (validSet.contains(currency)) return currency;
        } catch (NumberFormatException e) {
        }
        return 0;
    }

    public static int countCounterfeit(List<String> serialNumber) {

        int sum = 0;
        for (String s : serialNumber) {
            try {
                if (!has10To12Chars(s)) continue;
                if (!first3AreDistinctUpper(s)) continue;
                if (!hasValidYear(s)) continue;
                if (!lastIsUpper(s)) continue;
                int currency = getCurrency(s);
                sum += currency;
                System.out.printf("%s => %d%n", s, currency);
            } catch (Exception e) {
                continue;
            }
        }


        return sum;
    }

}

public class CounterfeitCurrency {
    public static void main(String[] args) throws IOException {
//        InputStreamReader in = new InputStreamReader(System.in);
        String test = "8\n" +
                "AVG190420T\n" +
                "RTF20001000Z\n" +
                "QWER201850G\n" +
                "AFA199620E\n" +
                "ERT1947200T\n" +
                "RTY20202004\n" +
                "DRV1984500Y\n" +
                "ETB2010400G\n";
        Reader inputString = new StringReader(test);
        BufferedReader bufferedReader = new BufferedReader(inputString);
//        String output_path = System.getenv("OUTPUT_PATH");
        String output_path = "C:\\Users\\Slark\\Documents\\Java\\unb-java2\\src\\cap\\one\\output.txt";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(output_path));

        int serialNumberCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> serialNumber = IntStream.range(0, serialNumberCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .collect(toList());

        int result = CFResult.countCounterfeit(serialNumber);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
