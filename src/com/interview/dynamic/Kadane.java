package com.interview.dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Sequence {
	ArrayList<Integer> numList = new ArrayList<>();
	int start_idx;
	int ending_idx;
	
	public Sequence() {

	}
	
	@Override
	public String toString() {
		return String.format("\t%-5d, %-5d, %s\n", start_idx, ending_idx, Arrays.toString(numList.toArray()) );
	}
}

public class Kadane {
	
	public static void main(String[] args) {
//		int maxSum = maxContSum(new int [] { -7, -6, 1, 2, -4, 3 ,-2, 1, 2, 4});
//		int maxSum = maxContSum(new int [] { -7, -6, 1, 2, -4, 3 ,-2, 1 });
//		int maxSum = maxContSum(new int [] { 1, 2, -4, 3 ,-2, -1, 1, 2, -4, -1, 2, -1, 2 });
		int maxSum = maxContSum(new int [] { 1, 2, 1, -4, 2, 2, -5});
		System.out.println("maxSum:" + maxSum);
	}
	
	public static int min(int a, int b, int c) {
		int l = Math.min(a, b);
		return Math.min(l, c); 
	}

	public static int maxContSum(int a[]) {
		int size = a.length;
		int[] rm_array = new int[size]; // array to collect all relative maximums
		int am = Integer.MIN_VALUE, rm = 0;

		for (int i = 0; i < size; i++) {
			rm += a[i];

			if (rm < 0)
				rm = 0;

			if (rm > am)
				am = rm;

			rm_array[i] = rm;
		}
		findMaxSums(rm_array, am, a);
		return am;
	}
	
	public static int findMaxSums(int rm_array[], int am, int[] a) {
		ArrayList<Sequence> seqs = new ArrayList<>();
		for (int i = rm_array.length - 1; i >= 0; i--) {
			if (rm_array[i] == am) {
				Sequence sequence = new Sequence();
				int sum = am;
				int j;
				for (j = i; j >= 0 && sum > 0; --j) {
					sequence.ending_idx = i;
					sum -= a[j];
					sequence.numList.add(a[j]);
				}
				sequence.start_idx = j + 1;
				Collections.reverse(sequence.numList);
				System.out.println(Arrays.toString(sequence.numList.toArray())); 
				seqs.add(sequence);
//				i = j; // This will stop sequences from showing up  
			}
		}
		Collections.reverse(seqs);
		System.out.println(String.format("total seqs: %d", seqs.size()));
		System.out.println(seqs);
		return 0;
	}
}
