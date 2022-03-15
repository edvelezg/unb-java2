package google.interview;

import java.util.Arrays;

public class CountIslands {

    static int[][] mtx = new int[][] {
        {0, 1, 0, 0, 0},
        {0, 1, 1, 0, 1},
        {1, 0, 1, 0, 0},
        {0, 0, 0, 0, 1},
        {0, 1, 0, 0, 1}
    };

    static boolean[][] wasHere = new boolean[mtx.length][mtx[0].length];

    public static void main(String[] args) {

        int numIslands = 0;
        int numRows = mtx.length;
        int numCols = mtx[0].length;
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numCols; j++) {
                if (mtx[i][j] == 1) {
                    if (wasHere[i][j] == false) {
                        numIslands += 1;
                        CountIslands.findIslands(i,j,numRows,numCols);
                    }
                }
            }
        }

        System.out.println(numIslands);
        printMtx(wasHere);

    }

    private static void printMtx(boolean[][] mtx) {
        Arrays.stream(mtx).forEach(ts -> System.out.println(Arrays.toString(ts)));
    }

    public static void findIslands(int row, int col, int numRows, int numCols) {

        if (wasHere[row][col] || mtx[row][col] == 0) {
            return;
        }

        if (row > 0 + 1) {
            if (mtx[row][col] == 1) {
                wasHere[row][col] = true;
                findIslands(row-1, col, numRows, numCols);
            }
        }

        if (row < numRows - 1) {
            if (mtx[row][col] == 1) {
                wasHere[row][col] = true;
                findIslands(row+1, col, numRows, numCols);
            }
        }

        if (col > 0 + 1) {
            if (mtx[row][col] == 1) {
                wasHere[row][col] = true;
                findIslands(row, col-1, numRows, numCols);
            }
        }

        if (col < numCols - 1) {
            if (mtx[row][col] == 1) {
                wasHere[row][col] = true;
                findIslands(row, col+1, numRows, numCols);
            }
        }
    }
}
