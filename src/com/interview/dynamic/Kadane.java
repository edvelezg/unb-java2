package com.interview.dynamic;

public class Kadane {
	
	public static void printMtx() {
		
	}
	
	public static void main(String[] args) {
		String str1 = "team";
		String str2 = "tea";
	
		int[][] T = new int[str1.length()+1][str2.length()+1];
		
		for (int i = 0; i < T.length; i++) {
			T[i][0] = i;
		}

		for (int j = 0; j < T.length; j++) {
			T[0][j] = j;
		}

		for (int i = 1; i < str1.length(); i++) {
			for (int j = 1; j < str2.length(); j++) {
				if (str1.charAt(i) == str2.charAt(j)) {
					T[i][j] = T[i-1][j-1];
				} else {
					T[i][j] = Math.min(Math.min(T[i-1][j-1], T[i-1][j]), T[i][j-1]) + 1; 
				}
			}
		}
	}
	
	public static int min(int a, int b, int c) {
		int l = Math.min(a, b);
		return Math.min(l, c); 
	}

	public static int maxContSum(int a[]) {
		int size = a.length;
		int am = Integer.MIN_VALUE, rm = 0;

		for (int i = 0; i < size; i++) {
			rm = rm + a[i];
			if (am < rm)
				am = rm;
			if (rm < 0)
				rm = 0;
		}
		return am;
	}
}
