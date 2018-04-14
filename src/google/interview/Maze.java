package google.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Cell<R, C> {
	private R row;
	private C col;
	public Cell(R x, C y) {
		this.row = x;
		this.col = y;
	}
	
	public R getX() {
		return row;
	}

	public C getY() {
		return col;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("(%d, %d)", row, col);
	}
}

public class Maze {
	
//	private static int[][] maze = {
//            { 0, 1, 0, 0, 0, 0 },
//            { 1, 1, 0, 1, 0, 1 },
//            { 1, 0, 0, 1, 1, 0 },
//            { 1, 0, 1, 0, 1, 0 },
//            { 1, 1, 1, 0, 1, 0 },
//            { 1, 0, 0, 1, 1, 1 },
//            { 1, 1, 1, 1, 0, 1 },
//            { 0, 0, 0, 0, 0, 1 }
//            };

	private static int[][] maze = {
            { 0, 1, 0, 0, 0 },
            { 1, 1, 0, 1, 0 },
            { 1, 0, 1, 1, 1 },
            { 1, 1, 1, 0, 1 },
            { 0, 0, 0, 0, 1 }
	};

	
	private static int numRows = maze.length;
	private static int numCols = numRows > 0 ? maze[0].length : 0;	
	private static int endRow = numRows -1;
	private static int endCol = numCols -1;
	
	static List<Cell<Integer, Integer>> list = new ArrayList<>();

//	private static boolean[][] wasHere = {
//            { false, false, false, false, false, false },
//            { false, false, false, false, false, false },
//            { false, false, false, false, false, false },
//            { false, false, false, false, false, false },
//            { false, false, false, false, false, false },
//            { false, false, false, false, false, false },
//            { false, false, false, false, false, false },
//            { false, false, false, false, false, false }
//            };

	private static boolean[][] wasHere = {
            { false, false, false, false, false },
            { false, false, false, false, false },
            { false, false, false, false, false },
            { false, false, false, false, false },
            { false, false, false, false, false },
	};
	
//	private static int[][] correctPath = {
//            { 0, 0, 0, 0, 0, 0 },
//            { 0, 0, 0, 0, 0, 0 },
//            { 0, 0, 0, 0, 0, 0 },
//            { 0, 0, 0, 0, 0, 0 },
//            { 0, 0, 0, 0, 0, 0 },
//            { 0, 0, 0, 0, 0, 0 },
//            { 0, 0, 0, 0, 0, 0 },
//            { 0, 0, 0, 0, 0, 0 }
//            };

	private static int[][] correctPath = {
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0 },
	};
	
	    public static boolean recursiveSolve(int row, int col) {
	        System.out.println("recursiveSolve(r=" + row + ", c=" + col + ")");
	        if (row == endRow && col == endCol) {
				correctPath[row][col] = 1;
				list.add(new Cell<>(row, col));
	            return true;
	        }

	        if (maze[row][col] == 0 || wasHere[row][col])
	            return false;
	        
	        wasHere[row][col] = true;
	        
	        if (row != 0) {
		        if (recursiveSolve(row-1, col)) {
					correctPath[row][col] = 1;
					list.add(new Cell<>(row, col));
					return true;
				}
			}

	        if (row != numRows-1) {
		        if (recursiveSolve(row+1, col)) {
					correctPath[row][col] = 1;
					list.add(new Cell<>(row, col));
					return true;
				}
			}
	        
	        if (col != 0) {
		        if (recursiveSolve(row, col-1)) {
					correctPath[row][col] = 1;
					list.add(new Cell<>(row, col));
					return true;
				}
			}

	        if (col != numCols-1) {
		        if (recursiveSolve(row, col+1)) {
					correctPath[row][col] = 1;
					list.add(new Cell<>(row, col));
					return true;
				}
			}
			return false;
	    }

	    public static void main(String[] args) {

	        int[][] mtx = Maze.maze;
	        boolean foundPath = false;

	        for (int col = 0; col < mtx[0].length; col++) {
	            foundPath = recursiveSolve(0, col);
	            System.out.println(foundPath);
	            if (foundPath) {
	            	break;
	            }
	        }

	        for (int i = 0; i < correctPath.length; i++) {
				System.out.println(Arrays.toString(correctPath[i]));
			}
	        
	        System.out.println(list);
	    }

}
