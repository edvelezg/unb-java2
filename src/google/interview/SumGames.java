package google.interview;

import java.util.Arrays;

public class SumGames {
    
    public static boolean hasSum(int[] a, int sum) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i+1; j < a.length; j++) {
                if (a[i] + a[j] == sum) return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        int[] a = new int[] { 5, 4, 2, 4 };
        int num = 8;
        
        if(hasSum(a, num)) System.out.println("yes");
        else System.out.println("no");
        a = new int[] { 5, 1, 2, 4 };
//        num = 8;
        if(hasSum(a, num)) System.out.println("yes");
        else System.out.println("no");
        
        a = new int[] { 8 };
        if(hasSum(a, num)) System.out.println("yes");
        else System.out.println("no");

        a = new int[] { };
        if(hasSum(a, num)) System.out.println("yes");
        else System.out.println("no");

        a = new int[] { 4, 4 };
        if(hasSum(a, num)) System.out.println("yes");
        else System.out.println("no");

        a = new int[] { 4, 5, 7, 8, 2, 9 , 10, 2, 4 };
        if(hasSum(a, num)) System.out.println("yes");
        else System.out.println("no");
        
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
       
    }

}
