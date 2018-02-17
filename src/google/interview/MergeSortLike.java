package google.interview;

import java.util.Arrays;

public class MergeSortLike {

	public static void main(String[] args) {
		int[] arr1 = new int[] { 1, 3, 6, 0, 0, 0};
		int[] arr2 = new int[] { 4, 5, 0, 0 };
		
		merge(arr1, 3, arr2, 2);
		System.out.println(Arrays.toString(arr1));
	}

	public static void merge(int[] arr1, int m, int[] arr2, int n) {
		assert arr1.length > m + n;
		int[] opt = new int[m + n];
		int i1 = 0; // for array 1
		int i2 = 0; // for array 2
		for (int i = 0; i < m + n; ++i) {
			if (i2 >= n || arr1[i1] <= arr2[i2]) {
				opt[i] = arr1[i1];// arr1 or arr2’s elem
				i1++;
			} else {
				opt[i] = arr2[i2];// arr1 or arr2’s elem
				i2++;

			}
		}
//		arr1 = opt;
		for (int i = 0; i < opt.length; ++i)
			arr1[i] = opt[i];

	}
}
