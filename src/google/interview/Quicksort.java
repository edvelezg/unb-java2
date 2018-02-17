package google.interview;

import java.util.Arrays;

public class Quicksort {
	public static void Qsort(int[] A) {
		Qsort(A, 0, A.length - 1);
	}

	public static void Qsort(int[] A, int lft, int rgt) {
		if (lft >= rgt)
			return;

		int idx = partition(A, lft, rgt);
		Qsort(A, lft, idx - 1);
		Qsort(A, idx, rgt);

	}

	private static int partition(int[] a, int lft, int rgt) {
		int pivot = a[(lft + rgt) / 2];

		while (lft <= rgt) {
			while (a[lft] < pivot) {
				++lft;
			}

			while (a[rgt] > pivot) {
				--rgt;
			}

			if (lft <= rgt) {
				swap(a, lft, rgt);
				++lft;
				--rgt;
			}
		}

		return lft;
	}

	private static void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	public static void main(String[] args) {

		int[] array = { 7, 2, 1, 6, 8, 5, 3, 4 };
		Qsort(array);
		System.out.println(Arrays.toString(array));

	}
}
