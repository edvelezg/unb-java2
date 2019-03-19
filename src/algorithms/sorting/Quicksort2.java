package algorithms.sorting;

import java.util.Arrays;

public class Quicksort2 {
	private static int count = 0;

	public static void qs(int[] a) {
		qs(a, 0, a.length - 1);
	}

	public static int part(int[] a, int l, int r) {

		int p = a[(l + r) / 2];
		int[] ca = Arrays.copyOfRange(a, l, r + 1);
		System.out.println(Arrays.toString(ca) + " " + "p: " + p);

		while (l < r) {
			while (a[l] < p) {
				l++;
			}
			while (a[r] > p) {
				r--;
			}
			if (l <= r) {
				swap(a, l, r);
				l++;
				r--;
			}
		}
		return l;
	}

	private static void swap(int[] a, int l, int r) {
		int t = a[l];
		a[l] = a[r];
		a[r] = t;
	}

	public static void qs(int[] a, int l, int r) {

		if (l >= r) {
			int[] ca = Arrays.copyOfRange(a, l, l + 1);
			// System.out.println(Arrays.toString(ca));
			return;
		}

		int idx = part(a, l, r);
		printLft(a, l, idx);
		qs(a, l, idx - 1);
		printRgt(a, r, idx);
		qs(a, idx, r);
	}

	private static void printLft(int[] a, int l, int idx) {
		// System.out.print(String.format("qs(%s", Arrays.toString(a)));
		System.out.print(String.format("qs(a"));
		System.out.println(String.format(", %d, %d) | %s ", l, idx - 1, Arrays.toString(Arrays.copyOfRange(a, l, idx))));
	}

	private static void printRgt(int[] a, int r, int idx) {
		// System.out.print(String.format("qs(%s", Arrays.toString(a)));
		System.out.print(String.format("qs(a"));
		System.out.println(String.format(", %d, %d) | %s", idx, r, Arrays.toString(Arrays.copyOfRange(a, idx, r + 1))));
	}


	public static void main(String[] args) {
		int[] array = new int[] { 5, 1, 4, 7, 9, 2, 8, 3, 6 };
		qs(array);
		System.out.println(Arrays.toString(array));
	}

}
