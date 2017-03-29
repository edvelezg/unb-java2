package other;

public class Recursion {

    public static void main(String[] args) {
        Recursion r = new Recursion();
        int[] array = r.allFactorials(5);
        for (int i = 0; i < array.length; i++) {
            if (i < array.length - 1) {
                System.out.print(array[i] + " ");
            } else {
                System.out.print(array[i]);
            }
        }
        System.out.println();

        int[] arr = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 14, 20, 21 };
        int pos = r.bin_srch(0, arr.length - 1, 0, arr);
        if (pos >= 0) {
            System.out.println(arr[pos]);
        } else {
            System.out.println("Not found!");
        }

        pos = r.bin_srch_iter(0, arr.length - 1, -1, arr);
        if (pos >= 0) {
            System.out.println(arr[pos]);
        } else {
            System.out.println("Not found!");
        }

        r.rotateStr("team".toCharArray(), 0);
    }

    int[] allFactorials(int n) { /* Wrapper function */

        int[] results = new int[n < 0 ? 1 : n];

        // doAllFactorials(n, results, 0);
        fact(n, results, 0);
        return results;
    }

    int doAllFactorials(int n, int[] results, int level) {
        if (n > 1) { /* Recursive case */
            results[level] = n * doAllFactorials(n - 1, results, level + 1);
            return results[level];
        } else { /* Base case */
            results[level] = 1;
            return 1;
        }
    }

    int fact(int n, int[] results, int lvl) {
        if (n == 1) {
            results[lvl] = 1;
            return 1;
        } else {
            results[lvl] = n * fact(n - 1, results, lvl + 1);
            return results[lvl];
        }

    }

    public int bin_srch(int start, int end, int num, int[] array) {
        int mid = (start + end) / 2;
        if (array[mid] == num) {
            return mid;
        }
        if (end <= start) {
            return -1;
        } else if (array[mid] < num) {
            return bin_srch(mid + 1, end, num, array);
        } else {
            return bin_srch(start, mid - 1, num, array);
        }

    }

    public int bin_srch_iter(int start, int end, int num, int[] array) {

        while (start <= end) {
            int mid = (start + end) / 2;
            if (array[mid] == num) {
                return mid;
            } else if (array[mid] < num) {
                start = mid + 1;
            } else { // array[mid] > num
                end = mid - 1;
            }

        }
        return -1;
    }

    public char[] rotateStr(char[] str, int n) {
        if (n == str.length - 1) {
            System.out.println(str);
            char tmp = str[n];
            str[n] = str[n - 1];
            str[n - 1] = tmp;
            return str;
        } else {
            str = rotateStr(str, n + 1);
            System.out.println(str);
            if (n > 0) {
                char tmp = str[n];
                str[n] = str[n - 1];
                str[n - 1] = tmp;
            }
            return str;
        }
    }

}
