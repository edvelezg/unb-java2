package algorithms.sorting;

import java.util.Arrays;

public class Quicksort2 {
	
	public static void qs(int[] a)
	{
		qs(a, 0, a.length-1);
	}
	
	public static void qs(int[] a, int l, int r) {
		
		if (l >= r) return;
		
		int idx = partition(a, l, r);
//		int[] cor = Arrays.copyOfRange(a, l, idx);
//		int[] ccor = Arrays.copyOfRange(a, idx, r+1);
//		System.out.println(Arrays.toString(cor) + "\t" + Arrays.toString(ccor));

		qs(a, l, idx-1);
		qs(a, idx, r);
	}
	
	public static int partition(int[] a, int l, int r) {
		int p = a[(l+r)/2];
		while (l <= r) {
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

	public static void main(String[] args) {
        int[] array = new int[] { 5, 1, 4, 7, 9, 2, 8, 3, 6 };
        Quicksort2.qs(array);
        
        System.out.println(Arrays.toString(array));
        
        String num = "Z";
        Integer num2 = Integer.parseInt(num);
        System.out.println(num2);
        num.equals("Z");
        
	}
}
