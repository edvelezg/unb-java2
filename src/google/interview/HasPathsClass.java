package google.interview;

public class HasPathsClass {

	private static int[][] mtrx = {
			{ 0, 1, 0, 0, 0 },
			{ 1, 1, 0, 1, 0 },
			{ 1, 0, 1, 1, 1 },
			{ 1, 1, 1, 0, 1 },
			{ 0, 0, 0, 0, 1 }
			};

	private static int[][] visited = {
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 }
            };

    public static boolean hasPath(int[][] mtx, int row, int numrows, int col, int numcols) {
        System.out.println("hasPath(r=" + row + ", c=" + col + ", n=" + numcols + ")");
        if (row > numrows - 1 || col > numcols - 1)
            return false;

        if (mtx[row][col] == 0)
            return false;

        if (row == numrows - 1)
            return (mtrx[row][col] == 1);

        if (visited[row][col] == 1)
            return false;

        boolean hasP = (mtx[row][col] == 1);
        visited[row][col] = 1;
        return hasP && (hasPath(mtx, row, numrows, col + 1, numcols) || hasPath(mtx, row + 1, numrows, col, numcols)
                || hasPath(mtx, row, numrows, col - 1, numcols) || hasPath(mtx, row - 1 , numrows, col, numcols));
    }

    public static void main(String[] args) {

        int[][] mtx = HasPathsClass.mtrx;
        int row = 0;
        boolean hasP = false;
        for (int c = 0; c < mtx[0].length; c++) {
            hasP = hasPath(mtx, row, mtx.length, c, mtx[0].length);
            System.out.println(hasP);
            if (hasP) {
//                break;
            }
        }
    }
}
