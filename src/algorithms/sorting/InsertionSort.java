package algorithms.sorting;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {
        
        int[] a = new int[] {5, 3, 2, 1, 4, 8, 7, 6};
        System.out.println(Arrays.toString(a));
        
        for (int i = 1; i < a.length; i++) {
            int j = i;
            while (j > 0 && a[j-1] > a[j]) {
                System.out.println(j);
                int temp = a[j];
                a[j] = a[j-1];
                a[j-1] = temp; 
                j = j-1;
            }
//            System.out.println(Arrays.toString(a));

        }
        
        System.out.println(Arrays.toString(a));
    }
}
