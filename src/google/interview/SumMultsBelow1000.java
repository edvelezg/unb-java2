package google.interview;

public class SumMultsBelow1000 {
    
    static long[] arr;
    
    public static int calc(int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }
        }
        return sum;
    }
    
    public static long fib(int n) {
        if (n == 0 || n == 1) return 1;
        
        if (arr[n-1] == 0) {
            arr[n-1] = fib(n-1) + fib(n-2);
        }
        
        return arr[n-1];
    }
    
    public static void main(String[] args) {
//        System.out.println(calc(1000));
        
        int sum = 0;
        for (int i = 1; i < 92; i++) {
            arr = new long[i];
            long res = fib(i);
            if (res % 2 == 0 && res < 4000000) {
                sum += res;
            }
            
            if (res > 4000000) {
                System.out.println("fib: " + res);
                break;
            }
        }
        System.out.println("sum: " + sum);
    }
}
