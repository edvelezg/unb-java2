package data.structures;

import java.util.Arrays;

public class RotateMatrixInPlace {
	
	public static void rotate(char[][] mtx, int n) {
		// l is layer
		// f is first
		// e is end
		for (int l = 0; l < n / 2; ++l) {
			int f = l;
			int e = n - 1 - l;
			for (int i = f; i < e; ++i) {
				int o = i - f;
				
				// save top to temporary
				char tmp = mtx[f][i]; 
				
				// left -> top
				mtx[f][i] = mtx[e - o][f];

				// bottom -> left
				mtx[e - o][f] = mtx[e][e - o];

				// right -> bottom
				mtx[e][e - o] = mtx[i][e];

				// top -> right
				mtx[i][e] = tmp; // right <- saved top
			}
		}
	}

	public static void main(String[] args) {

		int n = 7;
		char[][] mtx = new char[n][n];
		for (int i = 0; i < n * n; i++) {
			mtx[i / n][i % n] = (char) ('A' + i);
		}
		printMtx(mtx);	
		rotate(mtx, n);
		printMtx(mtx);
		

	}

	private static void printMtx(char[][] mtx) {
		for (int i = 0; i < mtx.length; i++) {
			System.out.println(Arrays.toString(mtx[i]));
		}
		System.out.println();
	}

	public RotateMatrixInPlace() {
		// TODO Auto-generated constructor stub
	}

}
