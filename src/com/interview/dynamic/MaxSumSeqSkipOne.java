package com.interview.dynamic;

import java.util.Arrays;

public class MaxSumSeqSkipOne {

	public MaxSumSeqSkipOne() {
	}
	
	public static void main(String[] args) {
		int[] a = new int[] {-1, 9, -1, -3, 4, 5};
		System.out.println(maxSumSeqSkipOne(a));
	}

	private static int maxSumSeqSkipOne(int[] a) {
		int[] T = new int[a.length + 2];

		for (int i = a.length-1; i >= 0; --i) {
			T[i] = a[i] + Math.max(T[i+1], T[i+2]);
			System.out.println(Arrays.toString(T));
		}
		return Math.max(T[0], T[1]);
	}

}
