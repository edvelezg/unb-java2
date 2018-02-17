package algorithms.sorting;

import java.util.Arrays;

public class Quicksort2 {
	private static int count = 0;
	
	public static void qs(int[] a) {
		qs(a, 0, a.length-1);
	}
	
	public static int part(int[] a, int l, int r) {
		int[] ca = Arrays.copyOfRange(a, l, r+1);
		System.out.println(Arrays.toString(ca));

		int p = a[(l+r)/2];
		while (l < r) {
			while (a[l] < p) {
				l++;
			}
			while (a[r] > p) {
				r--;
			}
			if (l <= r) {
				swap(a, l, r);
//				System.out.println(String.format("l%d, r%d, p%d", l, r, p));
				l++;
				r--;
			}
		}
//		System.out.println(l);
		return l;
	}
	
	private static void swap(int[] a, int l, int r) {
		int t = a[l];
		a[l] = a[r];
		a[r] = t;
	}

	public static void qs(int[] a, int l, int r) {
		if (l >= r || count > 90) {
			int[] ca = Arrays.copyOfRange(a, l, r+1);
			System.out.println(Arrays.toString(ca));
			
			return;
		}
		count+=1;
		
		int idx = part(a, l, r);
		qs(a, l, idx-1);
		qs(a, idx, r);
	}
	
	public static void main(String[] args) {
        int[] array = new int[] { 5, 1, 4, 7, 9, 2, 8, 3, 6};
        qs(array);
        System.out.println(Arrays.toString(array));
	}

}
