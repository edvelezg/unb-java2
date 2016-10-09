package algorithms.sorting;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] array = {2, 3, 5, 0, 1, 7};
        mergeSort(array, array.length);

        System.out.println(Arrays.toString(array));
//        return result.length() > 0 ? result.substring(0, result.length() - 1): "";
    }
    
    public static void mergeSort(int[] a, int n) {
        System.out.println(String.format("mergeSort(a=%s n=%d)", Arrays.toString(a), n));
        
        if (n == 1) {
            return;
        }

        int[] left = Arrays.copyOfRange(a, 0, n/2);
        int[] right = Arrays.copyOfRange(a, n/2, n);
        
        mergeSort(left, left.length);
        mergeSort(right, right.length);

        merge(a, left, right);
    }

    private static void merge(int[] a, int[] left, int[] right) {
        System.out.println(String.format("merge(a=%s left=%s right=%s)", Arrays.toString(a), Arrays.toString(left), Arrays.toString(right)));
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < a.length; i++) {
            if ((i1 == left.length) || (i2 < right.length && right[i2] < left[i1])) {
                a[i] = right[i2]; // take left
                i2++;
            } else {
                a[i] = left[i1]; // take right
                i1++;
            }
        }
    }
    
}
