package google.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountIslands {

    static class Island {
        class Pair {
            Integer row, col;

            public Pair(Integer row, Integer col) {
                this.row = row;
                this.col = col;
            }

            @Override
            public String toString() {
                return String.format("(%d, %d)", row, col);
            }
        }

        List<Pair> pairList;

        public Island() {
            this.pairList = new ArrayList<>();
        }

        public void addToList(int r, int c) {
            this.pairList.add(new Pair(r,c));
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("{");
            for (Pair pair : pairList) {
                stringBuilder.append(pair);
            }
            stringBuilder.append("}");
            return stringBuilder.toString();
        }
    }

    static List<Island> islandList = new ArrayList<>();
    static int[][] mtx = new int[][] {
        {0, 1, 0, 0, 0},
        {0, 1, 1, 0, 1},
        {1, 0, 1, 0, 0},
        {0, 0, 0, 0, 1},
        {0, 1, 0, 0, 1}
    };

    static int[][] wasHere = new int[mtx.length][mtx[0].length];

    public static void main(String[] args) {

        int numIslands = 0;
        int numRows = mtx.length;
        int numCols = mtx[0].length;
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numCols; j++) {
                if (mtx[i][j] == 1) {
                    if (wasHere[i][j] == 0) {
                        Island island = new CountIslands.Island();
                        numIslands += 1;
                        CountIslands.findIslands(i,j,numRows,numCols,island);
                        islandList.add(island);
                    }
                }
            }
        }

        System.out.println(numIslands);
        System.out.println(islandList);
        printMtx(wasHere);

    }

    private static void printMtx(int[][] mtx) {
        Arrays.stream(mtx).forEach(ts -> System.out.println(Arrays.toString(ts)));
    }

    public static void findIslands(int row, int col, int numRows, int numCols, Island island) {

        if (wasHere[row][col] == 1 || mtx[row][col] == 0) {
            return;
        }

        wasHere[row][col] = 1;
        island.addToList(row,col);

        if (row > 0 + 1) {
            if (mtx[row][col] == 1) {
                findIslands(row-1, col, numRows, numCols, island);
            }
        }

        if (row < numRows - 1) {
            if (mtx[row][col] == 1) {
                findIslands(row+1, col, numRows, numCols, island);
            }
        }

        if (col > 0 + 1) {
            if (mtx[row][col] == 1) {
                findIslands(row, col-1, numRows, numCols, island);
            }
        }

        if (col < numCols - 1) {
            if (mtx[row][col] == 1) {
                findIslands(row, col+1, numRows, numCols, island);
            }
        }
    }
}
