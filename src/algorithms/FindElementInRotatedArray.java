package algorithms;

import java.util.Scanner;
class FindElementInRotatedArray {

    private static Scanner keyboard;

    public FindElementInRotatedArray() {
    }

    public static void main(String args[]) {
        // int[] a = new int[]{1, 2, 3, 4, 5, 6, 0};
        // int[] a = new int[] { 4, 5, 6, 0, 1, 2, 3 };
        int[] a = new int[] { 1, 2, 3, 4, 5, 6, 0};

        int myint = 0;
        while (myint != -1) {
            keyboard = new Scanner(System.in); 
            System.out.println("Enter an integer: "); 
            myint = keyboard.nextInt();
            
            int pos = findElement(a, a.length, myint); 
            // int pos = findElement(a, a.length, 5);
            System.out.println("pos: " + pos);
        }

    }

    public static int findElement(int[] a, int n, int k) {

        int h = n - 1;
        int l = 0;

        // if (a[l] <= a[h]) return -1;

        while (l <= h) {
            int m = (h + l) / 2;

            if (k == a[m]) return m;
            else if (a[m] <= a[h]) {
                if (a[m] <= k && k <= a[h]) {
                    l = m + 1;
                } else {
                    h = m - 1;
                }
            } else { // (a[l] <= a[m])
                if (a[l] <= k && k <= a[m]) {
                    h = m - 1;
                } else {
                    l = m + 1;
                }
            }
        }

        return -1;
    }

    public static int findElement2(int[] a, int n, int k) {

        int h = n - 1;
        int l = 0;

        if (a[l] <= a[h]) return -1;

        while (l <= h) {
            int m = (h + l) / 2;
            int nxt = (m + 1) % n;
            int prv = (m - 1) % n;

            // found rotation point
            if (a[prv] >= a[m] && a[m] <= a[nxt]) {
                if (a[nxt] <= k && k <= a[n - 1]) {
                    l = nxt;
                    h = n - 1;
                    while (l <= h) {
                        m = (h + l) / 2;
                        if (a[m] == k) return m;
                        else if (a[m] <= a[h]) l = m + 1;
                        else h = m - 1;
                    }
                    return -1;
                } else {
                    l = 0;
                    h = prv;
                    while (l <= h) {
                        m = (h + l) / 2;
                        if (a[m] == k) return m;
                        else if (a[m] <= a[h]) l = m + 1;
                        else h = m - 1;
                    }
                    return -1;
                }
            }
            if (a[m] >= a[h]) l = m + 1;
            else h = m - 1;
        }

        return -1;
    }

}
