package other;

import java.util.Arrays;

public class RotateMatrix {

	public static void main(String[] args) {

		int n = 5;
		char[][] mtx = createMtx(n);

		printMtx(mtx);
		rotateMatrix(n, mtx);

		System.out.println();
		printMtx(mtx);
		System.out.println();
		printDiagonals(mtx);
	}

	private static void printDiagonals(char[][] mtx) {
		goingNorthEast(mtx);
		System.out.println();
		goingNorthWest(mtx);
		
	}
	
	private static void goingNorthWest(char[][] mtx) {
		for (int i = 0; i < mtx.length; ++i) {
			for (int j = 0; j < i + 1; ++j) {
				System.out.print(mtx[i - j][mtx.length-1-j] + " ");
			}
			System.out.println();
		}
		for (int i = mtx.length-1; i > 0; --i) {
			for (int j = mtx.length - i, k = mtx.length - 1; j < mtx.length; ++j) {
				System.out.print(mtx[k--][mtx.length-1-j] + " ");
			}
			System.out.println();
		}
	}


	@SuppressWarnings("unused")
	private static void goingNorthEast(char[][] mtx) {
		for (int i = 0; i < mtx.length; ++i) {
			for (int j = 0; j < i + 1; ++j) {
				System.out.print(mtx[i - j][j] + " ");
			}
			System.out.println();
		}
		for (int i = mtx.length-1; i > 0; --i) {
			for (int j = mtx.length - i, k = mtx.length - 1; j < mtx.length; ++j) {
				System.out.print(mtx[k--][j] + " ");
			}
			System.out.println();
		}
	}

	private static void rotateMatrix(int n, char[][] mtx) {
		for (int l = 0; l < n - 2; ++l) {
			int s = l;
			int e = n - 1 - l;
			for (int k = s; k < e; k++) {
				char t = mtx[s][k];
				mtx[s][k] = mtx[k][e];
				mtx[k][e] = mtx[e][e - k];
				mtx[e][e - k] = mtx[e - k][s];
				mtx[e - k][s] = t;
			}
		}
	}

	private static char[][] createMtx(int n) {
		char[][] mtx = new char[n][n];
		for (int i = 0; i < n * n; i++) {
			mtx[i / n][i % n] = (char) ('A' + i);
		}
		return mtx;
	}

	private static void printMtx(char[][] mtx) {
		for (int i = 0; i < mtx.length; i++) {
			System.out.println(Arrays.toString(mtx[i]));
		}
	}

}
