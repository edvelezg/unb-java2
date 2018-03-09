package algorithms.sorting;

import java.util.Arrays;

public class Quicksort2 {
	private static int count = 0;

	private static String arrayToString(int[] array, int left, int right) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			if (i > 0)
				buf.append(", ");

			if (i == left && i == right) {
				buf.append(String.format("%4s", String.format(">%d<", array[i])));
			} else if (i == left) {
				buf.append(String.format("%4s", String.format(">%d", array[i])));
			} else if (i == right) {
				buf.append(String.format("%4s", String.format("%d<", array[i])));
			} else {
				buf.append(String.format("%4d", array[i]));
			}
		}
		return buf.toString();

	}

	public static void qs(int[] a) {
		qs(a, 0, a.length - 1);
	}

	public static int part(int[] a, int l, int r) {
		int p = a[(l + r) / 2];

		int[] ca = Arrays.copyOfRange(a, l, r + 1);
		System.out.println(String.format("%57s \t p: %d", arrayToString(ca, l, r), p));

		while (l < r) {
			while (a[l] < p) {
				l++;
				System.out.println(String.format("%57s \t p: %d", arrayToString(ca, l, r), p));
			}
			while (a[r] > p) {
				r--;
				System.out.println(String.format("%57s \t p: %d", arrayToString(ca, l, r), p));
			}
			if (l <= r) {
				swap(a, l, r);
				l++;
				r--;
				System.out.println(String.format("%57s \t p: %d", arrayToString(ca, l, r), p));
			}
		}
		System.out.println(String.format("Split at: %d", l));
		return l;
	}

	private static void swap(int[] a, int l, int r) {
		System.out.println("Swapping(l=" + a[l] + ", r=" + a[r] + ")");
		int t = a[l];
		a[l] = a[r];
		a[r] = t;
	}

	public static void qs(int[] a, int l, int r) {
		if (l >= r || count > 90) {
			int[] ca = Arrays.copyOfRange(a, l, r + 1);
			System.out.println(String.format("%50s", arrayToString(ca, l, r)));
			return;
		}
		count += 1;

		int idx = part(a, l, r);
		qs(a, l, idx - 1);
		qs(a, idx, r);
	}

	public static void main(String[] args) {
		int[] array = new int[] { 5, 1, 4, 7, 9, 2, 8, 3, 6 };
		qs(array);
		System.out.println(Arrays.toString(array));
	}

}
