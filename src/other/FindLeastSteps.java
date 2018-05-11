package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindLeastSteps {

	public static void main(String[] args) {
		int n = 4;
		List<Integer> X = new ArrayList<>();
		List<Integer> Y = new ArrayList<>();
		X.add(0);
		X.add(4);
		X.add(1);
		X.add(2);
		
		Y.add(4);
		Y.add(2);
		Y.add(2);
		Y.add(0);

		int sum = 0;
	    int steps=0;
	    for(int i=0;i< X.size()-1;i++)
	    {
	        steps += Math.max(
	        			Math.abs(X.get(i+1)- X.get(i)), 
	        			Math.abs(Y.get(i+1)- Y.get(i))
	        		);
	    }
		System.out.println(steps);
	}
}
