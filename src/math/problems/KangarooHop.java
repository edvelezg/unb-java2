package math.problems;

import java.util.Scanner;

public class KangarooHop {

    static String kangaroo(int x1, int v1, int x2, int v2) {
    	int deltaV = v1 - v2;
    	if (deltaV > 0) {
    		int deltaX = x2 - x1;
    		if (deltaX > 0 && deltaX % deltaV == 0) return "YES";
		}
    	else if (deltaV < 0) {
    		int deltaX = x1 - x2;
    		if (deltaX > 0 && deltaX % deltaV == 0) return "YES";
    	} else {
    		if (x1 == x2) {
				return "YES";
			}
    	}
    	return "NO";
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x1 = in.nextInt();
        int v1 = in.nextInt();
        int x2 = in.nextInt();
        int v2 = in.nextInt();
        String result = kangaroo(x1, v1, x2, v2);
        System.out.println(result);
    }
}
