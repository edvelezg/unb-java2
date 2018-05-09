package other;

import java.util.Arrays;
import java.util.HashSet;

public class RowsNColsOfZero {

//	int[][] mtx = {
//			   { 1, 1, 1, 1, 1, 1 },
//			   { 1, 1, 0, 1, 1, 1 },
//			   { 1, 1, 1, 1, 1, 1 },
//			   { 1, 1, 1, 1, 0, 1 },
//			   { 1, 1, 1, 1, 1, 1 },
//			   { 1, 1, 1, 1, 1, 1 }
//			};

	public RowsNColsOfZero() {
		

	}
	
	public static void main(String[] args) {
		int[][] mtx = {
				   { 1, 1, 1, 1, 1, 1 },
				   { 1, 1, 0, 1, 1, 1 },
				   { 1, 1, 1, 1, 1, 1 },
				   { 1, 1, 1, 1, 0, 1 },
				   { 1, 1, 1, 1, 1, 1 },
				   { 1, 1, 1, 1, 1, 1 }
				};

		for (int i = 0; i < mtx.length; i++) {
			System.out.println(Arrays.toString(mtx[i]));
		}

		System.out.println();
		int[] rows = new int[mtx.length];
		int[] cols = new int [mtx[0].length];
		for (int i = 0; i < mtx.length; i++) {
			for (int j = 0; j < mtx[0].length; j++) {
				if (mtx[i][j] == 0) {
					rows[i] = 1;
					cols[j] = 1;
				}
			}
		}
		
//		int[][] mtx_cp = new int[mtx.length][mtx[0].length];
		for (int i = 0; i < mtx.length; ++i) {
			for (int j = 0; j < mtx[0].length; j++) {
				if (rows[i] == 1 || cols[j] == 1) {
					mtx[i][j] = 0;
				}
			}
		}
		
		for (int i = 0; i < mtx.length; i++) {
			System.out.println(Arrays.toString(mtx[i]));
		}

	}
}
