package cap.one;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class CSARes {

    /*
     * Complete the 'moves' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */
    private static boolean isEven(int num) {
        return num % 2 == 0;
    }
    private static boolean isOdd(int num) {
        return !isEven(num);
    }

    public static int moves(List<Integer> arr) {

        int[] ints = arr.stream().mapToInt(i -> i).toArray();
        int moves = 0;
        for (int i = 0; i < ints.length; i++) {
            if (isOdd(ints[i])) {
                for (int j = ints.length-1; j >= i; j--) {
                    if (isEven(ints[j])) {
                        int tmp = ints[i];
                        ints[i] = ints[j];
                        ints[j] = tmp;
                        moves++;
                        break;
                    }
                }
            }
        }
        return moves;
    }

}

public class CustomSortArrays {
    public static void main(String[] args) throws IOException {
//        InputStreamReader in = new InputStreamReader(System.in);
        String test = "4\n" +
                "13\n" +
                "10\n" +
                "21\n" +
                "20\n";
        Reader inputString = new StringReader(test);
        BufferedReader bufferedReader = new BufferedReader(inputString);
//        String output_path = System.getenv("OUTPUT_PATH");
        String output_path = "C:\\Users\\Slark\\Documents\\Java\\unb-java2\\src\\cap\\one\\output.txt";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(output_path));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = IntStream.range(0, arrCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int result = CSARes.moves(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
